package com.village.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.village.model.Issue;
// Update the import path to the correct location of DBConnection
import com.village.util.DBConnection;

public class IssueDAO {

    public boolean reportIssue(int userId, String title, String description, String category){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO issues (title, description, category, status, reported_by) VALUES (?, ?, ?, 'Pending', ?)")) {

            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setInt(4, userId);
            return stmt.executeUpdate() > 0;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStatus(int issueId, String status){
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE issues SET status=? WHERE id=?")) {

            stmt.setString(1, status);
            stmt.setInt(2, issueId);
            return stmt.executeUpdate() > 0;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Issue> getUserIssues(int userId){
        List<Issue> issues = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM issues WHERE reported_by=?")) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                issues.add(new Issue(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getInt("reported_by"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return issues;
    }

    public List<Issue> getAllIssues(){
        List<Issue> issues = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM issues")) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                issues.add(new Issue(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("status"),
                        rs.getInt("reported_by"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return issues;
    }
}
