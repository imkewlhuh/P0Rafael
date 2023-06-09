package com.revature.service;

import com.revature.daos.UserDAOInterface;
import com.revature.models.User;

import java.util.ArrayList;

public class UserService {

    private UserDAOInterface userDao;

    public UserService(UserDAOInterface userDao) {
        this.userDao = userDao;
    }

    public ArrayList<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User createNewUser(User user) {
        return userDao.insertUser(user);
    }

    public User getUserById(int id) {

        if (id > 0) {
            return userDao.getUserById(id);
        } else {
            return null;
        }

    }

    public boolean updateUser(int id, String name, int age) {

        if (age <= 0 || id <= 0 || name == null || name == "") {
            return false;
        } else {
            return userDao.updateUser(id, name, age);
        }

    }

    public boolean deleteUser(int id) {

        if (id > 0) {
            return userDao.deleteUser(id);
        } else {
            return false;
        }

    }

}