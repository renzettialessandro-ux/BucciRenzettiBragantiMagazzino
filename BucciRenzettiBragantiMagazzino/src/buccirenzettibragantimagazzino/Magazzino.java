package buccirenzettibragantimagazzino;

import java.util.ArrayList;

public class Magazzino {

    private ArrayList<Prodotto> listaProdotti;

    public Magazzino() {
        listaProdotti = new ArrayList<>();
    }

    /**
     * Aggiunge il prodotto alla lista. La scorta è già impostata dal
     * costruttore di Prodotto, quindi NON si chiama aumentaScorta per evitare
     * di raddoppiarla.
     */
    public void aggiungiProdotto(Prodotto p) {
        if (p != null) {
            listaProdotti.add(p);
        }
    }

    /**
     * Rimuove il prodotto dalla lista e diminuisce la scorta.
     *
     * @return true se la scorta è stata diminuita con successo
     */
    public boolean togliProdotto(Prodotto p, int vendite) {
        if (p == null) {
            return false;
        }
        boolean ok = p.diminuisciScorta(vendite);
        if (ok && listaProdotti.contains(p)) {
            listaProdotti.remove(p);
        }
        return ok;
    }

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }
}
