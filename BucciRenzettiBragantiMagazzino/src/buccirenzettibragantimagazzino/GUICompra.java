/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buccirenzettibragantimagazzino;

import javax.swing.JOptionPane;

/**
 *
 * @author bucci.alex
 */
public class GUICompra extends javax.swing.JFrame {

    /**
     * attributi
     */
    private final GestioneFile gf;
    private final Tabella tb;

    /**
     * costruttore
     *
     * @param gf gestore del file
     * @param tb tabella
     */
    public GUICompra(GestioneFile gf, Tabella tb) {
        this.gf = gf;
        this.tb = tb;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrezzoAcquista = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        BtnCompra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compra");
        setMinimumSize(new java.awt.Dimension(298, 267));
        setPreferredSize(new java.awt.Dimension(298, 267));

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[]{0, 8, 0, 8, 0, 8, 0};
        layout.rowHeights = new int[]{0, 21, 0, 21, 0, 21, 0, 21, 0};
        getContentPane().setLayout(layout);

        jLabel3.setText("Nome prodotto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        getContentPane().add(txtNome, gridBagConstraints);

        jLabel6.setText("ID prodotto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jLabel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        getContentPane().add(txtId, gridBagConstraints);

        jLabel7.setText("Prezzo acquisto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        getContentPane().add(jLabel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        getContentPane().add(txtPrezzoAcquista, gridBagConstraints);

        jLabel8.setText("Numero da acquistare");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        getContentPane().add(jLabel8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        getContentPane().add(txtNumero, gridBagConstraints);

        BtnCompra.setText("Compra");
        BtnCompra.addActionListener(e -> {
            if (aggiungiProdotto()) {
                tb.aggiorna();
                this.dispose();
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 7;
        getContentPane().add(BtnCompra, gridBagConstraints);

        pack();
    }

    /**
     * metodo per aggiungere il prodotto
     *
     * @return se l'aggiunta è andata a buon fine
     */
    private boolean aggiungiProdotto() {
        try {
            String id = txtId.getText().trim();
            String nome = txtNome.getText().trim();
            if (id.isEmpty() || nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID e Nome non possono essere vuoti", "Errore", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            double prezzoAcquista = Double.parseDouble(txtPrezzoAcquista.getText().trim());
            int nProdotti = Integer.parseInt(txtNumero.getText().trim());
            if (nProdotti <= 0 || prezzoAcquista <= 0) {
                JOptionPane.showMessageDialog(this, "Prezzo e quantità devono essere positivi", "Errore", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            Prodotto p = new Prodotto(id, nome, prezzoAcquista, nProdotti);
            gf.aggiungiProdottoFile(p);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Inserire valori numerici validi", "Errore", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // Variables declaration
    private javax.swing.JButton BtnCompra;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPrezzoAcquista;
}
