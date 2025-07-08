package com.inventory.controller;

import com.inventory.exception.InvalidInputException;
import com.inventory.exception.UsernameAlreadyExistsException;
import com.inventory.model.User;
import com.inventory.model.UserRole;
import com.inventory.service.UserService;
import com.inventory.service.impl.UserServiceImpl;
import com.inventory.validation.InputValidator;
import com.inventory.view.UserRegistrationView;

public class UserRegistrationController {

    private final UserRegistrationView view = new UserRegistrationView();
    private final UserService userService = new UserServiceImpl();

    public void handleRegistration(UserRole role) {
        String name;
        while (true) {
            name = view.getNameInput();
            try {
                InputValidator.validateName(name);
                break;
            } catch (InvalidInputException e) {
                view.showMessage(e.getMessage());
            }
        }

        String email;
        while (true) {
            email = view.getEmailInput();
            try {
                InputValidator.validateEmail(email);
                break;
            } catch (InvalidInputException e) {
                view.showMessage(e.getMessage());
            }
        }

        String contact;
        while (true) {
            contact = view.getContactInput();
            try {
                InputValidator.validateContactNumber(contact);
                break;
            } catch (InvalidInputException e) {
                view.showMessage(e.getMessage());
            }
        }

        String username;
        while (true) {
            username = view.getUsernameInput();
            try {
                InputValidator.validateUsername(username);
                break;
            } catch (InvalidInputException e) {
                view.showMessage(e.getMessage());
            }
        }

        String password;
        while (true) {
            password = view.getPasswordInput();
            try {
                InputValidator.validatePassword(password);
                break;
            } catch (InvalidInputException e) {
                view.showMessage(e.getMessage());
            }
        }

        User newUser = new User(name, email, contact, role);

        try {
            userService.registerUser(newUser, username, password);
            view.showMessage("User registered successfully.");
        } catch (UsernameAlreadyExistsException e) {
            view.showMessage(e.getMessage());
            handleRegistration(role);
        }
    }
}
