package sistemabsensi.admin.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author rss
 */
public class JPanelCetakLaporan extends javax.swing.JPanel {

	/**
	 * Creates new form JPanelCetakLaporan
	 */
	public JPanelCetakLaporan() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jComboBox1 = new javax.swing.JComboBox<>();
                jComboBox2 = new javax.swing.JComboBox<>();
                jButton1 = new javax.swing.JButton();
                jButton2 = new javax.swing.JButton();
                jPanel2 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();

                setLayout(new java.awt.GridLayout(1, 2));

                jPanel1.setLayout(null);

                jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                jPanel1.add(jComboBox1);
                jComboBox1.setBounds(101, 50, 160, 29);

                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                jPanel1.add(jComboBox2);
                jComboBox2.setBounds(338, 50, 160, 29);

                jButton1.setText("jButton1");
                jPanel1.add(jButton1);
                jButton1.setBounds(160, 500, 300, 70);

                jButton2.setText("jButton2");
                jPanel1.add(jButton2);
                jButton2.setBounds(160, 600, 300, 60);

                add(jPanel1);

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 548, Short.MAX_VALUE)
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 637, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(54, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(44, Short.MAX_VALUE))
                );

                add(jPanel2);
        }// </editor-fold>//GEN-END:initComponents


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JComboBox<String> jComboBox1;
        private javax.swing.JComboBox<String> jComboBox2;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        // End of variables declaration//GEN-END:variables
}
