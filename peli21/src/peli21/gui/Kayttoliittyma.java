/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Peli peli;
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;

    public Kayttoliittyma(Peli peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    @Override
    public void run() {

        frame = new JFrame("Peli 21");
        int leveys = (peli.getRuudukko().getLEVEYS()) * sivunPituus + 100;
        int korkeus = (peli.getRuudukko().getKORKEUS() + 1) * sivunPituus + 10;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container contentPane) {
        //Luodaan ensin piirtoalusta, lisätään se container-olioon
        //Sitten luodaan näppäimistönkuuntelija ja annetaan se framelle
        piirtoalusta = new Piirtoalusta(peli, sivunPituus);
        contentPane.add(piirtoalusta);
        frame.addKeyListener(new Nappaimistonkuuntelija(peli));
    }
}
