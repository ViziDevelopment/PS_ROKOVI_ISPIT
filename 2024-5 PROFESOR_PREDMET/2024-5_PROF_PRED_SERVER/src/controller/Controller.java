/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
import model.Angazovanje;
import model.Predmet;
import model.Profesor;

/**
 *
 * @author vanja
 */
public class Controller {
    private DBBroker dbb; 
    private static Controller instance;    
    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    private  Controller() {
       dbb= new DBBroker();
    }

    public void popuniBazu() {
        dbb.popuniBazu();
    }

    public Profesor login(Profesor profesor) {
        return dbb.login(profesor);
    }

    public List<Angazovanje> vratiAngazovanja(Profesor profesor) {
        return dbb.vratiAngazovanja(profesor);
    }

    public List<Angazovanje> vratiAngazovanjaPredmet(Predmet predmet) {
        return dbb.vratAngazovanjaPredmet(predmet);
    }

    public boolean obrisiAng(Angazovanje angazovanje) {
        return dbb.obisiAng(angazovanje);
    }

    public List<Predmet> vratiPredmete() {
        return dbb.vratiPredmete();
    }

    public List<Profesor> vratiProfesore() {
        return dbb.vratiProfesore();
    }

    public boolean kreirajAng(Angazovanje angazovanje) {
        return dbb.kreiajAng(angazovanje);
    }

 
      
}
