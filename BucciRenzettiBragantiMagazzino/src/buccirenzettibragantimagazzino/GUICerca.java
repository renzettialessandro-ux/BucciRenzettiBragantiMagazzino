package buccirenzettibragantimagazzino;

public class GUICerca extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
        java.util.logging.Logger.getLogger(GUICerca.class.getName());

    private final GestioneFile gf;

    public GUICerca() {
        this.gf = new GestioneFile();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnCerca = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(284, 140));
        setPreferredSize(new java.awt.Dimension(284, 140));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("ID");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(txtId, gridBagConstraints);

        btnCerca.setText("Cerca");
        btnCerca.addActionListener(e -> cerca());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        getContentPane().add(btnCerca, gridBagConstraints);

        pack();
    }

    private void cerca() {
        String idCercato = txtId.getText().trim();
        if (idCercato.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Inserire un ID!");
            return;
        }
        String risultato = gf.cercaProdottoFile(idCercato);
        if (risultato == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Prodotto non trovato o eliminato.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, risultato);
        }
    }

    // Variables declaration
    private javax.swing.JButton btnCerca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtId;
}
