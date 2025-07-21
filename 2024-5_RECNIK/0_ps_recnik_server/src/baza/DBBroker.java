/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Korisnik;
import model.Prevod;
import model.Rec;

/**
 *
 * @author vanja
 */
public class DBBroker {

    public List<Korisnik> vratiSveKorisnike() throws SQLException {
        List<Korisnik> lista = new ArrayList<>();
        String upit = "Select * from user";
        PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Korisnik k= new Korisnik();
            k.setId(rs.getInt("id"));
            k.setEmail(rs.getString("email"));
            k.setIme(rs.getString("ime"));
            k.setPrezime(rs.getString("prezime"));
            k.setStatus(rs.getString("status"));
            k.setLozinka(rs.getString("lozinka"));
            
            lista.add(k);
        }
        
        return lista;
    }

    public Korisnik login(Korisnik korisnik) throws SQLException {
         List<Korisnik> lista =  vratiSveKorisnike();
          for (Korisnik k : lista) {
            if(k.equals(korisnik)){
                LocalDateTime sad = LocalDateTime.now();
                azurirajStatus(k,"prijavljen "+sad);
                k.setStatus("prijavljen "+sad);
                return k;
            }
        }
       return null;
        
        
    }

    private void azurirajStatus(Korisnik k,String status) {
        
        try {
             String upit = " UPDATE user set status=? WHERE id=?";
             PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
              ps.setString(1, status);
            ps.setInt(2, k.getId());
            int uspeh=ps.executeUpdate();
            if(uspeh>0){
                Konekcija.getInstance().getConnection().commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean logout(Korisnik korisnik) {
        try{
            LocalDateTime sad = LocalDateTime.now();
           azurirajStatus(korisnik, "odjavljen "+sad);
           return true;
        }catch(Exception e){
             return false;
        }
       
    }

    public List<Rec> ucitajRecnik() { 
      
             try {
                    List<Rec> lista = new ArrayList<>();
                    String upit = "Select * from Rec";
                    PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){
                        Rec r = new Rec();
                        r.setId(rs.getInt("id"));
                        r.setSrpskaRec(rs.getString("srpska_rec"));
                        lista.add(r);
                    }
                    // 1,kuca,(home,house)->2,pas->3,auto
                    for (Rec rec : lista) {
                        upit = "Select * from prevod where srpska_rec_id="+rec.getId();
                        System.out.println(upit);
                         ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
                         rs = ps.executeQuery();
                         List<Prevod> prevodi = new ArrayList<>();
                         while(rs.next()){
                             Prevod p = new Prevod();
                             p.setId(rs.getInt("id"));
                             p.setEngRec(rs.getString("eng_rec"));
                             p.setRec(rec);
                             prevodi.add(p);
                         }
                         rec.setPrevodi(prevodi);
                    }
                    return lista;
                    
             } catch (SQLException ex) {
                 Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
             }
             return null;
        }

    public boolean unesi(Rec rec) {
        
        try {
            String upit = " insert into rec (srpska_rec) values (?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection()
                    .prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, rec.getSrpskaRec());
            ps.executeUpdate();
            
            ResultSet generatedKeys= ps.getGeneratedKeys();
            int noviId=-1;
            if(generatedKeys.next()){
                noviId= generatedKeys.getInt(1);
            }else{
                System.out.println("GRESKA PRILIKOM DOBIJANJA NOVOG IDIJA");
            }
             
            List<Prevod> lista = rec.getPrevodi(); 
            for (Prevod p : lista) {
                upit = "insert into prevod (srpska_rec_id,eng_rec) values (?,?)";
                ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
                ps.setInt(1, noviId);
                ps.setString(2, p.getEngRec()); 
                ps.executeUpdate();
            }
            
            Konekcija.getInstance().getConnection().commit();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
                
        
        
    }
        
        
        
    }
    
 
