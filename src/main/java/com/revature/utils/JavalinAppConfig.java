package com.revature.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.PetController;
import com.revature.controllers.UserController;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {

    Gson gson = new GsonBuilder().create();

    JsonMapper gsonMapper = new JsonMapper() {

        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }

        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return  gson.toJson(obj, type);
        }

    };

    private static final Logger logger = LoggerFactory.getLogger(JavalinAppConfig.class);

    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))
            .before(ctx -> logger.info(ctx.method() + " Request was sent to: " + ctx.fullUrl()))

            .routes(() -> {

                path("pets", () -> {
                    get(PetController::handleGetAllPets);
                    post(PetController::handleAddPet);

                    path("{id}", () -> {
                        get(PetController::handleGetPet);
                        put(PetController::handleUpdatePet);
                        delete(PetController::handleDeletePet);
                    });

                });

                path("user", () -> {
                    get(UserController::handleGetAllUsers);
                    post(UserController::handleNewUser);

                    path("{id}", () -> {
                        get(UserController::handleGetUser);
                        put(UserController::handleUpdateUser);
                        delete(UserController::handleDeleteUser);
                    });

                });

            });

    public void start(int port) { app.start(port); }
}
