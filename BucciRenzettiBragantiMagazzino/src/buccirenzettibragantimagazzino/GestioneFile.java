/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettibragantimagazzino;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author renzetti.alessandro
 */
public class GestioneFile {
     private int dimRecordProdotto = 112;

    public GestioneFile() {
    }

    
    public String aggiustaLunghezzaStringa(String s) {
        String aggiustata = s;
        if (s.length() < 20) {
            for (int i = 0; i < (20 - s.length()); i++) {
                aggiustata += "*";
            }
            return aggiustata;
        } else if (s.length() > 20) {
            aggiustata = s.substring(0, 20);
            return aggiustata;
        }
        return s;
    }
    
    public void aggiungiCombinazione(String proId,int posizione) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter("key.txt", true);
            fw.write(proId + ";" + posizione + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Errore scrittura combinazioni");
        }
    }
    
    public void aggiungiStudenteFile(Prodotto p) {
        try {
            RandomAccessFile file = new RandomAccessFile("studenti.gay", "rw");
            int nRecord = (int) (file.length() / dimRecordProdotto);
            file.seek(nRecord * dimRecordProdotto);
            file.writeChars(aggiustaLunghezzaStringa(p.getNome()));   
            file.writeChars(aggiustaLunghezzaStringa(s.getCognome())); 
            file.writeChars(s.getId());                               
            file.writeChars(aggiustaLunghezzaStringa(s.getClasse()));
            file.close();                                            
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file");
        }
    }
}
