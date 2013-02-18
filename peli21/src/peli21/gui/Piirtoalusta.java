/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.EnumMap;
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
    private Ruutu[][] taulukko;
    private Pelihahmo pelihahmo;
    private Font font;
    private Map<Suunta, Image> kuvat;
    private Map<Suunta, Image> bonuskuvat;
    private Image splashKuva;
    private Image tausta;
    private int maksimiaika;

    public Piirtoalusta(Peli peli, int palanSivunPituus) {
        this.peli = peli;
        this.sivunPituus = palanSivunPituus;
        this.font = new Font("Serif", Font.PLAIN, 16);
        this.kuvat = new EnumMap<Suunta, Image>(Suunta.class);
        this.bonuskuvat = new EnumMap<Suunta, Image>(Suunta.class);
        lataaKuvat();
    }

    private void lataaKuvat() {
        try {
            splashKuva = ImageIO.read(this.getClass().getResource("/splash.png"));
            tausta = ImageIO.read((this.getClass().getResource("/background.png")));
            kuvat.put(Suunta.YLOS, ImageIO.read(this.getClass().getResource("/up_new.png")));
            kuvat.put(Suunta.ALAS, ImageIO.read(this.getClass().getResource("/down_new.png")));
            kuvat.put(Suunta.OIKEA, ImageIO.read(this.getClass().getResource("/right_new.png")));
            kuvat.put(Suunta.VASEN, ImageIO.read(this.getClass().getResource("/left_new.png")));
            bonuskuvat.put(Suunta.YLOS, ImageIO.read(this.getClass().getResource("/up_bonus.png")));
            bonuskuvat.put(Suunta.ALAS, ImageIO.read(this.getClass().getResource("/down_bonus.png")));
            bonuskuvat.put(Suunta.OIKEA, ImageIO.read(this.getClass().getResource("/right_bonus.png")));
            bonuskuvat.put(Suunta.VASEN, ImageIO.read(this.getClass().getResource("/left_bonus.png")));
        } catch (IOException ex) {
            System.out.println("asdf");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!peli.isAlkanut()) {
            g.drawImage(splashKuva, 0, 0, this);
            return;
        }
        super.paintComponent(g);
        piirraRuudukko(g);
        piirraPelihahmo(g);
        piirraHud(g);
    }

    private void piirraRuudukko(Graphics g) {
        if (peli.getBonusLaskuri() > 0 && peli.jatkuu()) {
            g.setColor(new Color(0x050050));
        } else {
        g.setColor(Color.BLACK);
        }
        for (int x = 0; x < peliruudukko.getLEVEYS(); x++) {
            for (int y = 0; y < peliruudukko.getKORKEUS(); y++) {
                g.fill3DRect(x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, true);
                g.drawImage(tausta, x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                for (Suunta s : kuvat.keySet()) {
                    switch (taulukko[x][y].getTila(s)) {
                        case ON:
                            g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                            break;
                        case BONUS:
                            g.drawImage(bonuskuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                    }
//                    if (taulukko[x][y].getSuunta(s)) {
//                        //g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, this);
//                        g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
//                    }
                }
            }
        }
    }

    private void piirraPelihahmo(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(pelihahmo.getX() * sivunPituus, pelihahmo.getY() * sivunPituus, sivunPituus, sivunPituus);
    }

    private void piirraHud(Graphics g) {
        g.setFont(font);
        g.drawString(peli.getPelaajanNimi(), peliruudukko.getLEVEYS() * sivunPituus + 10, 20);
        g.drawString("SCORE: " + peli.getPisteet(), peliruudukko.getLEVEYS() * sivunPituus + 10, 40);
        //g.drawString("TIME: " + peli.getAika(), peliruudukko.getLEVEYS()*sivunPituus + 10, 60);
        g.drawString("TIME: ", peliruudukko.getLEVEYS() * sivunPituus + 10, 60);
        g.fill3DRect(peliruudukko.getLEVEYS() * sivunPituus + 10, 70, peli.getAika() * 100 / maksimiaika, 10, true);
        if (peli.getBonusLaskuri()>0) {
            g.setColor(Color.blue);
            g.drawString("BONUS: ", peliruudukko.getLEVEYS() * sivunPituus + 10, 100);
            g.fill3DRect(peliruudukko.getLEVEYS() * sivunPituus + 10, 110, peli.getBonusLaskuri()*2, 10, true);
        }
        
        
        if (!peli.jatkuu()) {
            g.setColor(Color.red);
            g.setFont(new Font("Sans", Font.PLAIN, 60));
            g.drawString("GAME OVER", peliruudukko.getLEVEYS() * sivunPituus / 2 - 150, peliruudukko.getKORKEUS() * sivunPituus / 2);
        }
    }

    /**
     * Tätä metodia pitää kutsua joka kerta, kun uusi peli on käynnistetty.
     */
    @Override
    public void paivitaKomponentit() {
        this.peliruudukko = peli.getRuudukko();
        this.taulukko = peliruudukko.getTaulukko();
        this.pelihahmo = peliruudukko.getPelaaja();
        this.maksimiaika = peli.getOletusAika();
    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
