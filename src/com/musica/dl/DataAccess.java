package com.musica.dl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccess {

    public String connectionDB(){
        String status = "";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/Music?useSSL=false", "root", "password123$")) {

            if (conn != null) {
                status = "Connected to the database!";
            } else {
                status = "Failed to make connection!";
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
