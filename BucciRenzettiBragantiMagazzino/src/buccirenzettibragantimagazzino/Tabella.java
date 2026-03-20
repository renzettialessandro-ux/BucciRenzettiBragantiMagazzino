/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettibragantimagazzino;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author bucci.alex
 */
public class Tabella extends AbstractTableModel{
    private final String[] colonne = {
        "ID", "Nome", "Prezzo Acq.", "Prezzo Vend.", 
        "Scorta", "Scorta Min", "Venduti"
    };
    
    private ArrayList<Prodotto> prodotti;
    private GestioneFile gf;

    public Tabella(GestioneFile gf){
        this.gf = gf;
        this.prodotti = gf.leggiTuttiProdotti();
    }
    
    public void aggiorna(){
        this.prodotti = gf.leggiTuttiProdotti();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return prodotti.size();
    }

    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = prodotti.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> p.getProId();
            case 1 -> p.getProNome();
            case 2 -> p.getProPrezzoAcq();
            case 3 -> p.getProPrezzovendite();
            case 4 -> p.getProScorta();
            case 5 -> p.getProScortaMin();
            case 6 -> p.getProVenduti();
            default -> null;
        };
    }
    
    
    
    
}
