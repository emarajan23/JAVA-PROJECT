package com.inventory.model;

public class UserEntity {
    private final int userId;
    private final String name;
    private final String email;
    private final String contactNumber;
    private final UserRole role;

    public UserEntity(int userId, String name, String email, String contactNumber, UserRole role) {
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
