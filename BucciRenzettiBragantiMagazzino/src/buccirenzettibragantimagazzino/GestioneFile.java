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
    
    public void aggiungiProdottoFile(Prodotto p) {
        try {
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw");
            int nRecord = (int) (file.length() / dimRecordProdotto);
            file.seek(nRecord * dimRecordProdotto);
            file.writeChars(aggiustaLunghezzaStringa(p.getProId())); 
            file.writeChars(aggiustaLunghezzaStringa(p.getProNome()));   
            file.writeDouble(p.getProPrezzoAcq()); 
            file.writeDouble(p.getProPrezzovendite());
            file.writeInt(p.getProScorta());
            file.writeInt(p.getProScortaMin());
            file.writeInt(p.getProVenduti());
            file.close();                                            
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file");
        }
    }
    public String cercaProdottoFile(Prodotto p, String idCercato) {
        try {
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "r");
            int nRecord = (int) (file.length() / dimRecordProdotto);

            for (int i = 0; i < nRecord; i++) {
                file.seek(i * dimRecordProdotto);
                file.writeChars(aggiustaLunghezzaStringa(p.getProId()));
                file.writeChars(aggiustaLunghezzaStringa(p.getProNome()));
                file.writeDouble(p.getProPrezzoAcq());
                file.writeDouble(p.getProPrezzovendite());
                file.writeInt(p.getProScorta());
                file.writeInt(p.getProScortaMin());
                file.writeInt(p.getProVenduti());
                if (p.getProId().equals(idCercato)) {
                    file.close();
                    return p.toString();
                }
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Errore lettura studente: " + e.getMessage());
        }
        return null;
    }

    public void eliminaProdottoFile(Prodotto p) {
        try {
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "r");
            if (checkStato(p.getStato()) == 0) {
                int nRecord = (int) (file.length() / dimRecordProdotto);
                for (int i = 0; i < nRecord; i++) {
                    file.seek(i * dimRecordProdotto);
                    
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura studente: " + e.getMessage());
        }
    }

    public int checkStato(int stato) {
        
    }
}
