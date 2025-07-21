/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.forme;
 
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Prevod;
import model.Rec;

/**
 *
 * @author vanja
 */
public class ModelTabeleRecnik extends AbstractTableModel {
    private String[] kolone={"srp","eng"};
    private List<Rec> lista;

    public ModelTabeleRecnik(List<Rec> lista) {
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
        Rec r = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return r.getSrpskaRec();
            case 1: 
               // r.getPrevodi().stream().map(Prevod::getEngRec).reduce((a,b)->a+";"+b)
                
                List<Prevod> prevodi =r.getPrevodi();
                String rezultat="";
                for (Prevod p : prevodi) {
                    rezultat+=p.getEngRec();
                    rezultat+=";";
                }
                return rezultat;
            default: return "NA";
                
        }
    }
    
}
