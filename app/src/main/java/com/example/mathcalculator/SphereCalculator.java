package com.example.mathcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SphereCalculator extends AppCompatActivity {

    private EditText radiusEdit;
    private TextView areaText;
    private TextView volumeText;

    private VibrationManager vibrationManager;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sphere_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        radiusEdit = findViewById(R.id.r_value);
        areaText = findViewById(R.id.area);
        volumeText = findViewById(R.id.volume);

        vibrationManager = new VibrationManager(this);
        soundManager = new SoundManager(this);

        Button calculateButton = findViewById(R.id.calculate_btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double radius = Double.parseDouble(radiusEdit.getText().toString());
                    double area = 4 * Math.PI * Math.pow(radius,2);
                    double volume = (4 * Math.PI * Math.pow(radius,3))/3;
                    areaText.setText(String.format("%.2f", area));
                    volumeText.setText(String.format("%.2f", volume));
                    soundManager.playSuccessSound();
                    vibrationManager.vibrateSuccess();
                } catch (NumberFormatException e){
                    Toast.makeText(
                            SphereCalculator.this,
                            R.string.error_message,
                            Toast.LENGTH_SHORT
                    ).show();
                    soundManager.playErrorSound();
                    vibrationManager.vibrateError();
                }
            }
        });

        ImageButton arrowBack = findViewById(R.id.arrow_back);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}