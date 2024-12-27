package com.example.quizeapk;

public class User {
    public String username;
    public String email;
    public String city;
    public String phone;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String username, String email, String city, String phone) {
        this.username = username;
        this.email = email;
        this.city = city;
        this.phone = phone;
    }
}
