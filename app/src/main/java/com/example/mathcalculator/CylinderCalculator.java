package com.example.mathcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CylinderCalculator extends AppCompatActivity {

    private EditText heightEdit;
    private EditText radiusEdit;
    private TextView baseAreaText;
    private TextView sideAreaText;
    private TextView fullAreaText;
    private TextView volumeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cylinder_calculator);
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
        volumeText = findViewById(R.id.volume);

        Button calculateButton = findViewById(R.id.calculate_btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double height = Double.parseDouble(heightEdit.getText().toString());
                    double radius = Double.parseDouble(radiusEdit.getText().toString());

                    double baseArea = Math.PI * Math.pow(radius, 2);
                    double sideArea = 2 * Math.PI * radius * height;
                    double fullArea = 2 * baseArea + sideArea;
                    double volume = baseArea * height;

                    baseAreaText.setText(String.format("%.2f", baseArea));
                    sideAreaText.setText(String.format("%.2f", sideArea));
                    fullAreaText.setText(String.format("%.2f", fullArea));
                    volumeText.setText(String.format("%.2f", volume));
                } catch (NumberFormatException e) {
                    Toast.makeText(
                            CylinderCalculator.this,
                            "Пожалуйста, введите корректные значения для высоты и радиуса.",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });
    }
}