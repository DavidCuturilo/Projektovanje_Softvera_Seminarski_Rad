/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import main.ServerEnvVariables;

/**
 *
 * @author David
 */
public class DBConnectionFactory {

    private Connection conn;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {
    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, IOException {
        if (conn == null || conn.isClosed()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(ServerEnvVariables.CONFIG_FILE_PATH));
                String db_host = properties.getProperty(ServerEnvVariables.DB_HOST);
                String db_port = properties.getProperty(ServerEnvVariables.DB_PORT);
                String db_name = properties.getProperty(ServerEnvVariables.DB_NAME);
                String user = properties.getProperty(ServerEnvVariables.DB_USERNAME);
                String pass = properties.getProperty(ServerEnvVariables.DB_PASSWORD);
                conn = DriverManager.getConnection("jdbc:mysql://" + db_host + ":" + db_port + "/" + db_name + "?serverTimezone=Europe/Prague&useSSL=false", user, pass);
            } catch (SQLException ex) {
                System.out.println("Error while establishing database connection.");
                throw ex;
            }
        }
        return conn;
    }

    public void testConnection(String dbHost, String dbPort, String dbName, String dbUsername, String dbPassword) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?serverTimezone=Europe/Prague&useSSL=false", dbUsername, dbPassword);
        connection.close();
    }   
}
