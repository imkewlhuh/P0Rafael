package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class UserController {

    private static final UserService userService = new UserService(new UserDAO());

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public static void handleGetAllUsers(Context ctx) {
        ctx.status(500);
    }

    public static void handleNewUser(Context ctx) {
        ctx.status(400);
    }

    public static void handleGetUser(Context ctx) {

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        User user = userService.getUserById(id);

        if (user != null) {
            ctx.status(200);
            ctx.json(user);
            logger.info("User found: " + user.toString());
        } else {
            ctx.status(404);
            logger.warn("User not found. id given: " + id);
        }

    }

    public static void handleUpdateAge(Context ctx) {

        User user = ctx.bodyAsClass(User.class);

        boolean updatedUser = userService.updateAge(user.getAge(), user.getUser_id());

        if (updatedUser) {
            ctx.status(200);
            logger.info("User: " + user.getUser_id() + " was updated to the age of " + user.getAge());
        } else {
            ctx.status(400);
        }

    }

    public static void handleDeleteUser(Context ctx) {
        ctx.status(500);
    }

}