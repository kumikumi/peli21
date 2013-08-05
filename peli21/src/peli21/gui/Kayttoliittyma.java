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
import javax.swing.KeyStroke;
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
    private JMenuBar menubar;
    private int sivuPalkinLeveys;

    /**
     * Konstruktorissa Kayttoliittyma saa pelinä käytettävän
     * <code>Peli</code>-olion sekä tiedon yhden peliruudun sivun pituudesta.
     *
     * @param peli
     * @param sivunPituus
     */
    public Kayttoliittyma(Peli peli, int sivunPituus, int sivuPalkinLeveys) {
        this.peli = peli;
        this.sivunPituus = sivunPituus;
        this.sivuPalkinLeveys = sivuPalkinLeveys;
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
        luoKomponentit(frame.getContentPane());
        piirtoalusta.setPreferredSize(new Dimension(640, 480));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    /**
     * Päivittää ikkunan koon vastaamaan pelin ruudukon mittoja.
     */
    public void paivitaKoko() {
        // frame.pack lakkaa näköjään toimimasta kokonaan, jos sitä käytetään kun koko ei ole muuttunut.
        // siispä tehtiin purukumisysteemi jolla tarkistetaan, tarvitseeko ruudukon kokoa muuttaa.
        boolean korkeusMuuttui = peli.getRuudukko().getKORKEUS()*sivunPituus != piirtoalusta.getHeight();
        boolean leveysMuuttui = peli.getRuudukko().getLEVEYS()*sivunPituus+sivuPalkinLeveys != piirtoalusta.getWidth();
        if (korkeusMuuttui || leveysMuuttui) {
        frame.setResizable(true);
        int leveys = (peli.getRuudukko().getLEVEYS()) * sivunPituus + sivuPalkinLeveys;
        int korkeus = (peli.getRuudukko().getKORKEUS()) * sivunPituus;
        piirtoalusta.setPreferredSize(new Dimension(leveys, korkeus));
        frame.pack();
        frame.setResizable(false);
        }
    }

    private void luoKomponentit(Container contentPane) {
        //Luodaan ensin piirtoalusta, lisätään se container-olioon
        //Sitten luodaan näppäimistönkuuntelija ja annetaan se framelle
        //ja luodaan vielä hauska menubar kaupan päälle
        piirtoalusta = new Piirtoalusta(peli, sivunPituus, sivuPalkinLeveys);
        contentPane.add(piirtoalusta);

        frame.addKeyListener(new Nappaimistonkuuntelija(peli));

        JMenu gameMenu = new JMenu("Game");
        menubar = new JMenuBar();
        menubar.add(gameMenu);

        JMenuItem newAction = new JMenuItem("New game");
        newAction.addActionListener(new UusiPeliKuuntelija(peli, this));
        gameMenu.add(newAction);
        
        newAction.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, 0));

        JMenuItem highScoreAction = new JMenuItem("High score...");
        highScoreAction.addActionListener(new HighScoreIkkuna(peli));
        gameMenu.add(highScoreAction);

        frame.setJMenuBar(menubar);
    }
}
