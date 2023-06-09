package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;

public class UserDAO implements UserDAOInterface {
    @Override
    public User getUserById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM User WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );

                System.out.println("User found!");
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Failed to find User");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateUserAge(int age, int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE User SET age = ? WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, age);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Age successfully updated! Reality is what you make it");
            return true;


        } catch (SQLException e) {
            System.out.println("Failed to update age");
            e.printStackTrace();
        }

        return false;
    }
}
