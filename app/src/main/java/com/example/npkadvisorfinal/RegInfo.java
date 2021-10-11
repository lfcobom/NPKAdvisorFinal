package com.example.npkadvisorfinal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//https://www.ibm.com/docs/es/db2/11.1?topic=documents-key-concepts

public class RegInfo extends AppCompatActivity {
    Button pruebaGet;
    TextView Humedad;
    TextView N;
    TextView P;
    TextView K;
    TextView Ph;
    TextView Temp;
    TextView horas;
    TextView fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_info);
        pruebaGet = findViewById(R.id.pruebaget);
        Humedad = findViewById(R.id.pruebagetT);
        N = findViewById(R.id.n);
        P = findViewById(R.id.p);
        K = findViewById(R.id.k);
        Ph = findViewById(R.id.ph);
        Temp = findViewById(R.id.temp);
        horas = findViewById(R.id.time);
        fecha = findViewById(R.id.date);
        horas = findViewById(R.id.time);
        pruebaGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Index();
            }
        });
    }

    public void Index() {

        Calendar rightNow = Calendar.getInstance(); //FORMATO DATE
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int min = rightNow.get(Calendar.MINUTE);

        //https://mkyong.com/java/java-date-and-calendar-examples/
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());



            Call<IndexResponse> indexResponseCall = ApiClient.getUserService().findIndex1();
            indexResponseCall.enqueue(new Callback<IndexResponse>() {
                @Override
                public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<IndexResponse2> IndexReponses = response.body().getInfoIndex();
                        for (int i = 0; i < IndexReponses.size(); i++) {
                            Humedad.setText((IndexReponses.get(i).getHumedad()).toString());
                            N.setText(IndexReponses.get(i).getN().toString());
                            P.setText((IndexReponses.get(i).getP()).toString());
                            K.setText((IndexReponses.get(i).getK()).toString());
                            Ph.setText((IndexReponses.get(i).getPh()).toString());
                            Temp.setText((IndexReponses.get(i).getTemp()).toString());
                            horas.setText(hour +":"+ min);
                            fecha.setText(date);
                        }

                    } else {
                        Toast.makeText(com.example.npkadvisorfinal.RegInfo.this, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<IndexResponse> call, Throwable t) {
                    Toast.makeText(com.example.npkadvisorfinal.RegInfo.this, "Request Failed", Toast.LENGTH_LONG).show();

                }
            });
        }
    }