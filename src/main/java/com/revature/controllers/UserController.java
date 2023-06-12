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
        ArrayList<User> users = userService.getAllUsers();

        ctx.status(200);
        ctx.json(users);
        logger.info("Users found! Now it's a party");
    }

    public static void handleNewUser(Context ctx) {

        User user = ctx.bodyAsClass(User.class);

        User newUser = userService.createNewUser(user);

        if (newUser != null) {
            ctx.status(201);
            ctx.json(newUser);
            logger.info("User added: " + newUser.toString());
        } else {
            ctx.status(400);
            logger.warn("Failed to add user");
        }

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

    public static void handleUpdateUser(Context ctx) {

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        User user = ctx.bodyAsClass(User.class);

        if (userService.getUserById(id) != null) {

            boolean updatedUser = userService.updateUser(id, user.getName(), user.getAge());

            if (updatedUser) {
                ctx.status(200);
                logger.info("User: " + id + " was updated to " + user.toString() + " Reality is what you make it");
            }

        }else {
            ctx.status(400);
            logger.warn("Could not update user");
        }

    }

    public static void handleDeleteUser(Context ctx) {

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        if (userService.getUserById(id) != null) {

            boolean deletedUser = userService.deleteUser(id);

            if (deletedUser) {
                ctx.status(200);
                logger.info("User #" + id + " was deleted. Press F to pay respects");
            }

        } else {
            ctx.status(400);
            logger.warn("Could not delete user");
        }

    }

}