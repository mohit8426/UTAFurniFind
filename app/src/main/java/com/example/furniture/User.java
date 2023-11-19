package com.example.furniture;

// Create a User class to represent user data
public class User {
    private String name;
    private String email;

    // Required public no-argument constructor for Firestore
    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Add getters and setters as needed

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}