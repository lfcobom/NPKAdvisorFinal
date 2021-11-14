package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficPie extends AppCompatActivity {

    PieChart pieChart;
    float sum = 0;
    private String desde;
    private String hasta;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_pie);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pieChart = findViewById(R.id.pieChart);
        Bundle bundle = getIntent().getExtras();
        desde =  bundle.getString("desde");
        hasta = bundle.getString("hasta");
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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date date1 = sdf.parse(desde);
                            Date date2 = sdf.parse(hasta);
                            Date datedb = sdf.parse(indexResponse.get(i).getCreateAt());
                            if ((datedb.after(date1) || datedb.equals(date1)) && (datedb.before(date2) || datedb.equals(date2))) {

                                datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getHumedad())), "Hum"));
                                datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getN())), "N"));
                                datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getP())), "P"));
                                datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getK())), "K"));
                                datan.add(new PieEntry(Float.parseFloat(String.valueOf(indexResponse.get(i).getTemp())), "T°"));
                            }
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }

                    final int[] sliceColors = {Color.GREEN, Color.RED, Color.BLUE, Color.GRAY, Color.YELLOW};
                    ArrayList<Integer> colors = new ArrayList<>();
                    for (int color : sliceColors){
                        colors.add(color);
                    }


                    PieDataSet dataSet = new PieDataSet(datan,"Values");
                    dataSet.setColors(colors);

                    PieData data = new PieData(dataSet);
                    data.setDrawValues(true);
                    data.setValueFormatter(new PercentFormatter(pieChart));
                    data.setValueTextSize(12f);
                    data.setValueTextColor(Color.BLACK);

                    pieChart.setData(data);
                    pieChart.setDrawHoleEnabled(true);
                    pieChart.setEntryLabelTextSize(12);
                    pieChart.setEntryLabelColor(Color.BLACK);
                    pieChart.setCenterText("NPK Advisor");
                    pieChart.setCenterTextSize(20);
                    pieChart.getDescription().setEnabled(false);
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