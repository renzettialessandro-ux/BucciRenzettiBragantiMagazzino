/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettibragantimagazzino;

import java.io.*;
import java.util.*;

/**
 *
 * @author renzetti.alessandro
 */
public class GestioneFile {

    private int dimRecordProdotto = 108;

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
            fw.write(proId + ";" + posizione + ";" + 1 + "\n"); // 1 = attivo di default
            fw.close();
        } catch (IOException e) {
            System.out.println("Errore scrittura combinazioni");
        }
    }

    public void aggiungiProdottoFile(Prodotto p) {
        try {
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw");
            int nRecord = (int) (file.length() / dimRecordProdotto);
            System.out.println("nRecord prima di scrivere: " + nRecord); // DEBUG
            System.out.println("Lunghezza file: " + file.length());      // DEBUG

            file.seek((long) nRecord * dimRecordProdotto);
            file.writeChars(aggiustaLunghezzaStringa(p.getProId()));
            file.writeChars(aggiustaLunghezzaStringa(p.getProNome()));
            file.writeDouble(p.getProPrezzoAcq());
            file.writeDouble(p.getProPrezzovendite());
            file.writeInt(p.getProScorta());
            file.writeInt(p.getProScortaMin());
            file.writeInt(p.getProVenduti());
            aggiungiCombinazione(p.getProId(), nRecord);
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file");
        }
    }

    public String cercaProdottoFile(String idCercato) {
        int posizione = -1;

        try (BufferedReader br = new BufferedReader(new FileReader("key.txt"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.split(";");
                if (parti.length < 3) {
                    continue;
                }
                String id = parti[0];
                String stato = parti[2].trim();
                if (id.equals(idCercato) && stato.equals("1")) {
                    posizione = Integer.parseInt(parti[1]);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura key.txt: " + e.getMessage());
            return null;
        }

        if (posizione == -1) {
            return null;
        }

        try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "r")) {
            file.seek((long) posizione * dimRecordProdotto);
            String id = leggiChars(file, 20).replace("*", "").trim();
            String nome = leggiChars(file, 20).replace("*", "").trim();
            double prezzoAcq = file.readDouble();
            double prezzoVen = file.readDouble();
            int scorta = file.readInt();
            int scortaMin = file.readInt();
            int venduti = file.readInt();

            return "ID: " + id + ", Nome: " + nome
                    + ", PrezzoAcq: " + prezzoAcq + ", PrezzoVen: " + prezzoVen
                    + ", Scorta: " + scorta + ", ScortaMin: " + scortaMin
                    + ", Venduti: " + venduti;
        } catch (IOException e) {
            System.out.println("Errore lettura prodotto: " + e.getMessage());
        }
        return null;
    }

    public void eliminaProdottoFile(String idCercato) {
        try {
            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("key.txt"));
            String riga;
            int posizione = -1;

            while ((riga = br.readLine()) != null) {
                int primoPunto = riga.indexOf(";");
                int secondoPunto = riga.indexOf(";", primoPunto + 1);

                String id = riga.substring(0, primoPunto);
                String stato = riga.substring(secondoPunto + 1);

                if (id.equals(idCercato) && stato.equals("1")) {
                    posizione = Integer.parseInt(riga.substring(primoPunto + 1, secondoPunto));
                    break;
                }
            }
            br.close();

            if (posizione == -1) {
                System.out.println("Prodotto non trovato o già eliminato");
                return;
            }

            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw");
            file.seek((long) posizione * dimRecordProdotto);
            file.writeChars("********************");
            file.writeChars("********************");
            file.writeDouble(0.0);
            file.writeDouble(0.0);
            file.writeInt(0);
            file.writeInt(0);
            file.writeInt(0);
            file.close();
            aggiornaStatoFile(idCercato, 0);
        } catch (IOException e) {
            System.out.println("Errore eliminazione: " + e.getMessage());
        }
    }

