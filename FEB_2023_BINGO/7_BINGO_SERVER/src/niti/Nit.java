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
public class Nit extends Thread{

    private JLabel jLabelBroj1;
    private JLabel jLabelBroj2;
    private JLabel jLabelBroj3;
    private JLabel jLabelBroj4;
    private  boolean kraj=false;
    public Nit(JLabel jLabelBroj1, JLabel jLabelBroj2, JLabel jLabelBroj3, JLabel jLabelBroj4) {
        this.jLabelBroj1 = jLabelBroj1;
        this.jLabelBroj2 = jLabelBroj2;
        this.jLabelBroj3 = jLabelBroj3;
        this.jLabelBroj4 = jLabelBroj4;
    }
    
    
    
    @Override
    public void run() {        
        while(!kraj){            
            int broj1 = (int) Math.round(Math.random()*5);     
            int broj2 = (int) Math.round(Math.random()*5);     
            int broj3 = (int) Math.round(Math.random()*5);     
            int broj4 = (int) Math.round(Math.random()*5);                 
            jLabelBroj1.setText(broj1+"");
            jLabelBroj2.setText(broj2+"");
            jLabelBroj3.setText(broj3+"");
            jLabelBroj4.setText(broj4+"");
            
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Nit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }

    public void zaustaviNit() {
        kraj=true;
        interrupt();
    }
    
    
    
}
