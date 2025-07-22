/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Angazovanje;

/**
 *
 * @author vanja
 */
public class ModelTabeleAngazovanja2 extends AbstractTableModel {
    String[] kolone = {"id","ime","prezime","vrsta"};
     private List<Angazovanje> lista;

    public ModelTabeleAngazovanja2(List<Angazovanje> lista) {
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
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Angazovanje a = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return a.getId();
            case 1: return a.getProfesor().getIme();
            case 2: return a.getProfesor().getPrezime();
            case 3: return a.getVrsta().toString();
            default: return "NA";
                 
        }
    }

    public List<Angazovanje> getLista() {
        return lista;
    }
    
}
