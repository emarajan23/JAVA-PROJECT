package com.inventory.model;

public class User {

    private String name;
    private String email;
    private String contactNumber;
    private UserRole role;


   public User(String name, String email, String contactNumber, UserRole role){
        this.name=name;
        this.email=email;
        this.contactNumber=contactNumber;
        this.role=role;

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
