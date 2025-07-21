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

/**
 *
 * @author vanja
 */
public class ModelTabele1 extends AbstractTableModel {
     private List<Profesor> lista;
  
    private final String[] kolone = {  "ime", "prezime", "brojAngazovanja"};

    public ModelTabele1(List<Profesor> lista ) {
        this.lista = lista;
        
    }

   
  
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesor p = lista.get(rowIndex);
        List<Angazovanje> svaAngazovanja = Controller
                     .getInstance().ucitajAngazovanja(p);
        switch (columnIndex) {
           
            case 0:
                return p.getIme();
            case 1:
                return  p.getPrezime();
            case 2:
                return svaAngazovanja.size(); 
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Profesor> getLista() {
        return lista;
    }
}
