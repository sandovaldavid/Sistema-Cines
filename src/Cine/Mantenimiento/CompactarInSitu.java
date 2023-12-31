/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Cine.Mantenimiento;

import Cine.Cine;
import static Main.MenuPrincipal.getCine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Sandoval Salvador
 */
public class CompactarInSitu extends javax.swing.JInternalFrame {

    Cine cine = getCine();

    /**
     * Creates new form CompactarPorCopia
     */
    public CompactarInSitu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCompactarInsitu = new javax.swing.JButton();

        setClosable(true);
        setTitle("Clasificacion In-Situ");

        btnCompactarInsitu.setBackground(new java.awt.Color(0, 153, 51));
        btnCompactarInsitu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCompactarInsitu.setForeground(new java.awt.Color(255, 255, 255));
        btnCompactarInsitu.setText("Compactar");
        btnCompactarInsitu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompactarInsituActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnCompactarInsitu)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnCompactarInsitu)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCompactarInsituActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompactarInsituActionPerformed
        try {
            cine.CompactacionInSitu();
        } catch (IOException ex) {
            Logger.getLogger(CompactarInSitu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCompactarInsituActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCompactarInsitu;
    // End of variables declaration//GEN-END:variables
}
