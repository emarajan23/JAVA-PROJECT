package com.inventory.view;

import com.inventory.exception.InvalidInputException;
import com.inventory.exception.LoginFailedException;
import com.inventory.model.UserRole;
import com.inventory.model.Users;
import com.inventory.service.UserService;
import com.inventory.service.impl.UserServiceImpl;
import com.inventory.validation.InputValidator;

import java.util.Scanner;

public class UserLoginView {

    private final UserService userService = new UserServiceImpl();

    public Users loginByRole(UserRole role) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n " + role + " Login");

        String username;
        while (true) {
            System.out.print("Enter Username: ");
            username = scan.nextLine();
            try {
                InputValidator.validateUsername(username);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scan.nextLine();
            try {
                InputValidator.validatePassword(password);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Users user = userService.login(username, password);

            if (user.getRole() != role) {
                System.out.println("Access denied. You are not a " + role);
                return null;
            }

            System.out.println("Login successful. Welcome " + user.getName());
            return user;

        } catch (LoginFailedException | InvalidInputException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