<<<<<<< HEAD
   public void eliminaProdottoFile(String idCercato){
    try {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("key.txt"));
        String riga;
        int posizione = -1;
=======
    private void aggiornaStatoFile(String idCercato, int nuovoStato) {
        StringBuilder contenuto = new StringBuilder();
>>>>>>> 98e3e1da81f61508f2058b32131c08dd9483a526

        try (BufferedReader br = new BufferedReader(new FileReader("key.txt"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.split(";");
                if (parti.length < 3) {
                    String id = parti[0];
                    String posizione = parti[1];
                    String stato = id.equals(idCercato) ? String.valueOf(nuovoStato) : parti[2].trim();
                    contenuto.append(id).append(";")
                            .append(posizione).append(";")
                            .append(stato).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura stato: " + e.getMessage());
            return;
        }

<<<<<<< HEAD
        RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw");
        file.seek(posizione * dimRecordProdotto);
        file.writeChars("********************");
        file.writeChars("********************");
        file.writeDouble(0.0);
        file.writeDouble(0.0);
        file.writeInt(0);
        file.writeInt(0);
        file.writeInt(0);
        file.close();

        aggiornaStatoFile(idCercato, 0);

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
=======
        try (FileWriter fw = new FileWriter("key.txt", false)) {
>>>>>>> 98e3e1da81f61508f2058b32131c08dd9483a526
            fw.write(contenuto.toString());
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

    public ArrayList<Prodotto> leggiTuttiProdotti() {
        ArrayList<Prodotto> lista = new ArrayList<>();
        try {
            RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "r");
            int nRecord = (int) (file.length() / dimRecordProdotto);

            for (int i = 0; i < nRecord; i++) {
                file.seek(i * dimRecordProdotto);

                String pId = leggiChars(file, 20).replace("*", "").trim();
                String pNome = leggiChars(file, 20).replace("*", "").trim();
                double pAcq = file.readDouble();
                double pVen = file.readDouble();
                int scorta = file.readInt();
                int scortaMin = file.readInt();
                int venduti = file.readInt();

                // salta i record eliminati (id vuoto)
                if (!pId.isEmpty()) {
                    Prodotto p = new Prodotto(pId, pNome, pAcq, scorta);
                    p.setProPrezzovendite(pVen);
                    p.setProScortaMin(scortaMin);
                    p.setProVenduti(venduti);
                    lista.add(p);
                }
            }
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Errore lettura lista prodotti: " + e.getMessage());
        }
        return lista;
    }
public boolean aggiornaProdottoFile(String idCercato, int quantitaVenduta) {
    int posizione = -1;

    try (BufferedReader br = new BufferedReader(new FileReader("key.txt"))) {
        String riga;
        while ((riga = br.readLine()) != null) {
            String[] parti = riga.split(";");
            if (parti.length < 3) continue;
            if (parti[0].equals(idCercato) && parti[2].trim().equals("1")) {
                posizione = Integer.parseInt(parti[1]);
                break;
            }
        }
    } catch (IOException e) {
        System.out.println("Errore lettura key.txt: " + e.getMessage());
        return false;
    }

    if (posizione == -1) return false;

    try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw")) {
        file.seek((long) posizione * dimRecordProdotto);
        String id       = leggiChars(file, 20);
        String nome     = leggiChars(file, 20);
        double prezzoAcq = file.readDouble();
        double prezzoVen = file.readDouble();
        int scorta      = file.readInt();
        int scortaMin   = file.readInt();
        int venduti     = file.readInt();

        if (scorta - quantitaVenduta < scortaMin) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Scorta insufficiente! Minimo: " + scortaMin + ", Disponibili: " + scorta);
            return false;
        }

        // riscrivi il record aggiornato
        file.seek((long) posizione * dimRecordProdotto);
        file.writeChars(id);
        file.writeChars(nome);
        file.writeDouble(prezzoAcq);
        file.writeDouble(prezzoVen);
        file.writeInt(scorta - quantitaVenduta);
        file.writeInt(scortaMin);
        file.writeInt(venduti + quantitaVenduta);
    } catch (IOException e) {
        System.out.println("Errore aggiornamento prodotto: " + e.getMessage());
        return false;
    }

    return true;
}
}
