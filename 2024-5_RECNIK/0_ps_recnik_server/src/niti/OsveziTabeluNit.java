/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;
import java.util.logging.Level;
import java.util.logging.Logger;
import forme.ServerskaForma;
import java.sql.SQLException;

/**
 *
 * @author vanja
 */
public class OsveziTabeluNit  extends Thread{
     private ServerskaForma sf;

    public OsveziTabeluNit(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
         while(!isInterrupted()){
            
            try {
                Thread.sleep(5000);
                try {
                    sf.popunitabelu();
                } catch (SQLException ex) {
                    Logger.getLogger(OsveziTabeluNit.class.getName()).log(Level.SEVERE, null, ex);
                }
                 System.out.println("OSVEZENA TABELA");
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziTabeluNit.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("NIT ZA OSVEZAVANJE PREKINUTA");
                break;
            }
               
        }
    }
     
     
     
     
     
}
