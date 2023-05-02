package com.example.wsrfood.api.user;

public class SignUpUserBody {
    private String email;
    private String password;
    private String fullname;
    private String phone;

    public SignUpUserBody(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.fullname = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
