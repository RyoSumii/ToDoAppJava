package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThingsDAO {
    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/to_do_app";
    private final String DB_USER = "postgres";
    private final String DB_PASS = "password";

    public ArrayList<Things> showAll(int userID) {
        ResultSet rs = null;
        ArrayList<Things> toDoList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * FROM things where user_id = ? ORDER BY id";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, userID);

            rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String thing = rs.getString("thing");
                String timeLimit = rs.getString("timelimit");
                int processed = rs.getInt("processed");

                Things toDoInfo = new Things(id, user_id, thing, timeLimit, processed);
                toDoList.add(toDoInfo);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  toDoList;
    }

    public void insertToDo(int user_id, String thing, String deadLine) {
         try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO things(user_id, thing, timelimit, processed) VALUES (?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, user_id);
             pStmt.setString(2, thing);
             pStmt.setString(3, deadLine);
             pStmt.setInt(4, 0);

            Boolean result = pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToDo(int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "DELETE FROM things WHERE id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);

            Boolean result = pStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markAsProcessed(int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE things SET processed = ? WHERE id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, 1);
            pStmt.setInt(2, id);

            Boolean result = pStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateToDo(String thing, String limeLimit, int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "UPDATE things SET thing = ?, timelimit = ? WHERE id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, thing);
            pStmt.setString(2, limeLimit);
            pStmt.setInt(3, id);

            Boolean result = pStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Things findToDoByID(int thingID) {
        Things things = null;
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT * from things WHERE id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, thingID);

            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int user_id = rs.getInt("user_id");
                String thing = rs.getString("thing");
                String timeLimit = rs.getString("timelimit");
                int processed = rs.getInt("processed");

                Things toDoInfo = new Things(id, user_id, thing, timeLimit, processed);
                return toDoInfo;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return things;
    }

}
