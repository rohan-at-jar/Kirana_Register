package com.kirana.Kirana_Register.dto;

public class UserDetailsDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public UserDetailsDTO() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
// Constructors, getters, and setters

    // Omitted for brevity
}
