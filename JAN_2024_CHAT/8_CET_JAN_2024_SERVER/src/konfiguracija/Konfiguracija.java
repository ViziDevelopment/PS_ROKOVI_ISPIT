/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanja
 */
public class Konfiguracija {
    private static Konfiguracija instanca;
    private Properties konfiguracija;
    String putanja = "C:\\Users\\vanja\\Documents\\NetBeansProjects"
            + "\\8_CET_JAN_2024_SERVER\\config\\app.config";
    
    private Konfiguracija() throws FileNotFoundException, IOException {
        konfiguracija = new Properties(); 
        File configFile = new File(putanja);
        if(!configFile.exists()){
            configFile.getParentFile().mkdirs();            
            konfiguracija.setProperty("max_broj_klijenata", 5+"");
            sacuvajIzmene();
        }else{
            //max_br_kl=5
            //db_name="jan2024"
            //db_username="root"
            //db_password="root"
            //port=9000
            FileInputStream fis = new FileInputStream(configFile);

            try {
                konfiguracija.load(fis);
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    public static Konfiguracija getInstanca() throws FileNotFoundException, IOException {
        if (instanca == null) {
            instanca = new Konfiguracija();
        }
        return instanca;
    }
    public String getProperty(String key) {
        return konfiguracija.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konfiguracija.setProperty(key, value);
    }

    public void sacuvajIzmene() throws IOException {
        try {
            konfiguracija.store(new FileOutputStream(putanja), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
