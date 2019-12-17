package com.musica.dl;

import com.musica.bl.Gender.Gender;
import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DataAccess {
    Connection connection = null;

    private Connection getConnection() throws Exception {
        try {
            Connection conn;
            conn = ConnectionFactory.getInstance().getConnection();
            return conn;
        }
        catch (Exception e){
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error en base de datos");
        }
    }

    public int insertIntoData(String statement) throws Exception {
        int id = -1;
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
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error en base de datos");
        }
    }

    public boolean insertData(String statement) throws Exception {
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
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error en base de datos");
        }
    }

    public ResultSet selectData(String statement) throws Exception {
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
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error en base de datos");
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
                LogError.getLogger().info("Error " + e.getMessage());
                throw new Exception("Error en base de datos");
            }
        }
        return crs;
        ///^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,10}$/
    }
}
