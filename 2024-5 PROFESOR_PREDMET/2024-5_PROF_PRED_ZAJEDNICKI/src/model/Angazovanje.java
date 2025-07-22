/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author vanja
 */
public class Angazovanje implements Serializable {
    
    private int id;
    private Profesor profesor;
    private Predmet predmet;
    private VrstaAngazovanja vrsta;  

    public Angazovanje() {
    }

    public Angazovanje(int id, Profesor profesor, Predmet predmet, VrstaAngazovanja vrsta) {
        this.id = id;
        this.profesor = profesor;
        this.predmet = predmet;
        this.vrsta = vrsta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public VrstaAngazovanja getVrsta() {
        return vrsta;
    }

    public void setVrsta(VrstaAngazovanja vrsta) {
        this.vrsta = vrsta;
    }
    
    
    
    
}
