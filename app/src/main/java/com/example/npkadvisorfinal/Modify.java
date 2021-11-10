package com.example.npkadvisorfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modify extends AppCompatActivity {
    private EditText cropname;
    private EditText croparea;
    private ImageButton modificar;
    private String name;
    private double area;
    private String aarea = String.valueOf(area);
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        cropname = findViewById(R.id.cropnametxt);
        croparea = findViewById(R.id.areatxt);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        cropname.setText(name);
        aarea = getIntent().getStringExtra("area");
        croparea.setText(aarea);
        modificar = findViewById(R.id.guardar);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar();
                modificar.setEnabled(false);
                cropname.setText("");
                croparea.setText("");
            }
        });
    }

    public void Modificar (){
        CropResponse2 cropResponse2 = new CropResponse2();
        cropResponse2.setCNombre(cropname.getText().toString());
        cropResponse2.setCArea(Double.parseDouble(croparea.getText().toString()));
        Call<CropResponse> cropResponseCall = ApiClient.getUserService().updateCrop(id, cropResponse2);
        cropResponseCall.enqueue(new Callback<CropResponse>() {
            @Override
            public void onResponse(Call<CropResponse> call, Response<CropResponse> response) {
                if (response.isSuccessful()) {
                  //  ArrayList<CropResponse2> cropResponses2 = response.body().getCultivosBuscados();
                    Toast.makeText(Modify.this, "Registro Modificado", Toast.LENGTH_LONG).show();
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