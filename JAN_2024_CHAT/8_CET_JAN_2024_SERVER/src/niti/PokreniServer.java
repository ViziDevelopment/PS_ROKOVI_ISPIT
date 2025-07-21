/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 *
 * @author vanja
 */
public class PokreniServer extends Thread {
    private boolean kraj=false;
    private ServerSocket serverskiSoket;
    private int kapacitet=0;
    int brojac=0;
    @Override
    public void run() {
       /*String brPortaString="";
        try {
            brPortaString = Konfiguracija.getInstanca().getProperty("port");
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      // int port = Integer.parseInt(brPortaString);
        try {
            serverskiSoket = new ServerSocket(9000);
             System.out.println("SOKET OTVOREN");
            while(!kraj){ 
                Socket s = serverskiSoket.accept();
                brojac++;
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
                Controller.getInstance().getKlijenti().add(nit);
                nit.start(); 
                if(brojac>=kapacitet){
                    kraj=true;
                    return;
                }
            }
                     
            } catch (IOException ex) { 
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    public void zaustaviServer(){
        System.out.println("SOKET ZATVOREN");
        kraj=true;
        try {
            serverskiSoket.close();
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }
 
    
    
}
