package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {

    ImageView charts;
    ImageView miscultivos;
    ImageView regInfo;
    ImageView maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        charts = findViewById(R.id.graphics);
        miscultivos = findViewById(R.id.vercultivos);
        maps = findViewById(R.id.maps);
        regInfo = findViewById(R.id.reginfo);

        //Actividad Mapas
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open2();
            }
        });

        //Actividad Mis Cultivos
        miscultivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open();
            }
        });

        //Actividad Gráficas
        charts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open3();
            }
        });

        //Actividad Registro de Información
        regInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open1();
            }
        });
    }


    public void Open() {  //Lleva a la actividad Mis Cultivos

        startActivity(new Intent(MainMenu.this, MainMenu2.class));
    }
    public void Open1() {  //Lleva a la actividad Registro de información

       // startActivity(new Intent(MainMenu.this, Grafic.class));
    }
    public void Open2() {  //Lleva a la actividad Mapas

        //startActivity(new Intent(MainMenu.this, RegInfo.class));
    }

    public void Open3() {  //Lleva a la actividad Gráficas

        //  startActivity(new Intent(MainMenu.this, pruebafoto.class));
    }
}