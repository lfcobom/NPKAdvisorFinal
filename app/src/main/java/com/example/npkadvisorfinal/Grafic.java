package com.example.npkadvisorfinal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

//https://developer.mozilla.org/es/docs/Web/JavaScript/Reference/Global_Objects/Date
//https://github.com/PhilJay/MPAndroidChart

public class Grafic extends AppCompatActivity {
    Button btnbar, btnpie, btnline, btndesde, btnhasta;
    private String desde;
    private String hasta;
    TextView txtdesde, txthasta;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnbar = findViewById(R.id.bar);
        btnpie = findViewById(R.id.pie);
        btnline =findViewById(R.id.line);
        btndesde = findViewById(R.id.desdegrafic);
        btnhasta = findViewById(R.id.hastagrafic);
        txtdesde = findViewById(R.id.txtdesde);
        txthasta = findViewById(R.id.txthasta);



        btnbar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Enviar();
            }
        });

        btnpie.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Enviar1();
            }
        });


        btnline.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Enviar2();
            }
        });

        btndesde.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate();
            }
        });

        btnhasta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate1();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ChooseDate(){
        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int day = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH);
        int year = rightNow.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtdesde.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);
                desde = txtdesde.getText().toString();
            }
        }
                ,day,month,year);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void ChooseDate1(){
        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int day1 = rightNow.get(Calendar.DAY_OF_MONTH);
        int month1 = rightNow.get(Calendar.MONTH);
        int year1 = rightNow.get(Calendar.YEAR) - 2021;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txthasta.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);
                hasta = txthasta.getText().toString();
            }
        }
                ,day1,month1,year1);
        datePickerDialog.show();
    }

    public void Enviar(){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Grafic.this, GraficBar.class);
        // Agregas la información del EditText al Bundle
        bundle.putString("desde", desde);
        bundle.putString("hasta", hasta);
        // Agregas el Bundle al Intent e inicias ActivityB
        intent.putExtras(bundle);
        Grafic.this.startActivity(intent);
    }
    public void Enviar1(){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Grafic.this, GraficPie.class);
        // Agregas la información del EditText al Bundle
        bundle.putString("desde", desde);
        bundle.putString("hasta", hasta);
        // Agregas el Bundle al Intent e inicias ActivityB
        intent.putExtras(bundle);
        Grafic.this.startActivity(intent);
    }
    public void Enviar2(){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(Grafic.this, GraficLine.class);
        // Agregas la información del EditText al Bundle
        bundle.putString("desde", desde);
        bundle.putString("hasta", hasta);
        // Agregas el Bundle al Intent e inicias ActivityB
        intent.putExtras(bundle);
        Grafic.this.startActivity(intent);
    }
}