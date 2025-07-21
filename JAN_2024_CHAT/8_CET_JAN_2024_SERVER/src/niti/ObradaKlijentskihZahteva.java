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
import model.Poruka;
import operacija.Operacija;
import static operacija.Operacija.LOGIN;
 
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
 
 

/**
 *
 * @author vanja
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket s;
    private Korisnik ulogovani=null;
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
                case LOGIN:
                    try {
                        Korisnik k = Controller.getInstance().login((Korisnik)kz.getParam());
                        if(k!=null){
                            ulogovani=k;
                        }
                        so.setOdgovor(k);
                    } catch (SQLException ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                break;

                case Operacija.LOGOUT:
                    Controller.getInstance().logout((Korisnik)kz.getParam());
                    kraj=true;
                {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;
                case Operacija.POSALJI_ULOGOVANE_KORISNIKE:
                    Controller.getInstance().posaljiUlogovaneKorisnike();
                    
                    break;
                    
                case Operacija.UCITAJ_PORUKE:
                   List<Poruka> poruke;
                try {
                    poruke = Controller.getInstance().ucitajPoruke((Korisnik)kz.getParam());
                    so.setOperacija(Operacija.UCITAJ_PORUKE);
                    so.setOdgovor(poruke);
                } catch (SQLException ex) {
                    Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
                    break;
                    
                 case Operacija.KREIRAJ_PORUKU:
                     Poruka  p = (Poruka) kz.getParam();
                     Controller.getInstance().kreirajPoruku(p);
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

    public Korisnik getUlogovani() {
        return ulogovani;
    }

    public void odjaviKorisnika() {
        System.out.println("OKZ-odjava");
        ServerskiOdgovor so = new ServerskiOdgovor(); 
        so.setOperacija(Operacija.LOGOUT);
        posaljiOdgovor(so); 
    }

    public void posaljiUlogovaneKorisnike(List<Korisnik> korisnici) {
        ServerskiOdgovor so = new ServerskiOdgovor(); 
        so.setOdgovor(korisnici);
        so.setOperacija(Operacija.POSALJI_ULOGOVANE_KORISNIKE);
        posaljiOdgovor(so); 
    }

 
 
    
    
    
    
    
}
