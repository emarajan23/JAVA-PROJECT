package com.inventory.service.impl;

//import com.inventory.dao.AuthDao;
import com.inventory.dao.AuthDao;
import com.inventory.dao.impl.AuthDaoImpl;
import com.inventory.dao.impl.UserDaoImpl;
import com.inventory.dao.UserDao;
//import com.inventory.dao.impl.AuthDaoImpl;


import com.inventory.exception.InvalidInputException;
import com.inventory.exception.LoginFailedException;
import com.inventory.exception.UsernameAlreadyExistsException;
import com.inventory.model.Users;
import com.inventory.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private final AuthDao authDao = new AuthDaoImpl();

    @Override
    public void registerUser(Users user, String username, String password) {
        if (authDao.isUsernameExists(username)) {
            throw new UsernameAlreadyExistsException("Username already taken.");
        }

        int userId = userDao.saveUser(user);
        if (userId != -1) {
            authDao.saveCredentials(userId, username, password);
            System.out.println("Registered successfully with ID: " + userId);
        } else {
            System.out.println("Registration failed.");
        }
    }

    @Override
    public Users login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            throw new InvalidInputException("Username and password cannot be empty.");
        }

        Users user = authDao.fetchUserByCredentials(username, password);
        if (user == null) {
            throw new LoginFailedException("Invalid username or password.");
        }

        return user;
    }

}
