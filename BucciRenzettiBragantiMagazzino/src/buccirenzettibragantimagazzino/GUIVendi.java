/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package buccirenzettibragantimagazzino;

/**
 *
 * @author braganti.alessandro
 */

public class GUIVendi extends javax.swing.JFrame {

    /**
     * attributi
     */
    private final GestioneFile gf;
    private final Tabella tb;

    /**
     * costruttore
     * @param gf gestore dei file
     * @param tb tabella
     */
    public GUIVendi(GestioneFile gf, Tabella tb) {
        this.gf = gf;
        this.tb = tb;
        initComponents();
        btnVendi.addActionListener(e -> vendi());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtScorteVendere = new javax.swing.JTextField();
        btnVendi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(253, 148));
        setPreferredSize(new java.awt.Dimension(253, 148));

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[]{0, 30, 0, 30, 0, 30, 0};
        layout.rowHeights = new int[]{0, 2, 0, 2, 0};
        getContentPane().setLayout(layout);

        jLabel3.setText("ID prodotto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 4, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        getContentPane().add(txtId, gridBagConstraints);

        jLabel4.setText("Numero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 3);
        getContentPane().add(txtScorteVendere, gridBagConstraints);

        btnVendi.setText("Vendi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 5, 0);
        getContentPane().add(btnVendi, gridBagConstraints);

        pack();
    }

    /**
     * metodo per vendere il prodotto
     */
    private void vendi() {
        String idCercato = txtId.getText().trim();

        if (idCercato.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Inserire un ID valido!");
            return;
        }

        int quantita;
        try {
            quantita = Integer.parseInt(txtScorteVendere.getText().trim());
            if (quantita <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Inserire un numero valido e positivo!");
            return;
        }

        String risultato = gf.cercaProdottoFile(idCercato);
        if (risultato == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Prodotto non trovato o già eliminato!");
            return;
        }

        boolean ok = gf.aggiornaProdottoFile(idCercato, quantita);
        if (ok) {
            tb.aggiorna();
            javax.swing.JOptionPane.showMessageDialog(this, "Vendita completata!\n" + risultato);
            this.dispose();
        }
    }

    // Variables declaration
    private javax.swing.JButton btnVendi;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtScorteVendere;
}
