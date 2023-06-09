package com.revature.controllers;

import com.revature.models.Pet;
import com.revature.service.PetService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class PetController {

    private static final PetService petService = new PetService();

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    public static void handleGetAllPets(Context ctx) {
        ArrayList<Pet> pets = petService.getAllPets();

        ctx.status(200);
        ctx.json(pets);
    }

    public static void handleAddPet(Context ctx) {

        Pet pet = ctx.bodyAsClass(Pet.class);

        Pet newPet = petService.addNewPet(pet);

        if (newPet != null) {
            ctx.status(201);
            ctx.json(newPet);
            logger.info("Pet added: " + newPet.toString());
        } else {
            ctx.status(400);
            logger.warn("Failed to add pet :(");
        }

    }

    public static void handleGetPet(Context ctx) {
        ctx.result("Found pet");
    }

    public static void handleUpdatePet(Context ctx) {
        ctx.result("Pet updated");
    }

    public static void handleDeletePet(Context ctx) {
        ctx.result("Pet removed");
    }

}

