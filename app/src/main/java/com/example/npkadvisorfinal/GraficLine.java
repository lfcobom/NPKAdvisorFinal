package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficLine extends AppCompatActivity {

    LineChart lineChart;
    LineData lineData;
    LineData lineData2;
    LineChart lineChart2;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_line);
        lineChart = findViewById(R.id.radarChart);
        Dataradar();
    }

    public void Dataradar() {
        ArrayList<Entry> datan = new ArrayList<>();
        ArrayList<Entry> datap = new ArrayList<>();
        ArrayList<Entry> datak = new ArrayList<>();
        ArrayList<Entry> datatemp = new ArrayList<>();

        ArrayList<String> datadate = new ArrayList<>();

        Call<IndexResponse> cropResponseCall = ApiClient.getUserService().findIndex();
        cropResponseCall.enqueue(new Callback<IndexResponse>() {
            @Override
            public void onResponse(Call<IndexResponse> call, Response<IndexResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<IndexResponse2> indexResponse = response.body().getInfoIndex();
                    for (int i = 0; i < indexResponse.size(); i++) {
                        android.util.Log.d(TAG, "onResponse: \n " +
                                "Cultivo " + indexResponse.get(i).getHumedad());
                        datan.add(new Entry(i, Float.parseFloat(String.valueOf(indexResponse.get(i).getN()))));
                        datap.add(new Entry(i,Float.parseFloat(String.valueOf(indexResponse.get(i).getP()))));
                        datak.add(new Entry(i,Float.parseFloat(String.valueOf(indexResponse.get(i).getK()))));
                        datatemp.add(new Entry(i,Float.parseFloat(String.valueOf(indexResponse.get(i).getTemp()))));
                    }


                    String[] dates = new String[datadate.size()];
                    for(int i = 0; i< datadate.size(); i++)
                    {
                        dates[i] = datadate.get(i);
                    }

                    LineDataSet set1,set2,set3,set4;

                    set1= new LineDataSet(datan,"N");
                    set1.setColor(Color.BLUE);
                    set2= new LineDataSet(datap,"P");
                    set2.setColor(Color.RED);
                    set3= new LineDataSet(datak,"K");
                    set3.setColor(Color.YELLOW);
                    set4= new LineDataSet(datatemp,"T°");
                    set4.setColor(Color.GREEN);
                    LineData lineData =  new LineData(set1,set2,set3,set4);
                    lineChart.setData(lineData);
                    lineChart.invalidate();
                }
            }
            @Override
            public void onFailure(Call<IndexResponse> call, Throwable t) {
                Toast.makeText(GraficLine.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}