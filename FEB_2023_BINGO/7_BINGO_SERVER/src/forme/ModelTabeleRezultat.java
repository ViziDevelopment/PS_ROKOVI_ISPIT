/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Igra;
import model.RezultatIgre;

/**
 *
 * @author vanja
 */
public class ModelTabeleRezultat extends AbstractTableModel {
    private List<RezultatIgre> lista=new ArrayList<>();
    private final String[] kolone = {"datumVreme", "kombinacija", "brPokusaja", "rezultat"};

    public ModelTabeleRezultat(List<RezultatIgre> lista) {
        this.lista=lista;
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
        RezultatIgre i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return i.getDatumVreme();
            case 1:
                return i.getDobitnaKomb();
            case 2:
                return  i.getBrojPokusaja();
            case 3:
                return i.getRezultat(); 
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<RezultatIgre> getLista() {
        return lista;
    }

    int vratiID(int selektovaniRed) {
        return lista.get(selektovaniRed).getId();
        
    }
  
    
}
