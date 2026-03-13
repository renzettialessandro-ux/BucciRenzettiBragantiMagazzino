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

   /**
    * costruttore di prodotto
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
    }
   
   
}
