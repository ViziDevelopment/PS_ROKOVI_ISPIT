/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Korisnik;

/**
 *
 * @author vanja
 */
public class ModelTabeleKorisnici extends AbstractTableModel{
    String[] kolone = {"ime","prezime","email","status"};
    List<Korisnik> lista;

    public ModelTabeleKorisnici(List<Korisnik> lista) {
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
        Korisnik k=  lista.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getEmail();
            case 3: return k.getStatus();
            default: return "NA";
        }
        
    }
    
}
