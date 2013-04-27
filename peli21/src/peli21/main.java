/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21;

import java.io.File;
import javax.swing.SwingUtilities;
import peli21.domain.Ruudukko;
import peli21.domain.Ruutu;
import peli21.gui.Kayttoliittyma;
import peli21.highscore.Highscorelista;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here  
        System.out.println("Eeppinen ruudukkopeli on täällä!");

        Peli ruudukkopeli = new Peli("peli21_highscore.omgwtfbbq");
        Kayttoliittyma gui = new Kayttoliittyma(ruudukkopeli, 64, 120);
        SwingUtilities.invokeLater(gui);
        while (gui.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        ruudukkopeli.setPaivitettava(gui.getPiirtoalusta());
    }
}
