package com.musica.dl;

import java.sql.*;

public class DataAccess {
    private Connection connection = null;
    public Connection connectionDB(){
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/Music?useSSL=false", "root", "password123$")) {

            if (conn != null) {
                connection = conn;
            } else {
                connection = null;
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public String insertData(String statement){
        try {
            Statement st = connection.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(statement);
            st.close();
            return "Action done";
        }
        catch (Exception e)
        {
            return "Error";
        }
    }

    public ResultSet selectData(String statement){
        ResultSet resultSet = null;
        try {
            Statement st = connection.createStatement();

            resultSet = st.executeQuery(statement);
            st.close();
        }
        catch (Exception e)
        {
            resultSet = null;
        }
        return resultSet;
    }
}
