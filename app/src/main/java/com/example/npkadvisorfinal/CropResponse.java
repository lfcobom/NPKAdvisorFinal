package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CropResponse {

    @SerializedName("cultivosBuscados")
    @Expose
    private ArrayList<CropResponse2> cultivosBuscados;

    public ArrayList<CropResponse2> getCultivosBuscados() {
        return cultivosBuscados;
    }

    public void setCultivosBuscados(ArrayList<CropResponse2> cultivosBuscados) {
        this.cultivosBuscados = cultivosBuscados;
    }

    @NonNull
    @Override
    public String toString() {
        return "cultivosBuscados{" +
                "cultivosBuscados=" + cultivosBuscados +
                '}';
    }
}








