package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.MessagePattern;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText username1;
    EditText password1;
    ImageView start_user;
    ImageView register;
    private static String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username1 = findViewById(R.id.username1);
        password1 = findViewById(R.id.password1);
        start_user = findViewById(R.id.btn_inicio);
        register = findViewById(R.id.register);


        start_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    SignIn();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenRegister();
            }
        });
    }



    public void Open() {  //Lleva a la actividad de Menú después de autenticar las credenciales.
        startActivity(new Intent(MainActivity.this, MainMenu2.class));
    }
    public void OpenRegister() {  //Lleva a la actividad de Menú después de autenticar las credenciales.
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

    @NonNull
    private Boolean validate() { //VALIDAR QUE LOS CAMPOS DEL LOGIN NO ESTEN VACIOS
        Boolean result = false;
        String name = username1.getText().toString();
        String password = password1.getText().toString();
        if (name.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese sus credenciales", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    public void SignIn() {
        if (validate()) {
            Login login = new Login(username1.getText().toString(), password1.getText().toString());
            Call<LoginModel> userResponseCall = ApiClient.getUserService().login(login);
            userResponseCall.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    if (response.isSuccessful()) {
                        token = response.body().getToken();
                        getSecret();
                        Open();
                        //Toast.makeText(MainActivity.this, "Iniciando Sesión...", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Verifique sus credenciales", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    private void getSecret(){
        Call<ResponseBody> call = ApiClient.getUserService().getSecret(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Iniciando Sesión...", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Verifica tus credenciales", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}