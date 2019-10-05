package com.demo.demo.request;

public class LoginRequest {
    private String email;
    private String password;
    private String social_user_id;

    public LoginRequest(String email, String password, String social_user_id) {
        this.email = email;
        this.password = password;
        this.social_user_id = social_user_id;
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

    public String getSocial_user_id() {
        return social_user_id;
    }

    public void setSocial_user_id(String social_user_id) {
        this.social_user_id = social_user_id;
    }
}
