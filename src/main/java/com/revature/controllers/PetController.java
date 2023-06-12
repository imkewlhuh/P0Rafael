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
        logger.info("All Pets found!");
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

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        Pet pet = petService.getPetById(id);

        if (pet != null) {
            ctx.status(200);
            ctx.json(pet);
            logger.info("Pet found: " + pet.toString());
        } else {
            ctx.status(404);
            logger.warn("Pet not found. id given: " + id);
        }

    }

    public static void handleUpdatePet(Context ctx) {

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        Pet pet = ctx.bodyAsClass(Pet.class);

        if (petService.getPetById(id) != null) {

            boolean updatedPet = petService.updatePet(id, pet.getName(), pet.getSpecies(), pet.getUser_id_fk());

            if (updatedPet) {
                ctx.status(200);
                logger.info("Pet: " + id + " was updated to " + pet.toString());
            }

        }else {
            ctx.status(400);
            logger.warn("Could not update pet");
        }

    }

    public static void handleDeletePet(Context ctx) {

        String s = ctx.pathParam("id");
        int id;

        try {
            id = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            ctx.status(400);
            logger.warn("Invalid id: " + s);
            return;
        }

        if (petService.getPetById(id) != null) {

            boolean deletedPet = petService.deletePet(id);

            if (deletedPet) {
                ctx.status(200);
                logger.info("Pet #" + id + " was disintegrated. You will be judged for your crimes.");
            }

        } else {
            ctx.status(400);
            logger.warn("Could not delete pet");
        }

    }

    public static void helloWorld(Context ctx) {
        ArrayList<String> pets = petService.helloWorld();

        ctx.status(200);
        ctx.json(pets);
        logger.info(":D");
    }

}

