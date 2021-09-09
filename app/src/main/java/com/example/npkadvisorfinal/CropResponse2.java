package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropResponse2 {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Create_at")
    @Expose
    private String createAt;
    @SerializedName("CNombre")
    @Expose
    private String cNombre;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("CArea")
    @Expose
    private Double cArea;

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

    public String getCNombre() {
        return cNombre;
    }

    public void setCNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Double getCArea() {
        return cArea;
    }

    public void setCArea(Double cArea) {
        this.cArea = cArea;
    }

    @NonNull
    @Override
    public String toString() {
        return cNombre+cArea;
    }
}
