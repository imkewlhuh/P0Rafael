package com.revature.daos;

import com.revature.models.Pet;

import java.util.ArrayList;

public interface PetDAOInterface {

    ArrayList<Pet> getAllPets();

    Pet insertPet(Pet pet);

}
