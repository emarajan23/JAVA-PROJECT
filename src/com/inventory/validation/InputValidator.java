package com.inventory.validation;

import com.inventory.exception.InvalidInputException;

public class InputValidator {
    public static void validateName(String name) {
        if (name == null || !name.matches("^[A-Za-z ]{2,50}$")) {
            throw new InvalidInputException("Name must contain only letters and spaces (2–50 chars).");
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !email.matches("^\\S+@\\S+\\.\\S+$")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }

    public static void validateContactNumber(String contact) {
        if (contact == null || !contact.matches("\\d{10}")) {
            throw new InvalidInputException("Contact number must be exactly 10 digits.");
        }
    }

    public static void validateUsername(String username) {
        if (username == null || username.length() < 6) {
            throw new InvalidInputException("Username must be at least 6 characters long.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new InvalidInputException("Password must be at least 6 characters long.");
        }
    }
}
