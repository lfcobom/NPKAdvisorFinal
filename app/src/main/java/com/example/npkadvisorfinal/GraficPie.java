package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficPie extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_pie);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pieChart = findViewById(R.id.pieChart);
        Datapie();
    }

    public void Datapie() {
        ArrayList<PieEntry> datan = new ArrayList<>();
        ArrayList<PieEntry> datap = new ArrayList<>();
        ArrayList<PieEntry> datak= new ArrayList<>();
        ArrayList<PieEntry> datatemp = new ArrayList<>();
        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        android.util.Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getN()))));
                        datap.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getP()))));
                        datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getK()))));
                        datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getTemp()))));
                    }

                    PieDataSet set1,set2,set3,set4;

                    set1= new PieDataSet(datan,"N");
                    set1.setColor(Color.BLUE);
                    set2= new PieDataSet(datan,"P");
                    set2.setColor(Color.RED);
                    set3= new PieDataSet(datan,"K");
                    set3.setColor(Color.YELLOW);
                    set4= new PieDataSet(datan,"T°");
                    set4.setColor(Color.GREEN);
                    PieData pieData =  new PieData(set1);
                    pieChart.setData(pieData);
                    pieChart.invalidate();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficPie.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}