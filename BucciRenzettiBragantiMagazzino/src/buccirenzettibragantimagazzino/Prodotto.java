package buccirenzettibragantimagazzino;

public class Prodotto {

    private String proId;
    private String proNome;
    private double proPrezzoAcq;
    private double proPrezzovendite;
    private int proScorta;
    private int proScortaMin;
    private int proVenduti;
    private int stato;

    public Prodotto(String proId, String proNome, double proPrezzoAcq, int proScorta) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrezzoAcq = proPrezzoAcq;
        this.proPrezzovendite = proPrezzoAcq * 1.23;
        this.proScorta = proScorta;
        this.proScortaMin = 5;
        this.stato = 1;
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

    @Override
    public String toString() {
        return "Prodotto{" + "proId=" + proId + ", proNome=" + proNome + ", proPrezzoAcq=" + proPrezzoAcq + ", proPrezzovendite=" + proPrezzovendite + ", proScorta=" + proScorta + ", proScortaMin=" + proScortaMin + ", proVenduti=" + proVenduti + ", stato=" + stato + '}';
    }

}
