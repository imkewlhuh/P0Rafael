package com.revature.service;

import com.revature.daos.PetDAO;
import com.revature.daos.PetDAOInterface;
import com.revature.models.Pet;

import java.util.ArrayList;
import java.util.Objects;

public class PetService {

    private final PetDAOInterface petDao = new PetDAO();

    public ArrayList<Pet> getAllPets() {
        return petDao.getAllPets();
    }

    public Pet addNewPet(Pet pet) {
        if (Objects.equals(pet.getName(), "") || Objects.equals(pet.getSpecies(), "") || pet.getUser_id_fk() <= 0) {
            return null;
        } else {
            return petDao.insertPet(pet);
        }
    }

    public Pet getPetById(int id) {

        if (id > 0) {
            return petDao.getPetById(id);
        } else {
            return null;
        }

    }

    public boolean updatePet(int id, String name, String species, int user_id_fk) {

        if (id <= 0 || user_id_fk <= 0 || name == null || name.equals("") || species == null || species.equals("")) {
            return false;
        } else {
            return petDao.updatePet(id, name, species, user_id_fk);
        }

    }

    public boolean deletePet(int id) {

        if (id > 0) {
            return petDao.deletePet(id);
        } else {
            return false;
        }

    }

    public ArrayList<String> helloWorld() {
        return petDao.helloWorld();
    }

}