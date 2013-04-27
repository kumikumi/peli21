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
import peli21.domain.Koordinaatit;
import peli21.domain.Ruudukko;
import peli21.domain.Ruutu;
import peli21.domain.KannustusGeneraattori;
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
    private Koordinaatit pelihahmo;
    private Koordinaatit hilight;
    private Font font;
    private Map<Suunta, Image> kuvat;
    private Map<Suunta, Image> bonuskuvat;
    private Image splashKuva;
    private Image tausta;
    private Image bonustausta;
    private int maksimiaika;
    private KannustusGeneraattori tauntGen;
    private String endGameMessage;
    private String timeUpMessage;
    private String deadlockMessage;
    private Color hilightColor;
    private Color bonusColor;

    public Piirtoalusta(Peli peli, int palanSivunPituus) {
        this.peli = peli;
        this.sivunPituus = palanSivunPituus;
        this.font = new Font("Serif", Font.PLAIN, 16);
        this.kuvat = new EnumMap<Suunta, Image>(Suunta.class);
        this.bonuskuvat = new EnumMap<Suunta, Image>(Suunta.class);
        this.tauntGen = new KannustusGeneraattori();
        //this.hilightColor = new Color(0xF06C00);
        this.hilightColor = new Color((float) 0.94, (float) 0.43, (float) 0.0, (float) 0.5);
        //this.hilightColor = new Color((float) 0.9, (float) 0.94, (float) 0.43, (float) 0.0);
        this.bonusColor = new Color(0x050050);
        lataaKuvat();
    }

    private void lataaKuvat() {
        try {
            splashKuva = ImageIO.read(this.getClass().getResource("/splash.png"));
            tausta = ImageIO.read((this.getClass().getResource("/tausta-64px.png")));
            bonustausta = ImageIO.read((this.getClass().getResource("/tausta-bonus-64px.png")));
            kuvat.put(Suunta.YLOS, ImageIO.read(this.getClass().getResource("/yellow-up-64px.png")));
            kuvat.put(Suunta.ALAS, ImageIO.read(this.getClass().getResource("/yellow-down-64px.png")));
            kuvat.put(Suunta.OIKEA, ImageIO.read(this.getClass().getResource("/yellow-right-64px.png")));
            kuvat.put(Suunta.VASEN, ImageIO.read(this.getClass().getResource("/yellow-left-64px.png")));
            bonuskuvat.put(Suunta.YLOS, ImageIO.read(this.getClass().getResource("/blue-up-64px.png")));
            bonuskuvat.put(Suunta.ALAS, ImageIO.read(this.getClass().getResource("/blue-down-64px.png")));
            bonuskuvat.put(Suunta.OIKEA, ImageIO.read(this.getClass().getResource("/blue-right-64px.png")));
            bonuskuvat.put(Suunta.VASEN, ImageIO.read(this.getClass().getResource("/blue-left-64px.png")));
        } catch (IOException ex) {
            System.err.println("Jotain meni pieleen kuvia ladattaessa!");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!peli.isAlkanut()) {
            g.drawImage(splashKuva, 0, 0, this);
            return;
        }

        super.paintComponent(g);
        if (peli.getBonusLaskuri() > 0) {
            piirraBonusRuudukko(g);
        } else {
            piirraRuudukko(g);
            if (hilight.getX() != -1) {
                piirraHilight(g);
            }
        }
        piirraPelihahmo(g);
        piirraHud(g);
        if (!peli.jatkuu()) {
            piirraLoppuTeksti(g);
        }
    }

    private void piirraRuudukko(Graphics g) {

        for (int x = 0; x < peliruudukko.getLEVEYS(); x++) {
            for (int y = 0; y < peliruudukko.getKORKEUS(); y++) {
                g.drawImage(tausta, x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                for (Suunta s : kuvat.keySet()) {
                    switch (taulukko[x][y].getTila(s)) {
                        case ON:
                            g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                            break;
                        case BONUS:
                            g.drawImage(bonuskuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                    }
                }
            }
        }
    }
    
    private void piirraHilight(Graphics g) {
        g.setColor(hilightColor);
        g.fill3DRect(hilight.getX()*sivunPituus, hilight.getY()*sivunPituus, sivunPituus, sivunPituus, true);
    }

    private void piirraBonusRuudukko(Graphics g) {

        for (int x = 0; x < peliruudukko.getLEVEYS(); x++) {
            for (int y = 0; y < peliruudukko.getKORKEUS(); y++) {
                g.drawImage(bonustausta, x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                for (Suunta s : kuvat.keySet()) {
                    switch (taulukko[x][y].getTila(s)) {
                        case ON:
                            g.drawImage(kuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                            break;
                        case BONUS:
                            g.drawImage(bonuskuvat.get(s), x * sivunPituus, y * sivunPituus, sivunPituus, sivunPituus, this);
                    }
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
        g.drawString("TIME: ", peliruudukko.getLEVEYS() * sivunPituus + 10, 60);
        g.fill3DRect(peliruudukko.getLEVEYS() * sivunPituus + 10, 70, peli.getAika() * 100 / maksimiaika, 10, true);
        if (peli.getBonusLaskuri() > 0) {
            g.setColor(Color.blue);
            g.drawString("BONUS: ", peliruudukko.getLEVEYS() * sivunPituus + 10, 100);
            g.fill3DRect(peliruudukko.getLEVEYS() * sivunPituus + 10, 110, peli.getBonusLaskuri() * 5, 10, true);
        }


    }

    private void piirraLoppuTeksti(Graphics g) {
        g.setColor(new Color((float) 0.9, (float) 0.9, (float) 0.9, (float) 0.7));
        g.fillRect(0, 0, peliruudukko.getLEVEYS() * sivunPituus, peliruudukko.getKORKEUS() * sivunPituus);
        g.setColor(new Color(0xC70000));
        g.setFont(new Font("Sans", Font.PLAIN, 60));
        int pituus = (int) g.getFontMetrics().getStringBounds("GAME OVER", g).getWidth();
        g.drawString("GAME OVER", peliruudukko.getLEVEYS() / 2 - pituus / 2 + peliruudukko.getLEVEYS() * sivunPituus / 2, peliruudukko.getKORKEUS() * sivunPituus / 2);
        g.setFont(new Font("Serif", Font.ITALIC, 22));
        String lause;
        if (peli.getPisteet() > 5 && peli.pelaajaSaiEnnatyksen()) { //jos pelaaja sai ennätyksen joka ei ole säälittävän pieni
            lause = "Congratulations! New highscore: " + peli.getPisteet();
        } else if (peli.deadLock()) {
            lause = this.deadlockMessage;
        } else if (peli.getAika() == 0) {
            lause = this.timeUpMessage;
        } else {
            lause = this.endGameMessage;
        }
        pituus = (int) g.getFontMetrics().getStringBounds(lause, g).getWidth();
        g.drawString(lause, peliruudukko.getLEVEYS() / 2 - pituus / 2 + peliruudukko.getLEVEYS() * sivunPituus / 2, peliruudukko.getKORKEUS() * sivunPituus / 2 + 40);
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
        this.hilight = peliruudukko.getHilight();
        this.endGameMessage = tauntGen.arvoEndGameLause();
        this.timeUpMessage = tauntGen.arvoTimeUpLause();
        this.deadlockMessage = tauntGen.arvoDeadLockLause();
    }

    @Override
    public void paivita() {
        super.repaint();
    }
}
