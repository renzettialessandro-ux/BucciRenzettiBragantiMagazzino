/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buccirenzettibragantimagazzino;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author bucci.alex
 */

public class Tabella extends AbstractTableModel {

    /**
     * attributi
     */
    private final String[] colonne = {
        "ID", "Nome", "Prezzo Acq.", "Prezzo Vend.",
        "Scorta", "Scorta Min", "Venduti"
    };
    
    private ArrayList<Prodotto> prodotti;
    private final GestioneFile gf;

    /**
     * costruttore
     * @param gf gestore del file
     */
    public Tabella(GestioneFile gf) {
        this.gf = gf;
        this.prodotti = gf.leggiTuttiProdotti();
    }

    /**
     * metodo per aggiornare la tabella dal file
     */
    public void aggiorna() {
        this.prodotti = gf.leggiTuttiProdotti();
        fireTableDataChanged();
    }

    /**
     * getter numero di righe
     * @return numero di righe
     */
    @Override
    public int getRowCount() {
        return prodotti.size();
    }

    /**
     * getter numero colonne
     * @return numero colonne
     */
    @Override
    public int getColumnCount() {
        return colonne.length;
    }

    /**
     * getter nome colonna
     * @param col numero della colonna
     * @return nome della colonna
     */
    @Override
    public String getColumnName(int col) {
        return colonne[col];
    }

    /**
     * metodo che prende la classe degli oggetti nelle colonne
     * @param col colonna di riferimento
     * @return tipi di dati contenuti
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case 0, 1 ->
                String.class;
            case 2, 3 ->
                Double.class;
            default ->
                Integer.class;
        };
    }

    /**
     * metodo se la tabella è modificabile
     * @param rowIndex indice riga
     * @param columnIndex indice colonna
     * @return falso
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * prende i valori del prodotto
     * @param rowIndex indice riga
     * @param columnIndex indice colonna
     * @return valori da inserire nella tabella
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodotto p = prodotti.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                p.getProId();
            case 1 ->
                p.getProNome();
            case 2 ->
                p.getProPrezzoAcq();
            case 3 ->
                p.getProPrezzovendite();
            case 4 ->
                p.getProScorta();
            case 5 ->
                p.getProScortaMin();
            case 6 ->
                p.getProVenduti();
            default ->
                null;
        };
    }
}
