/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sistemabsensi.ui.admin;

import sistemabsensi.ui.JFrameUtama;

/**
 *
 * @author rss
 */
public class JPanelWelcome extends javax.swing.JPanel {

	private JFrameUtama frameAdmin;

	/**
	 * Creates new form JPanelWelcome
	 */
	public JPanelWelcome(JFrameUtama frameAdmin) {
		this.frameAdmin = frameAdmin;
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

                jLabel1 = new javax.swing.JLabel();
                btnAbsen = new javax.swing.JButton();
                btnLoginAdmin = new javax.swing.JButton();

                jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
                jLabel1.setText("SISTEM ABSENSI");

                btnAbsen.setFont(new java.awt.Font("sansserif", 0, 48)); // NOI18N
                btnAbsen.setText("ABSEN");
                btnAbsen.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnAbsenActionPerformed(evt);
                        }
                });

                btnLoginAdmin.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
                btnLoginAdmin.setText("LOGIN ADMIN");
                btnLoginAdmin.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnLoginAdminActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(294, 294, 294)
                                                .addComponent(btnAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(167, 167, 167)
                                                .addComponent(btnLoginAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(538, 538, 538)
                                                .addComponent(jLabel1)))
                                .addContainerGap(317, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(138, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAbsen, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                        .addComponent(btnLoginAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(264, 264, 264))
                );
        }// </editor-fold>//GEN-END:initComponents

        private void btnAbsenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbsenActionPerformed
		this.frameAdmin.bukaTampilanAbsen();
        }//GEN-LAST:event_btnAbsenActionPerformed

        private void btnLoginAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginAdminActionPerformed
		this.frameAdmin.bukaLoginAdmin();
        }//GEN-LAST:event_btnLoginAdminActionPerformed


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnAbsen;
        private javax.swing.JButton btnLoginAdmin;
        private javax.swing.JLabel jLabel1;
        // End of variables declaration//GEN-END:variables
}
