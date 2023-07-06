/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author David Sandoval Salvador
 * @E-mail sandovaldavid2201@gmail.com
 * 
 */
public class Main {

    public static void main(String[] args) {
        try {
            MenuPrincipal menupricipal = new MenuPrincipal();
            menupricipal.setVisible(true);
            menupricipal.setLocationRelativeTo(null);
            //FlatLightFlatIJTheme.setup();
            FlatGrayIJTheme.setup();
            UIManager.setLookAndFeel(new FlatArcIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
