package buccirenzettibragantimagazzino;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class Tabella extends AbstractTableModel {

    private final String[] colonne = {
        "ID", "Nome", "Prezzo Acq.", "Prezzo Vend.",
        "Scorta", "Scorta Min", "Venduti"
    };

    private ArrayList<Prodotto> prodotti;
    private final GestioneFile gf;

    public Tabella(GestioneFile gf) {
        this.gf = gf;
        this.prodotti = gf.leggiTuttiProdotti();
    }

    public void aggiorna() {
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
    public String getColumnName(int col) {
        return colonne[col];
    }

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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

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
