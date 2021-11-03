package com.example.npkadvisorfinal;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://www.youtube.com/watch?v=y3exATaC0kA
public class History extends AppCompatActivity {
    ImageButton ChooseDate;
    ImageButton ChooseDateHasta;
    Button csvExport;
    Button Saveb;
    TextView ChooseT1;
    TextView ChooseT;
    TableRow fila;
    TextView Humedad;
    TextView N;
    TextView P;
    TextView K;
    TextView Temp;
    TextView Datex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ChooseDate = findViewById(R.id.choosedate);
        ChooseDateHasta = findViewById(R.id.choosedate1);
        ChooseT = findViewById(R.id.chooseText);
        ChooseT1 = findViewById(R.id.choosedateT1);
        csvExport = findViewById(R.id.csv);
        Saveb = findViewById(R.id.saveDate);

        Saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Save();
               Saveb.setEnabled(false);
            }
        });

        RequestPermissions();
        datalist();

        csvExport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                datalist();
                Toast.makeText(com.example.npkadvisorfinal.History.this,"Se creo existosamente" ,Toast.LENGTH_SHORT).show();
                //humedadlist();
            }
        });

        ChooseDateHasta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate1();
            }
        });
        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                ChooseDate();
            }
        });

    }
    public void Save(){
        TableRow.LayoutParams layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layouthumedad = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutN = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutP = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutK = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutTemp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams layoutDate = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    TableLayout lista = findViewById(R.id.table);
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                    for (int i = 0; i < IndexReponses.size(); i++) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date1 = sdf.parse(ChooseT.getText().toString());
                            Date date2 = sdf.parse(ChooseT1.getText().toString());
                            Date datedb = sdf.parse(IndexReponses.get(i).getCreateAt());

                            if ((datedb.after(date1)||datedb.equals(date1)) && (datedb.before(date2)||datedb.equals(date2))) {
                                fila = new TableRow(com.example.npkadvisorfinal.History.this);
                                fila.setLayoutParams(layoutFila);
                                if (i == 0) {
                                    Datex = new TextView(com.example.npkadvisorfinal.History.this);
                                    Datex.setText("FECHA");
                                    Datex.setGravity(Gravity.CENTER);
                                    Datex.setBackgroundColor(Color.BLACK);
                                    Datex.setTextColor(Color.WHITE);
                                    Datex.setPadding(10, 10, 10, 10);
                                    Datex.setLayoutParams(layoutDate);
                                    fila.addView(Datex);

                                    Humedad = new TextView(com.example.npkadvisorfinal.History.this);
                                    Humedad.setText("HUMEDAD");
                                    Humedad.setGravity(Gravity.CENTER);
                                    Humedad.setBackgroundColor(Color.BLACK);
                                    Humedad.setTextColor(Color.WHITE);
                                    Humedad.setPadding(10, 10, 10, 10);
                                    Humedad.setLayoutParams(layouthumedad);
                                    fila.addView(Humedad);

                                    N = new TextView(com.example.npkadvisorfinal.History.this);
                                    N.setText("N");
                                    N.setGravity(Gravity.CENTER);
                                    N.setBackgroundColor(Color.BLACK);
                                    N.setTextColor(Color.WHITE);
                                    N.setPadding(10, 10, 10, 10);
                                    N.setLayoutParams(layoutN);
                                    fila.addView(N);

                                    P = new TextView(com.example.npkadvisorfinal.History.this);
                                    P.setText("P");
                                    P.setGravity(Gravity.CENTER);
                                    P.setBackgroundColor(Color.BLACK);
                                    P.setTextColor(Color.WHITE);
                                    P.setPadding(10, 10, 10, 10);
                                    P.setLayoutParams(layoutP);
                                    fila.addView(P);

                                    K = new TextView(com.example.npkadvisorfinal.History.this);
                                    K.setText("K");
                                    K.setGravity(Gravity.CENTER);
                                    K.setBackgroundColor(Color.BLACK);
                                    K.setTextColor(Color.WHITE);
                                    K.setPadding(10, 10, 10, 10);
                                    K.setLayoutParams(layoutK);
                                    fila.addView(K);

                                    Temp = new TextView(com.example.npkadvisorfinal.History.this);
                                    Temp.setText("T°");
                                    Temp.setGravity(Gravity.CENTER);
                                    Temp.setBackgroundColor(Color.BLACK);
                                    Temp.setTextColor(Color.WHITE);
                                    Temp.setPadding(10, 10, 10, 10);
                                    Temp.setLayoutParams(layoutTemp);
                                    fila.addView(Temp);
                                    lista.addView(fila);
                                } else {
                                    Datex = new TextView(com.example.npkadvisorfinal.History.this);
                                    Datex.setText(IndexReponses.get(i).getCreateAt());
                                    Datex.setPadding(10, 10, 10, 10);
                                    Datex.setGravity(Gravity.CENTER);
                                    Datex.setBackgroundColor(Color.BLUE);
                                    Datex.setLayoutParams(layoutDate);
                                    fila.addView(Datex);

                                    Humedad = new TextView(com.example.npkadvisorfinal.History.this);
                                    Humedad.setText(IndexReponses.get(i).getHumedad().toString());
                                    Humedad.setPadding(10, 10, 10, 10);
                                    Humedad.setGravity(Gravity.CENTER);
                                    Humedad.setBackgroundColor(Color.BLUE);
                                    Humedad.setLayoutParams(layouthumedad);
                                    fila.addView(Humedad);

                                    N = new TextView(com.example.npkadvisorfinal.History.this);
                                    N.setText(IndexReponses.get(i).getN().toString());
                                    N.setPadding(10, 10, 10, 10);
                                    N.setGravity(Gravity.CENTER);
                                    N.setBackgroundColor(Color.BLUE);
                                    N.setLayoutParams(layoutN);
                                    fila.addView(N);

                                    P = new TextView(com.example.npkadvisorfinal.History.this);
                                    P.setText(IndexReponses.get(i).getP().toString());
                                    P.setPadding(10, 10, 10, 10);
                                    P.setGravity(Gravity.CENTER);
                                    P.setBackgroundColor(Color.BLUE);
                                    P.setLayoutParams(layoutP);
                                    fila.addView(P);

                                    K = new TextView(com.example.npkadvisorfinal.History.this);
                                    K.setText(IndexReponses.get(i).getK().toString());
                                    K.setPadding(10, 10, 10, 10);
                                    K.setGravity(Gravity.CENTER);
                                    K.setBackgroundColor(Color.BLUE);
                                    K.setLayoutParams(layoutK);
                                    fila.addView(K);

                                    Temp = new TextView(com.example.npkadvisorfinal.History.this);
                                    Temp.setText(IndexReponses.get(i).getTemp().toString());
                                    Temp.setPadding(10, 10, 10, 10);
                                    Temp.setGravity(Gravity.CENTER);
                                    Temp.setBackgroundColor(Color.BLUE);
                                    Temp.setLayoutParams(layoutTemp);
                                    fila.addView(Temp);

                                    lista.addView(fila);
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(com.example.npkadvisorfinal.History.this, "Request Failed", Toast.LENGTH_LONG).show();

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
                ChooseT.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);
            }
        }
        ,day,month,year);
        datePickerDialog.show();
    }

    public void ChooseDate1(){
        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int day1 = rightNow.get(Calendar.DAY_OF_MONTH);
        int month1 = rightNow.get(Calendar.MONTH);
        int year1 = rightNow.get(Calendar.YEAR) - 2021;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ChooseT1.setText(dayOfMonth+ "/" +(monthOfYear+1)+"/"+year);
            }
        }
                ,day1,month1,year1);
        datePickerDialog.show();
    }

    public void RequestPermissions(){

        if(ContextCompat.checkSelfPermission(com.example.npkadvisorfinal.History.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(com.example.npkadvisorfinal.History.this, new String []{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
    }
    public void datalist(){
        File file = new File(Environment.getExternalStorageDirectory() + "/CSV");
        String archivo = file.toString()+"/"+"NPK.csv";

        boolean isCreate = false;
        if(!file.exists()){
            isCreate = file.mkdir();
        }
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
        ArrayList<String> data = new ArrayList<>();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();

                    try {
                        FileWriter fileWriter = new FileWriter(archivo);
                        fileWriter.write("N");
                        fileWriter.write(",");
                        fileWriter.write("P");
                        fileWriter.write(",");
                        fileWriter.write("K");
                        fileWriter.write("\n");


                        for (int i = 0; i < IndexReponses.size(); i++) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date date1 = sdf.parse(ChooseT.getText().toString());
                                Date date2 = sdf.parse(ChooseT1.getText().toString());
                                Date datedb = sdf.parse(IndexReponses.get(i).getCreateAt());
                                if ((datedb.after(date1) || datedb.equals(date1)) && (datedb.before(date2) || datedb.equals(date2))) {
                                    fileWriter.write(IndexReponses.get(i).getN().toString());
                                    fileWriter.write(",");
                                    fileWriter.write(IndexReponses.get(i).getP().toString());
                                    fileWriter.write(",");
                                    fileWriter.write(IndexReponses.get(i).getK().toString());
                                    fileWriter.write(",");
                                    fileWriter.write("\n");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        fileWriter.flush();
                        fileWriter.close();
                        Toast.makeText(com.example.npkadvisorfinal.History.this, "Se creo existosamente", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(com.example.npkadvisorfinal.History.this, "Request Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void humedadlist(){
        File file = new File(Environment.getExternalStorageDirectory() + "/CSV");
        String archivo = file.toString()+"/"+"Humedad.csv";

        boolean isCreate = false;
        if(!file.exists()){
            isCreate = file.mkdir();
        }
        Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
        ArrayList<String> data = new ArrayList<>();
        indexResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                    try {
                        FileWriter fileWriter = new FileWriter(archivo);
                        int count = 0;
                        for (int i = 0; i < IndexReponses.size(); i++) {
                            count++;
                            fileWriter.write(IndexReponses.get(i).getHumedad().toString());
                            fileWriter.write(",");
                            if (count == 5){
                                fileWriter.write("\n");
                                count = 0;
                            }
                        }
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    catch (Exception e){}
                }

            }

            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(com.example.npkadvisorfinal.History.this, "Request Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
