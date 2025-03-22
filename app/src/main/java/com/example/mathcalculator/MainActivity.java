package com.example.mathcalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("Language", "");
        if(!language.isEmpty()){
            setLocale(language);
        }

        ImageButton btnChangeLanguage = findViewById(R.id.change_language);
        btnChangeLanguage.setOnClickListener(v ->showLanguageDialog());

        soundManager = new SoundManager(this);

        MaterialCardView cylinderCard = findViewById(R.id.cylinder_card);
        cylinderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playClickSound();
                Intent intent = new Intent(MainActivity.this, CylinderCalculator.class);
                startActivity(intent);
            }
        });

        MaterialCardView coneCard = findViewById(R.id.cone_card);
        coneCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playClickSound();
                Intent intent = new Intent(MainActivity.this, ConeCalculator.class);
                startActivity(intent);
            }
        });

        MaterialCardView sphereCard = findViewById(R.id.sphere_card);
        sphereCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playClickSound();
                Intent intent = new Intent(MainActivity.this, SphereCalculator.class);
                startActivity(intent);
            }
        });

        MaterialCardView triangleCard = findViewById(R.id.triangle_card);
        triangleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playClickSound();
                Intent intent = new Intent(MainActivity.this, TriangleCalculator.class);
                startActivity(intent);
            }
        });

        MaterialCardView quadraticEquationCard = findViewById(R.id.quadratic_equation_card);
        quadraticEquationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playClickSound();
                Intent intent = new Intent(MainActivity.this, QuadraticEquationCalculator.class);
                startActivity(intent);
            }
        });
    }

    //Метод вызова диалога для выбора языка
    private void showLanguageDialog(){
        String[] languages = getResources().getStringArray(R.array.languages);
        String[] languageCodes = getResources().getStringArray(R.array.language_codes);

        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String currentLanguage = preferences.getString("Language","en");

        int selectedIndex = Arrays.asList(languageCodes).indexOf(currentLanguage);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.change_language);

        builder.setSingleChoiceItems(languages, selectedIndex, (dialog,which)->{
            String selectedLanguageCode = languageCodes[which];

            setLocale(selectedLanguageCode);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Language", selectedLanguageCode);
            editor.apply();

            dialog.dismiss();

            restartApp();;
        });
        builder.setNegativeButton(R.string.cancel,(dialog, which) -> dialog.dismiss());
        builder.show();
    }

    //Метод изменения локали
    private void setLocale(String languageCode){
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        Context context = createConfigurationContext(config);
        getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Language", languageCode);
        editor.apply();
    }

    //Метод для перезапуска приложения
    private void restartApp(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundManager.release();
    }
}