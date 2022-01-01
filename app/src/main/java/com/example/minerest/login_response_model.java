package com.example.minerest;

public class login_response_model {
    String message;
    String token;

    public login_response_model(String message,String token) {
        this.message = message;
        this.token = token;
    }


    public login_response_model() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
