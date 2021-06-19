package com.company.Models;

public class user {
    private String UserLogin;
    private String UserPassword;
    private int UserId;

    public user(String userLogin, String userPassword, int UserId) {
        UserLogin = userLogin;
        UserPassword = userPassword;
        this.UserId = UserId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public String getUserLogin() {
        return UserLogin;
    }

    public void setUserLogin(String userLogin) {
        UserLogin = userLogin;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
