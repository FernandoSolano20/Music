package com.musica.dl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://127.0.0.1:3306/Music?useSSL=false&allowPublicKeyRetrieval=true";
    String dbUser = "root";
    String dbPwd = "password123$";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() throws Exception {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error con conexion");
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static ConnectionFactory getInstance() throws Exception {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
