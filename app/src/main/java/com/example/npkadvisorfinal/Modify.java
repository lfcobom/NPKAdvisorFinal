package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modify extends AppCompatActivity {
    private EditText cropname;
    private EditText croparea;
    private ImageButton modificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        cropname = findViewById(R.id.cropnametxt);
        croparea = findViewById(R.id.areatxt);
        String name = getIntent().getStringExtra("name");
        cropname.setText(name);
        String area = getIntent().getStringExtra("area");
        croparea.setText(area);
        modificar = findViewById(R.id.modificar);
    }

    public void Modificar (){
        Call<CropResponse> cropResponseCall = ApiClient.getUserService().findAllC();
        cropResponseCall.enqueue(new Callback<CropResponse>() {
            @Override
            public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<CropResponse2> cropResponses2 = response.body().getCultivosBuscados();
                }
            }
            @Override
            public void onFailure(Call<CropResponse> call, Throwable t) {
                Toast.makeText(Modify.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();
                //System.out.println("causes" + t.fillInStackTrace());
            }
        });
    }
}