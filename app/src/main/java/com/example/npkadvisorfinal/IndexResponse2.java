package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndexResponse2 {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Create_at")
    @Expose
    private String createAt;
    @SerializedName("Humedad")
    @Expose
    private double Humedad;
    @SerializedName("N")
    @Expose
    private double N;
    @SerializedName("P")
    @Expose
    private double P;
    @SerializedName("K")
    @Expose
    private double K;
    @SerializedName("Ph")
    @Expose
    private double Ph;
    @SerializedName("Temp")
    @Expose
    private double Temp;

    public Double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public Double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public Double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    public Double getPh() {
        return Ph;
    }

    public void setPh(double ph) {
        Ph = ph;
    }

    public Double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Double getHumedad() {
        return Humedad;
    }

    public void setHumedad(double humedad) {
        Humedad = humedad;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    @SerializedName("__v")
    @Expose
    private Integer v;

    @NonNull
    @Override
    public String toString() {
        return "IndexResponse2{" +
                "Humedad=" + Humedad +
                ", N=" + N +
                ", P=" + P +
                ", K=" + K +
                ", Ph=" + Ph +
                ", Temp=" + Temp +
                '}';
    }
}
