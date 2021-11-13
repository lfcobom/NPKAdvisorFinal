package com.example.npkadvisorfinal;

public class LoginModel {

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String Username;
    private String token;
}

