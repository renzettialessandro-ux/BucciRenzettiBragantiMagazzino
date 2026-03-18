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

    public void aggiungiCombinazione(String proId, int posizione) {
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
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw");
            int nRecord = (int) (file.length() / dimRecordProdotto);

            for (int i = 0; i < nRecord; i++) {
                file.seek(i * dimRecordProdotto);
                String idLetto = leggiChars(file, 20).replace("*", "").trim();

                if (idLetto.equals(p.getProId())) {
                    // 1) Azzera il record nel file binario
                    file.seek(i * dimRecordProdotto);
                    file.writeChars("********************"); // proId
                    file.writeChars("********************"); // proNome
                    file.writeDouble(0.0);
                    file.writeDouble(0.0);
                    file.writeInt(0);
                    file.writeInt(0);
                    file.writeInt(0);

                    // 2) Aggiorna lo stato nel file sequenziale
                    aggiornaStatoFile(p.getProId(), 0);
                    break;
                }
            }
            file.close();
        } catch (IOException e) {
            System.out.println("Errore eliminazione: " + e.getMessage());
        }
    }

    private void aggiornaStatoFile(String idCercato, int nuovoStato) {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("key.txt"));
            StringBuilder contenuto = new StringBuilder();
            String riga;

            while ((riga = br.readLine()) != null) {
                // formato riga: proId;posizione;stato
                int primoPunto = riga.indexOf(";");
                int secondoPunto = riga.indexOf(";", primoPunto + 1);

                String id = riga.substring(0, primoPunto);
                String posizione = riga.substring(primoPunto + 1, secondoPunto);
                String stato = riga.substring(secondoPunto + 1);

                if (id.equals(idCercato)) {
                    stato = String.valueOf(nuovoStato);
                }

                contenuto.append(id).append(";").append(posizione).append(";").append(stato).append("\n");
            }
            br.close();

            java.io.FileWriter fw = new java.io.FileWriter("key.txt", false);
            fw.write(contenuto.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println("Errore aggiornamento stato: " + e.getMessage());
        }
    }

    private String leggiChars(RandomAccessFile file, int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(file.readChar());
        }
        return sb.toString();
    }
}
