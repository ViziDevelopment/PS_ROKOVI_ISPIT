/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Profesor;

/**
 *
 * @author vanja
 */
public class ModelTabeleProfesor extends AbstractTableModel {
     private List<Profesor> lista;
    private final String[] kolone = {"id", "ime", "prezime", "zvanje","email"};

    public ModelTabeleProfesor() {
    }

    public ModelTabeleProfesor(List<Profesor> lista) {
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
        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getIme();
            case 2:
                return  p.getPrezime();
            case 3:
                return p.getZvanje();
           case 4:
                return p.getEmail();
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
