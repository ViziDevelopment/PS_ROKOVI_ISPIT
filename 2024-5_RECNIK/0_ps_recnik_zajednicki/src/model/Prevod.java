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
public class Prevod implements Serializable {
    private int id;
    private Rec rec;
    private String engRec;

    public Prevod() {
    }

    public Prevod(int id, Rec rec, String engRec) {
        this.id = id;
        this.rec = rec;
        this.engRec = engRec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rec getRec() {
        return rec;
    }

    public void setRec(Rec rec) {
        this.rec = rec;
    }

    public String getEngRec() {
        return engRec;
    }

    public void setEngRec(String engRec) {
        this.engRec = engRec;
    }
    
    
    
    
    
    
}
