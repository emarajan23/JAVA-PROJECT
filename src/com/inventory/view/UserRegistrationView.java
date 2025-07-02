package com.inventory.view;

import com.inventory.exception.InvalidInputException;
import com.inventory.exception.UsernameAlreadyExistsException;
import com.inventory.model.UserRole;
import com.inventory.model.Users;
import com.inventory.service.UserService;
import com.inventory.service.impl.UserServiceImpl;
import com.inventory.validation.InputValidator;

import java.util.Scanner;

public class UserRegistrationView {


    private final UserService userService = new UserServiceImpl();

    public void registerUserByRole(UserRole role){
        Scanner scan=new Scanner(System.in);

        Users users=new Users();
        users.setRole(role);

        while (true) {
            System.out.print("Enter Name: ");
            String name = scan.nextLine();
            try {
                InputValidator.validateName(name);
                users.setName(name);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }


        while (true) {
            System.out.print("Enter Email: ");
            String email = scan.nextLine();
            try {
                InputValidator.validateEmail(email);
                users.setEmail(email);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.print("Enter Contact Number: ");
            String contact = scan.nextLine();
            try {
                InputValidator.validateContactNumber(contact);
                users.setContactNumber(contact);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }


        String username;

        while (true) {
            System.out.print("Choose Username: ");
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
            System.out.print("Choose Password: ");
            password = scan.nextLine();
            try {
                InputValidator.validatePassword(password);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            userService.registerUser(users, username, password);
        } catch (UsernameAlreadyExistsException e) {
            System.out.println(e.getMessage());
            registerUserByRole(role);
        }


    }
}
