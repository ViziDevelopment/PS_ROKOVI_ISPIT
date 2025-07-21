/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Angazovanje;
import model.Korisnik;
import model.Predmet;
import model.Profesor;
 
import operacije.Operacije;
import tranfser.KlijentskiZahtev;
import tranfser.ServerskiOdgovor;
 
 

/**
 *
 * @author vanja
 */
public class ObradaKlijentskihZahteva extends Thread {
    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }
   
    @Override
    public void run() {
        while(true){
            KlijentskiZahtev kz = primiZahtev(); //(operacija,param)
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch (kz.getOperacija()) {
                 case Operacije.LOGIN:
                   
                     Korisnik k = Controller.getInstance().login((Korisnik)kz.getParam());
                     so.setOdgovor(k);
                    break;
                case Operacije.DODAJ_PROF:
                   
                     Profesor p = Controller.getInstance().unesiProf((Profesor)kz.getParam());
                     so.setOdgovor(p);
                    break;
                case Operacije.DODAJ_PREDMET:
                   
                     boolean uspesno = Controller.getInstance().unesiPredmet((Predmet)kz.getParam());
                     so.setOdgovor(uspesno);
                    break;    
                case Operacije.UCITAJ_PROFESORE:
                   
                      List<Profesor> lista = Controller.getInstance().ucitajProfesore();
                     so.setOdgovor(lista);
                    break;   
                    
               case Operacije.UCITAJ_PREDMETE:
                   
                      List<Predmet> lista2 = Controller.getInstance().ucitajPredmete();
                     so.setOdgovor(lista2);
                    break; 
                    
              case Operacije.UCITAJ_ANGAZOVANJA:
                   
                      List<Angazovanje> lista3 = 
                              Controller.getInstance().ucitajAngazovanja((Profesor)kz.getParam());
                     so.setOdgovor(lista3);
                    break; 
              case Operacije.SACUVAJ_ANGAZOVANJA:
                   
                    boolean uspeh = Controller.getInstance()
                            .sacuvajAngazovanja((List<Angazovanje>)kz.getParam());
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
