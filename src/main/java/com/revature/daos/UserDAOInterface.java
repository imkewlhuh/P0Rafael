package com.revature.daos;

import com.revature.models.Pet;
import com.revature.models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    ArrayList<User> getAllUsers();

    User insertUser(User user);

    User getUserById(int id);

    boolean updateUser(int id, String name, int age);

    boolean deleteUser(int id);

}