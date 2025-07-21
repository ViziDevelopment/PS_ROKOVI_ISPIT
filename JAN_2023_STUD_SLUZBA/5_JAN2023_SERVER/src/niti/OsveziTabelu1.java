/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanja
 */
public class OsveziTabelu1 extends Thread {
    private boolean kraj=false;
    private ServerskaForma sf;

    public OsveziTabelu1(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while(!kraj){
            try {
                sf.popuniTabelu1();
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziTabelu1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void zaustavi(){
        kraj=true;
    }
    
    
    
    
    
    
}
