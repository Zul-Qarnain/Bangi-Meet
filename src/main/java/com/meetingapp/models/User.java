package com.meetingapp.models;

public class User {
    private String username;
    private String email;
    private String name;

    public User() {
    }

    public User(String username, String email, String name) {
        this.username = username;
        this.email = email;
        this.name = name;
    }

    public User(String identifier) {
        // Assuming identifier can be either username or email
        if (identifier.contains("@")) {
            this.email = identifier;
        } else {
            this.username = identifier;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
