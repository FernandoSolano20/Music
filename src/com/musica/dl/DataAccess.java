package com.musica.dl;

import com.musica.bl.Gender.Gender;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;
import java.util.ArrayList;

public class DataAccess {
    Connection connection = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public boolean insertData(String statement){
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(statement);
            connection.close();
            st.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
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
        ///^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,10}$/
    }
}
