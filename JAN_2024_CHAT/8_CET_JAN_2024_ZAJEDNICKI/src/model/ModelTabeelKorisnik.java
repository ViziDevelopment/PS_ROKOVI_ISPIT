/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Korisnik;

/**
 *
 * @author vanja
 */
public class ModelTabeelKorisnik extends AbstractTableModel {
    private List<Korisnik> lista;
    private final String[] kolone = {"id", "username",};

    public ModelTabeelKorisnik(List<Korisnik> lista) {
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
         Korisnik k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getUserId();
            case 1:
                return k.getKorisnickoIme(); 
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Korisnik> getLista() {
        return lista;
    }

  
 

    
}
