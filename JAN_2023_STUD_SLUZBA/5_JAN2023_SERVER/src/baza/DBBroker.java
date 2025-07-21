package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Angazovanje;
import model.Predmet;
import model.Profesor;
import model.Zvanje;
 
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vanja
 */
public class DBBroker {

    public Profesor unesiProf(Profesor profesor) {
        try {
            String upit = "INSERT INTO PROFESOR (ime,prezime,zvanje,email) VALUES (?,?,?,?)";
            
            PreparedStatement ps = Konekcija.getInstance().getConnection()
                    .prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,profesor.getIme());
            ps.setString(2,profesor.getPrezime());
            ps.setString(3,profesor.getZvanje().toString());
            ps.setString(4,profesor.getEmail());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){               
                int id= rs.getInt(1);
                profesor.setId(id);
                return profesor;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public boolean unesiPredmet(Predmet predmet) {
        try {
            String upit = "INSERT INTO PREDMET (naziv,kod,esp) VALUES (?,?,?)";
            
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
              ps.setString(1,predmet.getNaziv());
            ps.setString(2,predmet.getKod());
           ps.setInt(3,predmet.getEsp());
            int brojredova=  ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            if(brojredova>0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return false;
        
        
        
        
    }

    public List<Profesor> ucitajProfesore() {
        List<Profesor> lista = new ArrayList<>();
        try {
            
            String upit = "SELECT * from profesor";
            
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs= st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("email");
                Zvanje zvanje = Zvanje.valueOf(rs.getString("zvanje"));
                
                Profesor p = new Profesor(id, ime, prezime, zvanje, email);
                lista.add(p);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lista;
    }

    public List<Predmet> ucitajPredmete() {
         List<Predmet> lista = new ArrayList<>();
        try {
            
            String upit = "SELECT * from predmet";
            
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs= st.executeQuery(upit);
            while(rs.next()){
                int sifraPredmeta = rs.getInt("id");
                String naziv = rs.getString("naziv");
                String kod = rs.getString("kod");
                int esp = rs.getInt("esp");
                
                
               Predmet p = new Predmet(sifraPredmeta, naziv, kod, esp);
                lista.add(p);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lista;
        
    }
    public Predmet vratiPredmetNaOsnovuID(int id){
         List<Predmet> predmeti = ucitajPredmete();
           for (Predmet p : predmeti) {
                    if(p.getSifraPredmeta()==id){
                        return p;
                    }
                }
        return null;
    }
    public List<Angazovanje> ucitajAngazovanja(Profesor profesor) {
        List<Angazovanje> lista = new ArrayList<>();
       
        try {
            
            String upit = "SELECT * from angazovanje";
            
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs= st.executeQuery(upit);
            while(rs.next()){
                
                int id = rs.getInt("id");
                java.sql.Date datumSQL = rs.getDate("datum");
                java.util.Date datumUtil = new java.util.Date(datumSQL.getTime());
                String email = rs.getString("email");
                
                int predmetId = rs.getInt("predmet");  //1
                Predmet predmet = vratiPredmetNaOsnovuID(predmetId);
              
                Angazovanje a = new Angazovanje(id, datumUtil, profesor, predmet, email);
                
                lista.add(a);
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lista;
        
        
        
    }

    public boolean sacuvajAngazovanja(List<Angazovanje> list) {
        try {
            Profesor profesor = list.get(0).getProfesor();
            String upit1 = "delete from angazovanje where profesor="+profesor.getId();
            System.out.println(upit1);
            Statement st1 = Konekcija.getInstance().getConnection().createStatement();
            st1.executeUpdate(upit1);
            
            String upit2 = "INSERT INTO ANGAZOVANJE(datum,profesor,predmet,email) VALUES (?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit2);
            
            for (Angazovanje a : list) {
                ps.setDate(1, new java.sql.Date(a.getDatum().getTime()));
                ps.setInt(2, profesor.getId());
                ps.setInt(3, a.getPredmet().getSifraPredmeta());
                ps.setString(4, a.getEmail());
                
                ps.addBatch();
            }
            ps.executeBatch();
            
            Konekcija.getInstance().getConnection().commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
        
    }

     
    
    
    
}
