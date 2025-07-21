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
public class Igra implements Serializable {
    private int rb;
    private String kombinacija;
    private int brPogodjenihNaMestu;
    private int brPogodjenihNisuNaMestu;

    public Igra() {
    }

    public Igra(int rb, String kombinacija, int brPogodjenihNaMestu, int brPogodjenihNisuNaMestu) {
        this.rb = rb;
        this.kombinacija = kombinacija;
        this.brPogodjenihNaMestu = brPogodjenihNaMestu;
        this.brPogodjenihNisuNaMestu = brPogodjenihNisuNaMestu;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getKombinacija() {
        return kombinacija;
    }

    public void setKombinacija(String kombinacija) {
        this.kombinacija = kombinacija;
    }

    public int getBrPogodjenihNaMestu() {
        return brPogodjenihNaMestu;
    }

    public void setBrPogodjenihNaMestu(int brPogodjenihNaMestu) {
        this.brPogodjenihNaMestu = brPogodjenihNaMestu;
    }

    public int getBrPogodjenihNisuNaMestu() {
        return brPogodjenihNisuNaMestu;
    }

    public void setBrPogodjenihNisuNaMestu(int brPogodjenihNisuNaMestu) {
        this.brPogodjenihNisuNaMestu = brPogodjenihNisuNaMestu;
    }

    @Override
    public String toString() {
        return "Igra{" + "rb=" + rb + ", kombinacija=" + kombinacija + ", brPogodjenihNaMestu=" + brPogodjenihNaMestu + ", brPogodjenihNisuNaMestu=" + brPogodjenihNisuNaMestu + '}';
    }
    
    
    
    
    
}
