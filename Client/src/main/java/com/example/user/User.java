package com.example.user;

public class User {
    private String login;
    private String password;
    private String isAdmin;

    public User(String login, String password, String isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmin(String admin) {
        isAdmin = admin;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getAdmin() {
        return isAdmin;
    }
}
