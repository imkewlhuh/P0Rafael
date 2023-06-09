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

    public Pet getPetById(int id) {

        if (id > 0) {
            return petDao.getPetById(id);
        } else {
            return null;
        }

    }

    public boolean updatePet(int id, String name, String species, int user_id_fk) {

        if (id <= 0 || user_id_fk <= 0 || name == null || name == "" || species == null || species == "") {
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

}