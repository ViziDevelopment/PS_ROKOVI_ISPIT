/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Korisnik;
import model.Poruka;

/**
 *
 * @author vanja
 */
public class ModelTabelePoruka extends AbstractTableModel {
     private List<Poruka> lista;
    private final String[] kolone = {"posiljalac", "tekst","vremeDatum"};

    public ModelTabelePoruka(List<Poruka> lista) {
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
         Poruka p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getPosiljalac().getKorisnickoIme();
            case 1:
                return p.getTekst(); 
            case 2: 
                return p.getDatumVreme();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Poruka> getLista() {
        return lista;
    }

   
    
    
    
}
