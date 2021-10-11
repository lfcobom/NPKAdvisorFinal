package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficBar extends AppCompatActivity {

     BarChart bar;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_bar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bar = findViewById(R.id.barChart);
        Databar();
    }

    public void Databar() {
        ArrayList<BarEntry> data = new ArrayList<>();
        ArrayList<BarEntry> data1 = new ArrayList<>();
        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        android.util.Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        data.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getN().toString())));
                        data.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getP().toString())));
                        data.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getK().toString())));
                       // data.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getTemp().toString())));
                    }

                    BarDataSet barDataSet = new BarDataSet(data,"N %");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(android.graphics.Color.BLACK);
                    barDataSet.setValueTextSize(16f);

                    BarDataSet barDataSet1 = new BarDataSet(data,"P %");
                    barDataSet1.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet1.setValueTextColor(android.graphics.Color.BLACK);
                    barDataSet1.setValueTextSize(16f);


                    BarData barData = new BarData(barDataSet);
                    bar.setFitBars(true);
                    bar.setData(barData);
                    bar.getDescription().setText("JUM");
                    bar.animateY(2000);
                    bar.invalidate();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficBar.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}