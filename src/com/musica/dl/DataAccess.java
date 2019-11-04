package com.musica.dl;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DataAccess {
    Connection connection = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public String insertData(String statement){
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(statement);
            connection.close();
            st.close();
            return "Action done";
        }
        catch (Exception e)
        {
            return "Error " + e.getMessage();
        }
    }

    public ResultSet selectData(String statement){
        ResultSet resultSet = null;
        Statement st = null;
        CachedRowSetImpl crs = null;
        try {
            connection = getConnection();
            st = connection.createStatement();
            resultSet = st.executeQuery(statement);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }
        catch (Exception e)
        {
            resultSet = null;
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
            catch (Exception e){
                e.getMessage();
            }
        }
        return crs;
    }
}
