/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import peli21.peli.Peli;

/**
 * Käyttöliittymäluokka, joka luo pelin pääikkunana käytettävän JFrame-olion,
 * asettaa sille koon ja luo siihen piirrettävät komponentit.
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Peli peli;
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;

    /**
     * Konstruktorissa Kayttoliittyma saa pelinä käytettävän
     * <code>Peli</code>-olion sekä tiedon yhden peliruudun sivun pituudesta.
     *
     * @param peli
     * @param sivunPituus
     */
    public Kayttoliittyma(Peli peli, int sivunPituus) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    /**
     * Runnable-rajapinnan toteuttamiseen vaadittu metodi, jossa luodaan
     * peli-ikkuna.
     */
    @Override
    public void run() {
        frame = new JFrame("Peli 21");
        frame.setPreferredSize(new Dimension(640, 520));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        luoKomponentit(frame.getContentPane());
        frame.setVisible(true);
    }

    /**
     * Päivittää ikkunan koon vastaamaan pelin ruudukon mittoja.
     */
    public void paivitaKoko() {
        int leveys = (peli.getRuudukko().getLEVEYS()) * sivunPituus + 120;
        int korkeus = (peli.getRuudukko().getKORKEUS() + 1) * sivunPituus + 10;
        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.pack();
    }

    private void luoKomponentit(Container contentPane) {
        //Luodaan ensin piirtoalusta, lisätään se container-olioon
        //Sitten luodaan näppäimistönkuuntelija ja annetaan se framelle
        //ja luodaan vielä hauska menubar kaupan päälle
        piirtoalusta = new Piirtoalusta(peli, sivunPituus);
        contentPane.add(piirtoalusta);

        frame.addKeyListener(new Nappaimistonkuuntelija(peli));

        JMenu gameMenu = new JMenu("Game");
        JMenuBar menubar = new JMenuBar();
        menubar.add(gameMenu);

        JMenuItem newAction = new JMenuItem("New game");
        newAction.addActionListener(new UusiPeliKuuntelija(peli, this));
        gameMenu.add(newAction);

        JMenuItem highScoreAction = new JMenuItem("High score...");
        highScoreAction.addActionListener(new HighScoreIkkuna(peli));
        gameMenu.add(highScoreAction);

        frame.setJMenuBar(menubar);
    }
}
