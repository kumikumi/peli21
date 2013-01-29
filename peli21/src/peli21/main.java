/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21;

import javax.swing.SwingUtilities;
import peli21.domain.Ruudukko;
import peli21.domain.Ruutu;
import peli21.gui.Kayttoliittyma;
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
        System.out.println("Seisotte nyt paikalla, johon on hyvää vauhtia valmistumassa eeppinen ruudukkopeli.");

        Peli ruudukkopeli = new Peli(20, 10);
        Kayttoliittyma gui = new Kayttoliittyma(ruudukkopeli, 40);
        SwingUtilities.invokeLater(gui);
        while (gui.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        ruudukkopeli.setPaivitettava(gui.getPiirtoalusta());
//        
//        System.out.println("Tehtiin ruudukko mitoilla (5, 4)");
//        Ruudukko ruudukko1 = new Ruudukko(5, 4);
//        
//        ruudukko1.setRuutu(2, 3, new Ruutu(true, true, false, false));
//        ruudukko1.getRuutu(3, 3).setDown(true);
//        
//        Ruutu ruutu1 = ruudukko1.getRuutu(2, 3);
//        System.out.println("Ruudukon ruutu (2,3): ");
//        System.out.println("UP: " + ruutu1.isUp());
//        System.out.println("DOWN: " + ruutu1.isDown());
//        System.out.println("LEFT: " + ruutu1.isLeft());
//        System.out.println("RIGHT: " + ruutu1.isRight());
//        
//        Ruutu ruutu2 = ruudukko1.getRuutu(3, 3);
//        System.out.println("Ruudukon ruutu (3,3): ");
//        System.out.println("UP: " + ruutu2.isUp());
//        System.out.println("DOWN: " + ruutu2.isDown());
//        System.out.println("LEFT: " + ruutu2.isLeft());
//        System.out.println("RIGHT: " + ruutu2.isRight());
    }
}
