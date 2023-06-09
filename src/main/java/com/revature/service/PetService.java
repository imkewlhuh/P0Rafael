package com.revature.service;

import com.revature.daos.PetDAO;
import com.revature.daos.PetDAOInterface;
import com.revature.models.Pet;

import java.util.ArrayList;

public class PetService {

    private final PetDAOInterface petDao = new PetDAO();

    public ArrayList<Pet> getAllPets() {
        return petDao.getAllPets();
    }

    public Pet addNewPet(Pet pet) {
        return petDao.insertPet(pet);
    }

}