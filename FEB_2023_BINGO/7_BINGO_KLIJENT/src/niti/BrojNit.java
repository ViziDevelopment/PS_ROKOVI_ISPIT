/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author vanja
 */
public class BrojNit extends Thread {
    private JLabel labela;
    private boolean kraj=false;
    public BrojNit(JLabel labela) {
        this.labela = labela;
    }

    @Override
    public void run() {
        while(!kraj){            
            int broj1 = (int) Math.round(Math.random()*5);                  
            labela.setText(broj1+"");     
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BrojNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public void zaustaviNit() {
        kraj=true;
        
    }
            
    
    
    
    
    
}
