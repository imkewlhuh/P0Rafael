package com.revature.daos;

import com.revature.models.Pet;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class PetDAO implements PetDAOInterface {


    @Override
    public ArrayList<Pet> getAllPets() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM Pet";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Pet> pets = new ArrayList<>();

            UserDAO uDAO = new UserDAO();

            while (rs.next()) {
                Pet pet = new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        uDAO.getUserById(rs.getInt("user_id_fk"))
                );

                pets.add(pet);

            }

            return pets;

        } catch (SQLException e) {
            System.out.println("Failed to fetch pets");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Pet insertPet(Pet pet) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO Pet (name, species, user_id_fk) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getSpecies());
            ps.setInt(3, pet.getUser_id_fk());

            ps.execute();

            return pet;

        } catch (SQLException e) {
            System.out.println("Failed to add pet!");
            e.printStackTrace();
        }

        return null;
    }
}