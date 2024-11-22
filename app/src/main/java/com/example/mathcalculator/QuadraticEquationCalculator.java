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

public class QuadraticEquationCalculator extends AppCompatActivity {

    private EditText aEdit;
    private EditText bEdit;
    private EditText cEdit;
    private TextView x1Text;
    private TextView x2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quadratic_equation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        aEdit = findViewById(R.id.a_value);
        bEdit = findViewById(R.id.b_value);
        cEdit = findViewById(R.id.c_value);
        x1Text = findViewById(R.id.x1);
        x2Text = findViewById(R.id.x2);

        Button calculateButton = findViewById(R.id.calculate_btn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(aEdit.getText().toString());
                    double b = Double.parseDouble(bEdit.getText().toString());
                    double c = Double.parseDouble(cEdit.getText().toString());

                    double d = Math.pow(b,2) - 4 * a * c;

                    if(d > 0){
                        double x1 = (-b + Math.sqrt(d))/(2 * a);
                        double x2 = (-b - Math.sqrt(d))/(2 * a);

                        x1Text.setText(String.format("%.2f", x1));
                        x2Text.setText(String.format("%.2f", x2));
                    } else if (d == 0) {
                        double x1 = -b / (2 * a);

                        x1Text.setText(String.format("%.2f", x1));
                        x2Text.setText("-");
                    }else {
                        Toast.makeText(
                                QuadraticEquationCalculator.this,
                                "Корней нет.",
                                Toast.LENGTH_SHORT
                        ).show();
                        x1Text.setText("-");
                        x2Text.setText("-");
                    }

                }catch (NumberFormatException e){
                    Toast.makeText(
                            QuadraticEquationCalculator.this,
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