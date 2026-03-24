/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccirenzettibragantimagazzino;

import java.util.ArrayList;

/**
 *
 * @author renzetti.alessandro
 */
public class Magazzino {

    /**
     * attributi
     */
    private ArrayList<Prodotto> listaProdotti;

    /**
     * costruttore di magazzino
     */
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

    /**
     * getter della lista dei prodotti
     *
     * @return lista di prodotti
     */
    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    /**
     * setter della lista dei prodotti
     *
     * @param listaProdotti lista di prodotti da assegnare all'oggetto
     */
    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }
}
