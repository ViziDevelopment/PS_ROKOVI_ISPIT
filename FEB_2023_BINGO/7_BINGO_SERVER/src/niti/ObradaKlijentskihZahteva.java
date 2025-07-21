/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Igra;
import tranfser.KlijentskiZahtev;
import tranfser.ServerskiOdgovor;
 
 

/**
 *
 * @author vanja
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket s;
  
    public ObradaKlijentskihZahteva(Socket s ) {
        this.s = s;
        
    }
   
    @Override
    public void run() {
        while(true){
            KlijentskiZahtev kz = primiZahtev(); //(operacija,param)
            ServerskiOdgovor so = new ServerskiOdgovor(); 
            switch (kz.getOperacija()) {
                  
                case operacije.Operacije.POGADJAJ:
                    Igra igra =  Controller.getInstance().pogadjaj((Igra) kz.getParam());
                    so.setOdgovor(igra);
                    break;
                
                default:
                    System.out.println("GRESkA");
            }
           
            posaljiOdgovor(so);
        }
        
        
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois =  new ObjectInputStream(s.getInputStream());
           
                return (KlijentskiZahtev) ois.readObject();
            
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
 
    
    
    
    
    
}
