package model;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.Objects;

public  class UsersDAO {
//    String JDBC_URL = "jdbc:postgresql://localhost:5432/to_do_app";
//    String DB_USER = "postgres";
//    String DB_PASS = "password";

    //Userのidとnameをデータベースから取得。
    //idはToDoの検索に使用。
    public Users getUserInfo(String name) {
        Users user = null;
        String JDBC_URL = "jdbc:postgresql://localhost:5432/to_do_app";
        String DB_USER = "postgres";
        String DB_PASS = "password";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT id, name FROM users WHERE name = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("name");
                user = new Users(id, userName);
            } else {
                int id = -1;
                String userName = "failed";
                user = new Users(id, userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public void createUser(String name) {
        String JDBC_URL = "jdbc:postgresql://localhost:5432/to_do_app";
        String DB_USER = "postgres";
        String DB_PASS = "password";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);

            Boolean result = pStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // データベースからuserNameで検索し、名前がなければ新たにUsersクラスを作り登録し、再度検索。
    public Users checkUserExist(String userName) {
        Users user = getUserInfo(userName);
        if (!Objects.equals(user.getName(), userName)) {
            createUser(userName);
            user = getUserInfo(userName);
        }
        return user;
    }
}

