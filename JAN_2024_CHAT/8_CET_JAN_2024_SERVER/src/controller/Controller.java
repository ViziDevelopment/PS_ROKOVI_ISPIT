/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import forme.ServerskaForma;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Korisnik;
import model.Poruka;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author vanja
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList<>();
    private List<Korisnik> korisnici = new ArrayList<>();
    private ServerskaForma sf;
    private  Controller() {
        dbb = new DBBroker();
        
    }
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public List<ObradaKlijentskihZahteva> getKlijenti() {
        return klijenti;
    }
 
    public void setKlijenti(List<ObradaKlijentskihZahteva> klijenti) {
        this.klijenti = klijenti;
    }

    public Korisnik login(Korisnik korisnik) throws SQLException {
        Korisnik k = dbb.login(korisnik);
        if(k!=null){
            if(!korisnici.contains(k)){
                 korisnici.add(k);
                 sf.osveziTabelu();
                posaljiUlogovaneKorisnike();
                 
                  return k;
            }
            else{
                return null;
            }
        }
        return k;
    }
    public void logout(Korisnik korisnik) {
        for (ObradaKlijentskihZahteva okz : klijenti) {
            if(okz.getUlogovani().equals(korisnik)){
                okz.odjaviKorisnika();
            }
        } 
        korisnici.remove(korisnik);
        sf.osveziTabelu();
        posaljiUlogovaneKorisnike();
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public void posaljiUlogovaneKorisnike() {
        for (ObradaKlijentskihZahteva okz : klijenti) {
                     okz.posaljiUlogovaneKorisnike(korisnici);
        }
    }

    public List<Poruka> ucitajPoruke(Korisnik korisnik) throws SQLException {
        return dbb.ucitajPoruke(korisnik);
    }

    public void kreirajPoruku(Poruka p) throws SQLException {
        dbb.kreirajPoruku(p);
    }


        
    
    
}
