/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettibragantimagazzino;

/**
 *
 * @author renzetti.alessandro
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
     * costruttore di prodotto
     *
     * @param proId id del prodotto
     * @param proNome nome del prodotto
     * @param proPrezzoAcq prezzo dell'acquirente
     * @param proPrezzovendite prezzo di vendita
     * @param proScorta numero prodotti da acquistare
     */
    public Prodotto(String proId, String proNome, double proPrezzoAcq, double proPrezzovendite, int proScorta) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrezzoAcq = proPrezzoAcq;
        this.proPrezzovendite = proPrezzovendite;
        this.proScorta = proScorta;
        proScortaMin = 5;
        stato = 1;
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public double getProPrezzoAcq() {
        return proPrezzoAcq;
    }

    public void setProPrezzoAcq(double proPrezzoAcq) {
        this.proPrezzoAcq = proPrezzoAcq;
    }

    public double getProPrezzovendite() {
        return proPrezzovendite;
    }

    public void setProPrezzovendite(double proPrezzovendite) {
        this.proPrezzovendite = proPrezzovendite;
    }

    public int getProScorta() {
        return proScorta;
    }

    public void setProScorta(int proScorta) {
        this.proScorta = proScorta;
    }

    public int getProScortaMin() {
        return proScortaMin;
    }

    public void setProScortaMin(int proScortaMin) {
        this.proScortaMin = proScortaMin;
    }

    public int getProVenduti() {
        return proVenduti;
    }

    public void setProVenduti(int proVenduti) {
        this.proVenduti = proVenduti;
    }

    public void aumentaScorta(int aumento) {
        this.proScorta += aumento;
    }

    public void diminuisciScorta(int vendite) {

        if (proScorta - vendite >= proScortaMin) {
            this.proScorta -= vendite;
        } else {
            System.out.println("non puoi andare sotto la scorta minima di: " + proScortaMin + " prodotti");
        }
    }

    @Override
    public String toString() {
        return "Prodotto{" + "proId=" + proId + ", proNome=" + proNome + ", proPrezzoAcq=" + proPrezzoAcq + ", proPrezzovendite=" + proPrezzovendite + ", proScorta=" + proScorta + ", proScortaMin=" + proScortaMin + ", proVenduti=" + proVenduti + '}';
    }

}
