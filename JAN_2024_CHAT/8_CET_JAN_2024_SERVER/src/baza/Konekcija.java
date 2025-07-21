/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanja
 */
public class Konekcija {
    private static Konekcija instance;
    private Connection connection;
    private Konekcija()  {
        try {
         //   String nazivBaze = konfiguracija.Konfiguracija.getInstanca().getProperty("db_name");
           // String username = konfiguracija.Konfiguracija.getInstanca().getProperty("db_user");
            String url = "jdbc:mysql://localhost:3306/jan2024ps";//+nazivBaze;
            connection = DriverManager.getConnection(url, "root", "root");
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

    
    
    
    
}
