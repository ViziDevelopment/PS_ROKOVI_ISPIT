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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
import model.Korisnik;
import model.Rec;
import operacije.Operacije;
 
 
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
 
 

/**
 *
 * @author vanja
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket s;
   
    private boolean kraj=false;
    public ObradaKlijentskihZahteva(Socket s ) {
        this.s = s; 
    } 
    @Override
    public void run() {
        while(!kraj){
            KlijentskiZahtev kz = primiZahtev(); 
            ServerskiOdgovor so = new ServerskiOdgovor(); 
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                   
                    try {
                         Korisnik ulogovani = Controller.getInstance().login((Korisnik)kz.getParam());
                          so.setOdgovor(ulogovani);
                    } catch (SQLException ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    break;
                case Operacije.LOGOUT:
                    boolean uspeh =  Controller.getInstance().logout( (Korisnik)kz.getParam());
                     so.setOdgovor(uspeh);
                    
                    break;
                case Operacije.UCITAJ_RECNIK:
                    List<Rec> lista = Controller.getInstance().ucitajRecnik();
                    so.setOdgovor(lista);
                    System.out.println(lista);
                    break;
                case Operacije.PRETRAGA:
                    Rec rezultat = Controller.getInstance().pretraga((String)kz.getParam());
                    so.setOdgovor(rezultat);
                    break;
                case Operacije.UNESI:
                    
                    uspeh= Controller.getInstance().unesi((Rec)kz.getParam());
                    so.setOdgovor(uspeh);
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
