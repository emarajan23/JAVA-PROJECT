package com.inventory.model;

public class Users {
    private final int userId;
    private final String name;
    private final String email;
    private final String contactNumber;
    private final UserRole role;

    public Users(int userId, String name, String email, String contactNumber, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public UserRole getRole() {
        return role;
    }
}
