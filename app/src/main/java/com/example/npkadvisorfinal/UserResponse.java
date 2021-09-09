package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {

    public ArrayList<UserRequest> getUsuariosBuscados() {
        return usuariosBuscados;
    }

    public void setUsuariosBuscados(ArrayList<UserRequest> usuariosBuscados) {
        this.usuariosBuscados = usuariosBuscados;
    }

    @SerializedName("persona")
    @Expose
    private ArrayList<UserRequest> usuariosBuscados;

    @NonNull
    @Override
    public String toString() {
        return "UserResponse{" +
                "usuariosBuscados=" + usuariosBuscados +
                '}';
    }
}
