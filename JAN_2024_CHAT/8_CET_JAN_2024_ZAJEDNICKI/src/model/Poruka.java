/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vanja
 */
public class Poruka implements Serializable {
    private int id;
    private Korisnik posiljalac;
    private Korisnik primalac;
    private String tekst;
    private Date datumVreme;

    public Poruka() {
    }

    public Poruka(int id, Korisnik posiljalac, Korisnik primalac, String tekst, Date datumVreme) {
        this.id = id;
        this.posiljalac = posiljalac;
        this.primalac = primalac;
        this.tekst = tekst;
        this.datumVreme = datumVreme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Korisnik getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Korisnik posiljalac) {
        this.posiljalac = posiljalac;
    }

    public Korisnik getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Korisnik primalac) {
        this.primalac = primalac;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    @Override
    public String toString() {
        return "Poruka{" + "id=" + id + ", posiljalac=" + posiljalac + ", primalac=" + primalac + ", tekst=" + tekst + ", datumVreme=" + datumVreme + '}';
    }
    
    
    
    
    
    
}
