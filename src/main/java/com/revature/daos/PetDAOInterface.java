package com.revature.daos;

import com.revature.models.Pet;

import java.util.ArrayList;

public interface PetDAOInterface {

    ArrayList<Pet> getAllPets();

    Pet insertPet(Pet pet);

    Pet getPetById(int id);

    boolean updatePet(int id, String name, String species, int user_id_fk);

    boolean deletePet(int id);

    ArrayList<String> helloWorld();

}
