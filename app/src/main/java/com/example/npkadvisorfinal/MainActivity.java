package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText username1;
    EditText password1;
    ImageView start_user;
    TextView register;

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
                Open();
                if (validate()) {
                    SignIn(Credenciales());
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

    @NonNull
    public UserRequest Credenciales() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username1.getText().toString());
        userRequest.setPassword(password1.getText().toString());
        return userRequest;
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

    public void SignIn(UserRequest userRequest) {
        if (validate()) {
            Call<UserResponse> userResponseCall = ApiClient.getUserService().SignIn(userRequest);
            userResponseCall.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Iniciando Sesión...", Toast.LENGTH_LONG).show();
                        Open();
                    } else {
                        Toast.makeText(MainActivity.this, "Verifique su conexión a Internet", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_LONG).show();

                }
            });
        }
    }
}