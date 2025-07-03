package com.inventory.service.impl;

import com.inventory.dao.AuthDao;
import com.inventory.dao.UserDao;
import com.inventory.dao.impl.AuthDaoImpl;
import com.inventory.dao.impl.UserDaoImpl;
import com.inventory.exception.InvalidInputException;
import com.inventory.exception.LoginFailedException;
import com.inventory.exception.UsernameAlreadyExistsException;
import com.inventory.model.NewUsers;
import com.inventory.model.Users;
import com.inventory.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    private final AuthDao authDao = new AuthDaoImpl();

    @Override
    public void registerUser(NewUsers newUser, String username, String password) {
        if (authDao.isUsernameExists(username)) {
            throw new UsernameAlreadyExistsException("Username already taken.");
        }

        int userId = userDao.saveUser(newUser);
        if (userId == -1) {
            throw new RuntimeException("Registration failed. Could not generate user ID.");
        }

        authDao.saveCredentials(userId, username, password);
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
