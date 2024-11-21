package com.example.mathcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConeCalculator extends AppCompatActivity {

    private EditText heightEdit;
    private EditText radiusEdit;
    private TextView baseAreaText;
    private TextView sideAreaText;
    private TextView fullAreaText;
    private TextView volumeText;
    private TextView generatorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cone_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        heightEdit = findViewById(R.id.h_value);
        radiusEdit = findViewById(R.id.r_value);
        baseAreaText = findViewById(R.id.base_area);
        sideAreaText = findViewById(R.id.side_area);
        fullAreaText = findViewById(R.id.full_area);
        generatorText = findViewById(R.id.generator);
        volumeText = findViewById(R.id.volume);

        Button calculateButton = findViewById(R.id.calculate_btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{
                   double height = Double.parseDouble(heightEdit.getText().toString());
                   double radius = Double.parseDouble(radiusEdit.getText().toString());

                   double baseArea = Math.PI * Math.pow(radius,2);
                   double generator = Math.sqrt(Math.pow(radius,2) + Math.pow(height,2));
                   double sideArea = Math.PI * radius * generator;
                   double fullArea = baseArea + sideArea;
                   double volume = (Math.PI * Math.pow(radius,2) * height)/3;

                   baseAreaText.setText(String.format("%.2f", baseArea));
                   sideAreaText.setText(String.format("%.2f", sideArea));
                   fullAreaText.setText(String.format("%.2f", fullArea));
                   volumeText.setText(String.format("%.2f", volume));
                   generatorText.setText(String.format("%.2f",generator));

               } catch (NumberFormatException e){
                   Toast.makeText(
                           ConeCalculator.this,
                           "Пожалуйста, введите корректные значения.",
                           Toast.LENGTH_SHORT
                   ).show();
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