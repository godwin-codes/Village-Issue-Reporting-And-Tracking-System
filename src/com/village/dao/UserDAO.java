package com.village.dao;

import java.sql.*;
import com.village.model.User;
// Ensure the import path matches the actual location of DBConnection.java
import com.village.util.DBConnection;

public class UserDAO {

    public boolean registerUser(String name, String email, String password){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, 'user')")) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            return stmt.executeUpdate() > 0;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public User loginUser(String email, String password){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM users WHERE email=? AND password=?")) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
