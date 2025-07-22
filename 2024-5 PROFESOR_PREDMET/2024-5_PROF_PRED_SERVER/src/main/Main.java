/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.Controller;
import server.PokreniServer;

/**
 *
 * @author vanja
 */
public class Main {
    public static void main(String[] args) {
        PokreniServer ps =new PokreniServer();
        ps.start();
        
        
        
        
       // Controller.getInstance().popuniBazu();
    }
}
