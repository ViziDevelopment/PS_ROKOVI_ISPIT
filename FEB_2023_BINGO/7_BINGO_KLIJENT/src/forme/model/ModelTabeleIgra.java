/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Igra;

/**
 *
 * @author vanja
 */
public class ModelTabeleIgra extends AbstractTableModel {
    private List<Igra> lista=new ArrayList<>();
    private final String[] kolone = {"rb", "kombinacija", "NA MESTU", "VAN MESTA"};

    public ModelTabeleIgra(List<Igra> lista) {
        this.lista = lista;
    }

    public ModelTabeleIgra() {
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
        Igra i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getRb();
            case 1:
                return i.getKombinacija();
            case 2:
                return  i.getBrPogodjenihNaMestu();
            case 3:
                return i.getBrPogodjenihNisuNaMestu(); 
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Igra> getLista() {
        return lista;
    }

    public void dodajIgru(Igra igra) {
        int rb = lista.size()+1;
        igra.setRb(rb);
        lista.add(igra);
        fireTableDataChanged();
    }

   
    
    
}
