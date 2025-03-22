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

public class TriangleCalculator extends AppCompatActivity {

    private EditText aEdit;
    private EditText bEdit;
    private EditText cEdit;
    private TextView perimeterText;
    private TextView areaText;
    private TextView alphaText;
    private TextView betaText;
    private TextView gammaText;

    private VibrationManager vibrationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_triangle_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aEdit = findViewById(R.id.a_value);
        bEdit = findViewById(R.id.b_value);
        cEdit = findViewById(R.id.c_value);
        perimeterText = findViewById(R.id.perimeter);
        areaText = findViewById(R.id.area);
        alphaText = findViewById(R.id.alpha);
        betaText = findViewById(R.id.beta);
        gammaText = findViewById(R.id.gamma);

        vibrationManager = new VibrationManager(this);

        Button calculateButton = findViewById(R.id.calculate_btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    double a = Double.parseDouble(aEdit.getText().toString());
                    double b = Double.parseDouble(bEdit.getText().toString());
                    double c = Double.parseDouble(cEdit.getText().toString());
                    double perimeter = a+b+c;
                    double p = perimeter/2;
                    double area = Math.sqrt(p * (p-a) * (p-b) * (p-c));

                    if(area==0 || Double.isNaN(area)){
                        Toast.makeText(
                                TriangleCalculator.this,
                                "Такого треугольника не существует!.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else {
                        double alpha = Math.toDegrees(Math.acos((Math.pow(b,2) + Math.pow(c,2) - Math.pow(a,2))/(2 * b * c)));
                        double beta = Math.toDegrees(Math.asin((b/a) * Math.sin(Math.toRadians(alpha))));
                        double gamma = Math.toDegrees(Math.asin((c/a) * Math.sin(Math.toRadians(alpha))));

                        perimeterText.setText(String.format("%.2f", perimeter));
                        areaText.setText(String.format("%.2f", area));
                        alphaText.setText(String.format("%.2f", alpha) + "°");
                        betaText.setText(String.format("%.2f", beta) + "°");
                        gammaText.setText(String.format("%.2f", gamma) + "°");
                    }
                    vibrationManager.vibrateSuccess();
                }catch (NumberFormatException e){
                    Toast.makeText(
                            TriangleCalculator.this,
                            "Пожалуйста, введите корректные значения.",
                            Toast.LENGTH_SHORT
                    ).show();
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