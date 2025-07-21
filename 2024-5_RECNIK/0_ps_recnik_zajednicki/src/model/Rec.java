/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vanja
 */
public class Rec implements Serializable {
    private int id;
    private String srpskaRec;
    private List<Prevod> prevodi;

    public Rec() {
    }

    public Rec(int id, String srpskaRec, List<Prevod> prevodi) {
        this.id = id;
        this.srpskaRec = srpskaRec;
        this.prevodi = prevodi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrpskaRec() {
        return srpskaRec;
    }

    public void setSrpskaRec(String srpskaRec) {
        this.srpskaRec = srpskaRec;
    }

    public List<Prevod> getPrevodi() {
        return prevodi;
    }

    public void setPrevodi(List<Prevod> prevodi) {
        this.prevodi = prevodi;
    }

    @Override
    public String toString() {
        return "Rec{" + "id=" + id + ", srpskaRec=" + srpskaRec + ", prevodi=" + prevodi + '}';
    }
    
    
    
    
    
    
    
    
    
    
}
