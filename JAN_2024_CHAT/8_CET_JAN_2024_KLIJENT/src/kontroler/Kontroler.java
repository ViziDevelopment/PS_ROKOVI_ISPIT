/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.KlijentskaForma;
import java.util.List;
import model.Korisnik;
import model.Poruka;

/**
 *
 * @author vanja
 */
public class Kontroler {
    private static Kontroler instance;


   private KlijentskaForma kf;
   
    private Kontroler() {
        
    }

    public static Kontroler getInstance() {
        if(instance==null){
            instance= new Kontroler();
        }
        return instance;
    }

    public void setKf(KlijentskaForma kf) {
        this.kf = kf;
    }

    public void zatvoriKlijentskuFormu() {
        kf.zatvoriFormu();
    }
    public  void osveziTabelu(List<Korisnik> korisnici) {
        kf.osveziTabelu(korisnici);
    }

    public void prikaziPoruke(List<Poruka> poruke) {
        kf.prikaziPoruke(poruke);
    }
}
