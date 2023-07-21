/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Cine.Indice;

import Archivo.Nodo;
import Cine.Cine;
import static Main.MenuPrincipal.getCine;

/**
 *
 * @author David Sandoval Salvador
 */
public class ListarIndicePrimario extends javax.swing.JInternalFrame {

    Cine cine = getCine();

    /**
     * Creates new form ListarIndicePrimario
     */
    public ListarIndicePrimario() {
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

        btnListarIndicePrimario = new javax.swing.JButton();

        setClosable(true);

        btnListarIndicePrimario.setText("Listar Indice");
        btnListarIndicePrimario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarIndicePrimarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(btnListarIndicePrimario)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnListarIndicePrimario)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarIndicePrimarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarIndicePrimarioActionPerformed
        System.out.println("Listado del Indice Primario en Memoria");
        int i = 0;
        for (Nodo n : cine.getIndicePrimario()) {
            System.out.println("pos " + i + ": " + n.toString());
            i++;
        }
    }//GEN-LAST:event_btnListarIndicePrimarioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnListarIndicePrimario;
    // End of variables declaration//GEN-END:variables
}