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
                        uDAO.getUserById(rs.getInt("user_id_fk")),
                        rs.getInt("user_id_fk")
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

            String sql = "INSERT INTO Pet(name, species, user_id_fk) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, pet.getName());
            ps.setString(2, pet.getSpecies());
            ps.setInt(3, pet.getUser_id_fk());

            ps.executeUpdate();

            return pet;

        } catch (SQLException e) {
            System.out.println("Failed to add pet!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Pet getPetById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM Pet WHERE pet_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            UserDAO uDAO = new UserDAO();

            if (rs.next()) {
                Pet pet = new Pet(
                        rs.getInt("pet_id"),
                        rs.getString("name"),
                        rs.getString("species"),
                        uDAO.getUserById(rs.getInt("user_id_fk")),
                        rs.getInt("user_id_fk")
                );

                return pet;
            }

        } catch (SQLException e) {
            System.out.println("Failed to find pet");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updatePet(int id, String name, String species, int user_id_fk) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE Pet SET name = ?, species = ?, user_id_fk = ? WHERE pet_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, species);
            ps.setInt(3, user_id_fk);
            ps.setInt(4, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Failed to update pet!");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePet(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM Pet WHERE pet_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Failed to delete pet");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ArrayList<String> helloWorld() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT name, species FROM Pet ORDER BY species, name ASC";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<String> pets = new ArrayList<>();

            while (rs.next()) {
                String pet = rs.getString("name") + ", " + rs.getString("species");

                pets.add(pet);

            }

            return pets;

        } catch (SQLException e) {
            System.out.println("You entered the room and no one greeted you.");
            e.printStackTrace();
        }

        return null;
    }
}