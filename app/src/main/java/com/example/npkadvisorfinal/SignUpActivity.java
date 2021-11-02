package com.example.npkadvisorfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText name;
    EditText username;
    EditText password;
    EditText passwordc;
    FloatingActionButton btn_registro;
    String mail;
    boolean flag = false;
    boolean flag1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordc = findViewById(R.id.passwordc);
        btn_registro = findViewById(R.id.btn_registro);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(createRequest());
            }
        });
    }

    public void Open() {  //ABRIR UNA NUEVA ACTIVIDADj
        startActivity(new Intent(com.example.npkadvisorfinal.SignUpActivity.this, MainActivity.class));
    }

    @NonNull
    public UserRequest createRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setNombre(name.getText().toString());
        userRequest.setUsername(username.getText().toString());
        userRequest.setPassword(password.getText().toString());
        userRequest.setPasswordC(passwordc.getText().toString());
        return userRequest;
    }

    @NonNull
    private Boolean validate() { //VALIDAR QUE LOS CAMPOS DEL LOGIN NO ESTEN VACIOS
        Boolean result = false;
        if (name.getText().toString().isEmpty() || username.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_LONG).show();
        } else {
            result = true;
        }
        return result;
    }

    public void saveUser(@NonNull UserRequest userRequest) {
        mail = username.getText().toString();
        if (validate()) {
            if ((userRequest.getPassword().equals(userRequest.getPasswordC()))) {
                Call<UserResponse> userResponseCall2 = ApiClient.getUserService().FindUser();
                userResponseCall2.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        ArrayList<UserRequest> cropResponses = response.body().getUsuariosBuscados();
                        if(response.isSuccessful()) {
                            for (int i = 0; i < cropResponses.size(); i++) {
                                if (cropResponses.get(i).getUsername().equalsIgnoreCase(mail)) {
                                    Toast.makeText(com.example.npkadvisorfinal.SignUpActivity.this, "El email ya está en uso", Toast.LENGTH_LONG).show();
                                    flag = false;
                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(com.example.npkadvisorfinal.SignUpActivity.this, "Verifique su conexión a internet", Toast.LENGTH_LONG).show();

                    }
                });

                if(flag) {
                    Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
                    userResponseCall.enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(com.example.npkadvisorfinal.SignUpActivity.this, "Registro exitoso, por favor inicie sesión", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {
                            Toast.makeText(com.example.npkadvisorfinal.SignUpActivity.this, "Falló", Toast.LENGTH_LONG).show();

                        }
                    });
                    flag = false;
                }
            }
        } else {
            Toast.makeText(com.example.npkadvisorfinal.SignUpActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
        }
    }
}