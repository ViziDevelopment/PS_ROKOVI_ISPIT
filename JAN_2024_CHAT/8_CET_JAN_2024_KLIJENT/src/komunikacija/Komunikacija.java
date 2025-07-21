/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import model.Korisnik;
import model.Poruka;
import operacija.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author vanja
 */
public class Komunikacija extends Thread{
    
    private Socket s;
    private static Komunikacija instance;

    private Komunikacija() {
        try {
            s = new Socket("localhost",9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static Komunikacija getInstance(){
         if(instance==null){
             instance = new Komunikacija();
         }
         return instance;
     }

    @Override
    public void run() {
         while(true){
             ServerskiOdgovor so = primiOdgovor();
             System.out.println(so);
             switch(so.getOperacija()){
                 case Operacija.LOGOUT:
                     kontroler.Kontroler.getInstance().zatvoriKlijentskuFormu();
                    break;
                    
                 case Operacija.POSALJI_ULOGOVANE_KORISNIKE:
                      List<Korisnik> korisnici= (List<Korisnik>) so.getOdgovor();
                      System.out.println(korisnici);
                      kontroler.Kontroler.getInstance().osveziTabelu(korisnici);
                    break;
                    
                    
                 case Operacija.UCITAJ_PORUKE:
                     System.out.println("");
                      List<Poruka> poruke= (List<Poruka>) so.getOdgovor();
                      System.out.println(poruke);
                      kontroler.Kontroler.getInstance().prikaziPoruke(poruke);
                    break;
                  default: System.out.println("GRESKA");
             }
             
         }
        
    }
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     public ServerskiOdgovor primiOdgovor(){
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
             oos.writeObject(kz);
             oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }

    public Socket getS() {
        return s;
    }
    
    
}
