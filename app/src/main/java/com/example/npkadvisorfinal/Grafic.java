package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;

//https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/Date
//https://github.com/PhilJay/MPAndroidChart

public class Grafic extends AppCompatActivity {
    Button btnbar, btnpie, btnline;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnbar = findViewById(R.id.bar);
        btnpie = findViewById(R.id.pie);
        btnline =findViewById(R.id.line);

        btnbar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(getApplicationContext(), GraficBar.class));
            }
        });

        btnpie.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(getApplicationContext(), GraficPie.class));
            }
        });


        btnline.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                startActivity(new Intent(getApplicationContext(), GraficLine.class));
            }
        });
    }
}