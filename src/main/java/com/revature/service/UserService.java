package com.revature.service;

import com.revature.daos.UserDAOInterface;
import com.revature.models.User;

public class UserService {

    private UserDAOInterface userDao;

    public UserService(UserDAOInterface userDao) {
        this.userDao = userDao;
    }

    public User getUserById(int id) {

        if (id > 0) {
            return userDao.getUserById(id);
        } else {
            return null;
        }

    }

    public boolean updateAge(int age, int id) {

        if (age <= 0 && id <= 0) {
            return false;
        } else {
            return userDao.updateUserAge(age, id);
        }

    }
}