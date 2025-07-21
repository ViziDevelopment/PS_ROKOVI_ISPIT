/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import controller.Controller;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Angazovanje;
import model.Profesor;
import model.Zvanje;

/**
 *
 * @author vanja
 */
public class ModelTabele2 extends AbstractTableModel {
    private Zvanje[] zvanja = Zvanje.values();
    private final String[] kolone = {  "zvanje", "brojProfesor"};
 
    private List<Profesor> lista;

    public ModelTabele2(List<Profesor> lista) {
        this.lista = lista;
    }
  
    
    
    @Override
    public int getRowCount() {
        return zvanja.length;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Zvanje z = zvanja[rowIndex];
        int brojac=0;
        for (Profesor p : lista) {
            if(p.getZvanje().equals(z)){
                brojac++;
            }
        }
        if(brojac>0){
             switch (columnIndex) {           
            case 0:
                return z;
            case 1:
                return  brojac;
            
            default:
                return null;
            }
        }else{
            switch (columnIndex) {           
            case 0:
                return null;
            case 1:
                return null;
            
            default:
                return null;
            }
        }
       
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
 
}

