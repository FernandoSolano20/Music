package com.musica.dl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String NOMBRE_ARCHIVO = "Configuration.txt";
    private static String urlMysql;
    private static String driver;
    private static String user;
    private static String password;

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() throws Exception {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LogError.getLogger().info("Error " + e.getMessage());
            throw new Exception("Error con conexion");
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(urlMysql, user, password);
        return conn;
    }

    public static ConnectionFactory getInstance() throws Exception {
        if (connectionFactory == null) {
            readFileConnection();
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    private static void readFileConnection() throws Exception {
        try {
            FileReader reader = new FileReader(NOMBRE_ARCHIVO);
            BufferedReader buffer = new BufferedReader(reader);
            String datos;
            String[] data;
            int i = 0;
            while ((datos = buffer.readLine()) != null) {
                data = datos.split(";");
                driver = data[0];
                urlMysql = data[1];
                user = data[2];
                password = data[3];
            }
            reader.close();
        } catch (IOException e) {
            LogError.getLogger().info(e.getMessage());
            throw new Exception("Error de configuracion");
        }
    }
}
