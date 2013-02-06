/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import peli21.Suunta;
import peli21.domain.Pelihahmo;
import peli21.domain.Ruudukko;
import peli21.domain.Ruutu;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class Piirtoalusta extends JPanel implements Paivitettava {
    private Peli peli;
    private int sivunPituus;
    private Ruudukko peliruudukko;
    private Pelihahmo pelihahmo;
    private Font font;
    private Image kuva;
    private Map<Suunta, Image> kuvat;

    public Piirtoalusta(Peli peli, int palanSivunPituus) {
        this.peli = peli;
        this.sivunPituus = palanSivunPituus;
        this.peliruudukko = peli.getRuudukko();
        this.pelihahmo = peliruudukko.getPelaaja();
        this.font = new Font("Serif", Font.PLAIN, 16);
        this.kuvat = new HashMap<Suunta, Image>();
        lataaKuvat();
    }

    private void lataaKuvat() {
        try {
            kuvat.put(Suunta.YLOS, ImageIO.read(new File("img/up_a.png")));
            kuvat.put(Suunta.ALAS, ImageIO.read(new File("img/down_a.png")));
            kuvat.put(Suunta.OIKEA, ImageIO.read(new File("img/right_a.png")));
            kuvat.put(Suunta.VASEN, ImageIO.read(new File("img/left_a.png")));
        } catch (IOException ex) {
            System.out.println("asdf");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Ruutu[][] ruudukko = peli.getRuudukko().getTaulukko();

        g.setColor(Color.BLACK);

        for (int x = 0; x < peliruudukko.getLEVEYS(); x++) {
            for (int y = 0; y < peliruudukko.getKORKEUS(); y++) {
                g.fill3DRect(x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, true);
                for (Suunta s : kuvat.keySet()) {
                    if (ruudukko[x][y].isSuunta(s)) {
                        g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, this);
                    }
                }
            }
        }

        g.setColor(Color.RED);
        g.fillOval(pelihahmo.getX() * sivunPituus, pelihahmo.getY() * sivunPituus, sivunPituus, sivunPituus);
        g.setFont(font);
        g.drawString("SCORE: " + peli.getPisteet(), peliruudukko.getLEVEYS() * sivunPituus + 10, 20);
        //g.drawString("SCORE: " + peli.getPisteet(), peliruudukko.getKORKEUS()/2, peliruudukko.getLEVEYS()*sivunPituus + 10);
    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
