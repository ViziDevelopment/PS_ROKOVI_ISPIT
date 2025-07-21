/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker; 
import java.util.ArrayList;
import java.util.List;
import model.Angazovanje;
import model.Korisnik;
import model.Predmet;
import model.Profesor;
 
import server.ObradaKlijentskihZahteva;

/**
 *
 * @author vanja
 */
public class Controller {
    private List<Korisnik> sviKorisnici =  new ArrayList<>();
    private DBBroker dbb; 
    private static Controller instance;    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    private  Controller() {
          Korisnik k1 = new Korisnik(1, "vanja", "vizi", "vanja@gmail.com", "vanja");
          Korisnik k2 = new Korisnik(1, "vanja2", "vizi2", "vanja2@gmail.com", "vanja2");
          Korisnik k3 = new Korisnik(1, "vanja3", "vizi3", "vanja3@gmail.com", "vanja33");
          Korisnik k4 = new Korisnik(1, "vanja4", "vizi4", "vanja4@gmail.com", "vanja4");
          Korisnik k5 = new Korisnik(1, "vanja5", "vizi5", "vanja5@gmail.com", "vanja5");
          
          sviKorisnici.add(k1);
          sviKorisnici.add(k2);
          sviKorisnici.add(k3);
          sviKorisnici.add(k4);
          sviKorisnici.add(k5);

          dbb = new DBBroker();
    }

    public Korisnik login(Korisnik korisnik) {
         for (Korisnik k : sviKorisnici) {
            if(k.getEmail().equals(korisnik.getEmail())  && 
                    k.getLozinka().equals(korisnik.getLozinka())){
                return k;
            }
        }
            return null;
        
    }

    public Profesor unesiProf(Profesor profesor) {
        return dbb.unesiProf(profesor);
        
    }

    public boolean unesiPredmet(Predmet predmet) {
          return dbb.unesiPredmet(predmet);
    }

    public List<Profesor> ucitajProfesore() {
        return dbb.ucitajProfesore();
    }

    public List<Predmet> ucitajPredmete() {
        return dbb.ucitajPredmete();
    }

    public List<Angazovanje> ucitajAngazovanja(Profesor profesor) {
        return dbb.ucitajAngazovanja(profesor);
    }

    public boolean sacuvajAngazovanja(List<Angazovanje> list) {
        return dbb.sacuvajAngazovanja(list);
    }

  
    
    
}
