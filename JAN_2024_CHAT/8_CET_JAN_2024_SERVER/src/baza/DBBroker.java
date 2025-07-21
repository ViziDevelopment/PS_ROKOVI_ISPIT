/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.*;
 
import model.Korisnik;
import java.sql.ResultSet;     
import java.util.ArrayList;
import java.util.List;
import model.Poruka;
 

/**
 *
 * @author vanja
 */
public class DBBroker {

    public Korisnik login(Korisnik korisnik) throws SQLException {
        String upit = "SELECT * FROM KORISNIK WHERE username=? and password=?";
        PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            System.out.println(korisnik);
        ps.setString(1,korisnik.getKorisnickoIme());
        ps.setString(2,korisnik.getLozinka());
       
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("DA");
            korisnik.setUserId(rs.getInt("id"));
            return korisnik;
        }else{
             System.out.println("NE");
            return null;
        }
 
    }

    public List<Poruka> ucitajPoruke(Korisnik korisnik) throws SQLException {
        List<Poruka> lista = new ArrayList<>();
        String upit = "SELECT * FROM PORUKA P "
                + "JOIN KORISNIK K ON P.POSILJALAC=K.ID"
                + " WHERE primalac=?"
                + " ORDER BY P.datumVreme DESC";
        PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            
        ps.setInt(1,korisnik.getUserId()); 
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int id= rs.getInt("p.id");
            Korisnik posiljalac  = new Korisnik(rs.getInt("k.id"), 
                    rs.getString("k.username"), null);
            String tekst = rs.getString("p.tekst");
            Timestamp datumVremeTS =rs.getTimestamp("p.datumVreme");
            java.util.Date datum = new java.util.Date(datumVremeTS.getTime()); 
            Poruka p = new Poruka(id, posiljalac, korisnik, tekst, datum);
            lista.add(p); 
        }
        return lista;
        
    }

    public void kreirajPoruku(Poruka p) throws SQLException {
        String upit = "INSERT INTO PORUKA (POSILJALAC, PRIMALAC,TEKST,DATUMVREME)"
                + " VALUES(?,?,?,?)";
        PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);

        ps.setInt(1,p.getPosiljalac().getUserId());
        ps.setInt(2,p.getPrimalac().getUserId()); 
        ps.setString(3,p.getTekst());
        Timestamp ts = new Timestamp(p.getDatumVreme().getTime());
        ps.setTimestamp(4, ts); 
        ps.executeUpdate();    
        Konekcija.getInstance().getConnection().commit();
        
    }
    
}
