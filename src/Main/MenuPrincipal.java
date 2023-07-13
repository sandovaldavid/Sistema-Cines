/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Main;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Sando
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Sistema_Cines
     */
    public MenuPrincipal() {
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

        jDialog1 = new javax.swing.JDialog();
        Escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        ActualizarCine = new javax.swing.JMenu();
        RegistrarCine = new javax.swing.JMenuItem();
        EliminarCine = new javax.swing.JMenuItem();
        MantenimientoCine = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de salas de cine");

        Escritorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 856, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        jMenuBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseMoved(evt);
            }
        });

        jMenu1.setText("Cine");

        ActualizarCine.setText("Actualizar");

        RegistrarCine.setText("Registrar");
        RegistrarCine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarCineActionPerformed(evt);
            }
        });
        ActualizarCine.add(RegistrarCine);

        EliminarCine.setText("Eliminar");
        EliminarCine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarCineActionPerformed(evt);
            }
        });
        ActualizarCine.add(EliminarCine);

        jMenu1.add(ActualizarCine);

        MantenimientoCine.setText("Mantenimiento");

        jMenuItem5.setText("jMenuItem5");
        MantenimientoCine.add(jMenuItem5);

        jMenu1.add(MantenimientoCine);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sala");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pelicula");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Proyeccion");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuBar1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1MouseMoved

    private void EliminarCineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarCineActionPerformed

    }//GEN-LAST:event_EliminarCineActionPerformed

    private void RegistrarCineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarCineActionPerformed


    }//GEN-LAST:event_RegistrarCineActionPerformed

    /**
     * @param args the command line arguments
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatArcIJTheme());
        java.awt.EventQueue.invokeLater(() -> {
            MenuPrincipal v = new MenuPrincipal();
            v.setVisible(true);
            v.setLocationRelativeTo(null);
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ActualizarCine;
    private javax.swing.JMenuItem EliminarCine;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenu MantenimientoCine;
    private javax.swing.JMenuItem RegistrarCine;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables
}
