package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class GraficBar extends AppCompatActivity {

     private BarChart bar;
     int[]colorClassArray = new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.CYAN};
     private String desde;
     private String hasta;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_bar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Bundle bundle = getIntent().getExtras();
        desde =  bundle.getString("desde");
        hasta = bundle.getString("hasta");
        bar = findViewById(R.id.barChart);
        Databar();

    }

    public void Databar() {
        ArrayList<BarEntry> datan = new ArrayList<>();
        ArrayList<BarEntry> datap = new ArrayList<>();
        ArrayList<BarEntry> datak = new ArrayList<>();
        ArrayList<BarEntry> datat = new ArrayList<>();
        ArrayList<BarEntry> datah = new ArrayList<>();

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
                                //android.util.Log.d(TAG, "onResponse: \n " +
                                //      "Cultivo " + indexResponse.get(i).getHumedad());
                                datan.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getN().toString())));
                                datap.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getP().toString())));
                                datak.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getK().toString())));
                                datat.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getTemp().toString())));
                                datah.add(new BarEntry(i, Float.parseFloat(indexResponse.get(i).getHumedad().toString())));
                            }
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                    BarDataSet set1,set2,set3,set4,set5;

                    set1= new BarDataSet(datan,"N");
                    set1.setColor(Color.BLUE);
                    set2= new BarDataSet(datap,"P");
                    //set2.setColor(Color.RED);
                    set3= new BarDataSet(datak,"K");
                    //set3.setColor(Color.YELLOW);
                    set4= new BarDataSet(datat,"T°");
                    //set4.setColor(Color.GREEN);
                    set5= new BarDataSet(datah,"Hum°");
                    //set5.setColor(Color.CYAN);
                    BarData barData =  new BarData(set1,set2,set3,set4,set5);
                    bar.setData(barData);
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