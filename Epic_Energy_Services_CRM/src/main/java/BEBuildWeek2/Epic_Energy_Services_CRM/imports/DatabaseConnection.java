package BEBuildWeek2.Epic_Energy_Services_CRM.imports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    private static final String PROPERTIES_FILE_PATH = "env.properties";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fis);
            System.out.println("connection ok");
        } catch (IOException e) {
            // Gestisci l'eccezione appropriatamente
            e.printStackTrace();
        }

        String username = properties.getProperty("PG_USERNAME");
        String password = properties.getProperty("PG_PW");
        String dbName = properties.getProperty("PG_DB");

        String url = DB_URL + dbName;

        return DriverManager.getConnection(url, username, password);
    }

}