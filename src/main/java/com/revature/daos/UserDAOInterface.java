package com.revature.daos;

import com.revature.models.User;

public interface UserDAOInterface {

    User getUserById(int id);

    boolean updateUserAge(int age, int id);

}