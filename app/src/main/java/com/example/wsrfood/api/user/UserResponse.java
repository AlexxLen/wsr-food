package com.example.wsrfood.api.user;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("token")
    private String token;

    public UserResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
