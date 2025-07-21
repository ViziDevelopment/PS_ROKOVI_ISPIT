/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Angazovanje;
import model.Profesor;

/**
 *
 * @author vanja
 */
public class ModelTabeleAngazovanje extends AbstractTableModel {
      private List<Angazovanje> lista;
    private final String[] kolone = {"datum", "predmet"};

    public ModelTabeleAngazovanje(List<Angazovanje> lista) {
        this.lista = lista;
    }

    public ModelTabeleAngazovanje() {
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
        Angazovanje a = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getDatum();
            case 1:
                return a.getPredmet().getNaziv(); 
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Angazovanje> getLista() {
        return lista;
    }

    public void dodaj(Angazovanje a) {
        lista.add(a);
        fireTableDataChanged();
    }

    public void obrisi(int selektovaniRed) {
        lista.remove(selektovaniRed);
        fireTableDataChanged();
    }

    public void osveziTabelu() {
         fireTableDataChanged();
    }

    public void azuirajAngazovanje(Angazovanje a) {
        for (Angazovanje angazovanje : lista) {
            if(angazovanje.getPredmet().equals(a.getPredmet()) && 
                    angazovanje.getProfesor().equals(a.getProfesor())){
                angazovanje.setDatum(a.getDatum());
            }
        }
        fireTableDataChanged();
    }

  
 
}
