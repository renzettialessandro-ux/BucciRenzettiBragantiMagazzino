/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettibragantimagazzino;
import java.util.*;
/**
 *
 * @author bucci.alex
 */
public class Magazzino {
    /**
     * attributi
     */
    private ArrayList<Prodotto> listaProdotti;
    public Magazzino(){
        listaProdotti=new ArrayList();
    }
    public void aggiungiProdotto(Prodotto p,int quantita){
        if(p!=null){
            listaProdotti.add(p);
            p.aumentaScorta(quantita);
        }
        
    }
    public void togliProdotto(Prodotto p,int vendite){
        if(listaProdotti.contains(p))listaProdotti.remove(p);
        p.diminuisciScorta(vendite);
    }
    
}
