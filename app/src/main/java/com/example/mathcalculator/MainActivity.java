package com.example.mathcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundManager.release();
    }
}