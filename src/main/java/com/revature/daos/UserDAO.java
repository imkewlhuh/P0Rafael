package com.revature.daos;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {
    @Override
    public ArrayList<User> getAllUsers() {

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM \"User\"";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );

                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            System.out.println("Failed to find users. How lonely");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User insertUser(User user) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO \"User\"(name, age) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());

            ps.executeUpdate();

            return user;

        } catch (SQLException e) {
            System.out.println("Failed to create user!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "SELECT * FROM \"User\" WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getInt("age")
                );

                return user;
            }

        } catch (SQLException e) {
            System.out.println("Failed to find User");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateUser(int id, String name, int age) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "UPDATE \"User\" SET name = ?, age = ? WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Failed to update user");
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM \"User\" WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;

    } catch (SQLException e) {
            System.out.println("Failed to delete user");
            e.printStackTrace();
        }

        return false;
    }
}
