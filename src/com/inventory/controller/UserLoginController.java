package com.inventory.controller;

import com.inventory.exception.InvalidInputException;
import com.inventory.exception.LoginFailedException;
import com.inventory.model.UserRole;
import com.inventory.model.Users;
import com.inventory.service.UserService;
import com.inventory.service.impl.UserServiceImpl;
import com.inventory.validation.InputValidator;
import com.inventory.view.UserLoginView;

public class UserLoginController {

    private final UserLoginView view = new UserLoginView();
    private final UserService userService = new UserServiceImpl();

    public Users handleLogin(UserRole role) {
        view.showLoginTitle(role.name());

        String username;
        while (true) {
            username = view.getUsernameInput();
            try {
                InputValidator.validateUsername(username);
                break;
            } catch (InvalidInputException e) {
                view.showError(e.getMessage());
            }
        }

        String password;
        while (true) {
            password = view.getPasswordInput();
            try {
                InputValidator.validatePassword(password);
                break;
            } catch (InvalidInputException e) {
                view.showError(e.getMessage());
            }
        }

        try {
            Users user = userService.login(username, password);

            if (user.getRole() != role) {
                view.showAccessDenied(role.name());
                return null;
            }

            view.showSuccess(user.getName());
            return user;

        } catch (LoginFailedException | InvalidInputException e) {
            view.showError(e.getMessage());
            return null;
        }
    }
}
