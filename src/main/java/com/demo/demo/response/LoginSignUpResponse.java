package com.demo.demo.response;

import com.demo.demo.entity.User;

public class LoginSignUpResponse extends GenericResponse{

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
