/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buccirenzettibragantimagazzino;

/**
 *
 * @author braganti.alessandro
 */
public class Prodotto {

    /**
     * attributi
     */
    private String proId;
    private String proNome;
    private double proPrezzoAcq;
    private double proPrezzovendite;
    private int proScorta;
    private int proScortaMin;
    private int proVenduti;
    private int stato;

    /**
     * costruttore
     *
     * @param proId id del prodotto
     * @param proNome nome del prodotto
     * @param proPrezzoAcq prezzo di acquisto del prodotto
     * @param proScorta numero dei prodotti disponibili
     */
    public Prodotto(String proId, String proNome, double proPrezzoAcq, int proScorta) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrezzoAcq = proPrezzoAcq;
        this.proPrezzovendite = proPrezzoAcq * 1.23;
        this.proScorta = proScorta;
        this.proScortaMin = 5;
        this.stato = 1;
    }

    /**
     * getter stato
     *
     * @return stato del prodotto
     */
    public int getStato() {
        return stato;
    }

    /**
     * setter dello stato
     *
     * @param stato stato da impostare
     */
    public void setStato(int stato) {
        this.stato = stato;
    }

    /**
     * getter id del prodotto
     *
     * @return id del prodotto
     */
    public String getProId() {
        return proId;
    }

    /**
     * setter dell'id del prodotto
     *
     * @param proId id da inserire
     */
    public void setProId(String proId) {
        this.proId = proId;
    }

    /**
     * getter nome del prodotto
     *
     * @return nome del prodotto
     */
    public String getProNome() {
        return proNome;
    }

    /**
     * setter nome del prodotto
     *
     * @param proNome nome da inserire
     */
    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    /**
     * getter prezzo di acquisto
     *
     * @return prezzo di acquisto
     */
    public double getProPrezzoAcq() {
        return proPrezzoAcq;
    }

    /**
     * setter prezzo di acquisto
     *
     * @param proPrezzoAcq prezzo di acquisto da assegnare
     */
    public void setProPrezzoAcq(double proPrezzoAcq) {
        this.proPrezzoAcq = proPrezzoAcq;
    }

    /**
     * getter prezzo di vendita
     *
     * @return prezzo di vendita
     */
    public double getProPrezzovendite() {
        return proPrezzovendite;
    }

    /**
     * setter prezzo di vendita
     *
     * @param proPrezzovendite prezzo di vendita da assegnare
     */
    public void setProPrezzovendite(double proPrezzovendite) {
        this.proPrezzovendite = proPrezzovendite;
    }

    /**
     * getter scorta del prodotto
     *
     * @return scorta del prodotto
     */
    public int getProScorta() {
        return proScorta;
    }

    /**
     * setter scorta del prodotto
     *
     * @param proScorta scorda da assegnare
     */
    public void setProScorta(int proScorta) {
        this.proScorta = proScorta;
    }

    /**
     * getter scorta minima
     *
     * @return scorta minima
     */
    public int getProScortaMin() {
        return proScortaMin;
    }

    /**
     * setter scorta minima
     *
     * @param proScortaMin scorta minima da assegnare
     */
    public void setProScortaMin(int proScortaMin) {
        this.proScortaMin = proScortaMin;
    }

    /**
     * getter prodotti venduti
     *
     * @return numero prodotti venduti
     */
    public int getProVenduti() {
        return proVenduti;
    }

    /**
     * setter prodotti venduti
     *
     * @param proVenduti numero dei prodotti venduti
     */
    public void setProVenduti(int proVenduti) {
        this.proVenduti = proVenduti;
    }

    /**
     * metodo per aumentare la scorta
     *
     * @param aumento valore di aumento
     */
    public void aumentaScorta(int aumento) {
        this.proScorta += aumento;
    }

    /**
     * Diminuisce la scorta se non si scende sotto la scorta minima.
     *
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public boolean diminuisciScorta(int vendite) {
        if (proScorta - vendite >= proScortaMin) {
            this.proScorta -= vendite;
            this.proVenduti += vendite;
            return true;
        }
        return false;
    }

    /**
     * override toString
     *
     * @return attributi dell'oggetto
     */
    @Override
    public String toString() {
        return "Prodotto:" + "proId=" + proId + ", proNome=" + proNome + ", proPrezzoAcq=" + proPrezzoAcq + ", proPrezzovendite=" + proPrezzovendite + ", proScorta=" + proScorta + ", proScortaMin=" + proScortaMin + ", proVenduti=" + proVenduti + ", stato=" + stato;
    }

}
