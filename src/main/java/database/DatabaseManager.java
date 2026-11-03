package database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseManager {

    private final Properties prop;

//    private String baseUrl = "jdbc:mysql://localhost:3306/";
//    private String user = "root";
//    private String pass = "root";

    public DatabaseManager() {
        prop = new Properties();
        loadProperties();
    }


    public Connection getConnection(String nomeDatabase) {
//        String url = baseUrl + nomeDatabase;
        try {
            IO.println(getUrl());
            Connection conn = DriverManager.getConnection(getUrl() + nomeDatabase, getUsername(), getPassword());
            IO.println("connessione al db " + nomeDatabase + " riuscita");
            return conn;
        } catch (SQLException e) {
            IO.println("Qualcosa è andato storto");
            e.printStackTrace();
            return null;
        }
    }

    private void loadProperties() {

        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("app.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "File app.properties non trovato!"
                );
            }

            prop.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Errore nel caricamento delle configurazioni", e
            );
        }
    }

    private String getUrl() {
        return prop.getProperty("db.baseUrl");
    }

    private String getUsername() {
        return prop.getProperty("db.user");
    }

    private String getPassword() {
        return prop.getProperty("db.pass");
    }
}
