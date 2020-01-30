package com.example.app_banhang.model;

public class User {
    private String username, email;
    private String id;
    public User(String id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
}
