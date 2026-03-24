/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccirenzettibragantimagazzino;

/**
 *
 * @author renzetti.alessandro
 */
import java.io.*;
import java.util.ArrayList;

public class GestioneFile {

    /**
     * attributi
     */
    private final int dimRecordProdotto = 108;

    /**
     * costruttore
     */
    public GestioneFile() {
    }

    /**
     * metodo per sistemare la stringa
     *
     * @param s stringa
     * @return stringa sistemata
     */
    public String aggiustaLunghezzaStringa(String s) {
        if (s.length() >= 20) {
            return s.substring(0, 20);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < 20) {
            sb.append('*');
        }
        return sb.toString();
    }

    /**
     * metodo per aggiungere la combinazione (proId;posizione in acesso diretto)
     * in un file sequenziale
     *
     * @param proId id prodotto
     * @param posizione posizione riferente al file ad accesso diretto
     */
    public void aggiungiCombinazione(String proId, int posizione) {
        try (FileWriter fw = new FileWriter("key.txt", true)) {
            fw.write(proId + ";" + posizione + ";1\n");
        } catch (IOException e) {
            System.out.println("Errore scrittura combinazioni: " + e.getMessage());
        }
    }

    /**
     * metodo per aggiungere profotto nel file ad accesso diretto
     *
     * @param p prodotto da aggiungere
     */
    public void aggiungiProdottoFile(Prodotto p) {
        try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw")) {
            int nRecord = (int) (file.length() / dimRecordProdotto);
            file.seek((long) nRecord * dimRecordProdotto);
            file.writeChars(aggiustaLunghezzaStringa(p.getProId()));
            file.writeChars(aggiustaLunghezzaStringa(p.getProNome()));
            file.writeDouble(p.getProPrezzoAcq());
            file.writeDouble(p.getProPrezzovendite());
            file.writeInt(p.getProScorta());
            file.writeInt(p.getProScortaMin());
            file.writeInt(p.getProVenduti());
            aggiungiCombinazione(p.getProId(), nRecord);
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Problema in lettura-scrittura file: " + e.getMessage());
        }
    }

    /**
     * metodo per cercare il prodotto tramite id
     *
     * @param idCercato id del prodotto da cercare
     * @return il "toString" del prodotto cercato
     */
    public String cercaProdottoFile(String idCercato) {
        int posizione = cercaPosizione(idCercato);
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
                    + ", PrezzoAcq: " + prezzoAcq
                    + ", PrezzoVen: " + prezzoVen
                    + ", Scorta: " + scorta
                    + ", ScortaMin: " + scortaMin
                    + ", Venduti: " + venduti;
        } catch (IOException e) {
            System.out.println("Errore lettura prodotto: " + e.getMessage());
        }
        return null;
    }

    /**
     * metodo per eliminare un prodotto dal file ad accesso diretto
     *
     * @param idCercato id da cercare del prodotto da eliminare
     */
    public void eliminaProdottoFile(String idCercato) {
        int posizione = cercaPosizione(idCercato);

        if (posizione == -1) {
            System.out.println("Prodotto non trovato o già eliminato");
            return;
        }

        try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw")) {
            file.seek((long) posizione * dimRecordProdotto);
            for (int i = 0; i < 20; i++) {
                file.writeChar('*');
            }
            for (int i = 0; i < 20; i++) {
                file.writeChar('*');
            }
            file.writeDouble(0.0);
            file.writeDouble(0.0);
            file.writeInt(0);
            file.writeInt(0);
            file.writeInt(0);
        } catch (IOException e) {
            System.out.println("Errore scrittura magazzino: " + e.getMessage());
            return;
        }

        aggiornaStatoFile(idCercato, 0);
    }

    /**
     * metodo per cambare il valore delle scorte nel file
     *
     * @param idCercato id del prodotto
     * @param quantitaVenduta quantità da togliere
     * @return
     */
    public boolean aggiornaProdottoFile(String idCercato, int quantitaVenduta) {
        int posizione = cercaPosizione(idCercato);
        if (posizione == -1) {
            return false;
        }

        try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "rw")) {
            file.seek((long) posizione * dimRecordProdotto);
            String id = leggiChars(file, 20);
            String nome = leggiChars(file, 20);
            double prezzoAcq = file.readDouble();
            double prezzoVen = file.readDouble();
            int scorta = file.readInt();
            int scortaMin = file.readInt();
            int venduti = file.readInt();

            if (scorta - quantitaVenduta < scortaMin) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "Scorta insufficiente! Minimo: " + scortaMin + ", Disponibili: " + scorta);
                return false;
            }

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

    /**
     * Metodo privato di supporto: cerca la posizione nel file binario leggendo
     * key.txt. Restituisce -1 se non trovato o non attivo.
     */
    private int cercaPosizione(String idCercato) {
        try (BufferedReader br = new BufferedReader(new FileReader("key.txt"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.split(";");
                if (parti.length < 3) {
                    continue;
                }
                if (parti[0].equals(idCercato) && parti[2].trim().equals("1")) {
                    return Integer.parseInt(parti[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura key.txt: " + e.getMessage());
        }
        return -1;
    }

    /**
     * metodo per cambiare lo stato del prodotto nel file
     *
     * @param idCercato id prodotto
     * @param nuovoStato 1 se integro,0 se eliminato o modificato
     */
    private void aggiornaStatoFile(String idCercato, int nuovoStato) {
        StringBuilder contenuto = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("key.txt"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.split(";");
                if (parti.length < 3) {
                    continue;  // riga malformata: salta
                }
                String id = parti[0];
                String posizione = parti[1];
                String stato = id.equals(idCercato)
                        ? String.valueOf(nuovoStato)
                        : parti[2].trim();
                contenuto.append(id).append(";")
                        .append(posizione).append(";")
                        .append(stato).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Errore lettura stato: " + e.getMessage());
            return;
        }

        try (FileWriter fw = new FileWriter("key.txt", false)) {
            fw.write(contenuto.toString());
        } catch (IOException e) {
            System.out.println("Errore aggiornamento stato: " + e.getMessage());
        }
    }

    /**
     * metodo per leggere i campi nel file ad accesso diretto
     *
     * @param file file da dove leggere
     * @param n lunghezza file
     * @return campi letti
     * @throws IOException
     */
    private String leggiChars(RandomAccessFile file, int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(file.readChar());
        }
        return sb.toString();
    }

    /**
     * metodo per leggere tutti i prodotti nel file
     *
     * @return la lista di prodotti letti
     */
    public ArrayList<Prodotto> leggiTuttiProdotti() {
        ArrayList<Prodotto> lista = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile("magazzino.fgm", "r")) {
            int nRecord = (int) (file.length() / dimRecordProdotto);
            for (int i = 0; i < nRecord; i++) {
                file.seek((long) i * dimRecordProdotto);
                String pId = leggiChars(file, 20).replace("*", "").trim();
                String pNome = leggiChars(file, 20).replace("*", "").trim();
                double pAcq = file.readDouble();
                double pVen = file.readDouble();
                int scorta = file.readInt();
                int scortaMin = file.readInt();
                int venduti = file.readInt();

                if (!pId.isEmpty()) {
                    Prodotto p = new Prodotto(pId, pNome, pAcq, scorta);
                    p.setProPrezzovendite(pVen);
                    p.setProScortaMin(scortaMin);
                    p.setProVenduti(venduti);
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Errore lettura lista prodotti: " + e.getMessage());
        }
        return lista;
    }
}
