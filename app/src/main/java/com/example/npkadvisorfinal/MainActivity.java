package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton1 = findViewById(R.id.button);
        boton1.setOnClickListener((v)->{
            Intent intencion = new Intent(getApplicationContext(),MainMenu2.class);
            startActivity(intencion);
        });

    }
}