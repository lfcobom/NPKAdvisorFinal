package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRequest {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("Nombre")
    @Expose
    private String Nombre;

    @SerializedName("Username")
    @Expose
    private String username;

    @SerializedName("Password")
    @Expose
    private String password;

    @SerializedName("PasswordC")
    @Expose
    private String passwordC;

    @SerializedName("__v")
    @Expose
    private Integer v;

    @SerializedName("Create_at")
    @Expose
    private String createat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordC() {
        return passwordC;
    }

    public void setPasswordC(String passwordC) {
        this.passwordC = passwordC;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getCreateat() {
        return createat;
    }

    public void setCreateat(String createat) {
        this.createat = createat;
    }
    @NonNull
    @Override
    public String toString() {
        return "UserRequest{" +
                "id='" + id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordC='" + passwordC + '\'' +
                ", v=" + v +
                ", createat='" + createat + '\'' +
                '}';
    }
}
