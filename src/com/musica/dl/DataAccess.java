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

    public int insertIntoData(String statement){
        int id = 0;
        try {
            connection = getConnection();
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    Statement.RETURN_GENERATED_KEYS);
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(statement,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            connection.close();
            st.close();
            return id;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public boolean insertData(String statement){
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(statement,Statement.RETURN_GENERATED_KEYS);
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
