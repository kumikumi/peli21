/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import java.util.Random;
import peli21.Tila;
import peli21.Suunta;

/**
 * Ruudukko tuntee taulukollisen ruutuja ja pelihahmon. Ruudukko on vastuussa
 * siirtojen toteuttamisesta, ja raportoi tehtyjen siirtojen vaikutuksista.
 */
public class Ruudukko {

    private Ruutu[][] taulukko;
    private Pelihahmo pelaaja;
    private final int LEVEYS;
    private final int KORKEUS;
    private int[] hilight;

    /**
     * Konstruktorissa ruudukolle annetaan parametreina
     * <code>leveys</code> ja
     * <code>korkeus</code>. Ruudukko huolehtii ruutujen alustamisesta ja
     * pelihahmon luonnista.
     *
     * @param leveys
     * @param korkeus
     */
    public Ruudukko(int leveys, int korkeus) {
        this.LEVEYS = leveys;
        this.KORKEUS = korkeus;
        taulukko = new Ruutu[leveys][korkeus];
        alustaRuudut();
        this.pelaaja = new Pelihahmo(leveys / 2, korkeus / 2);
        for (int i = 0; i < 25; i++) {
            arvoTila(Tila.OFF);
        }
        arvoTila(Tila.BONUS);
        arvoTila(Tila.BONUS);

        hilight = new int[2];
        hilight[0] = -1;
        hilight[1] = -1;

    }

    private void alustaRuudut() {
        for (int y = 0; y < this.KORKEUS; y++) {
            for (int x = 0; x < this.LEVEYS; x++) {
                taulukko[x][y] = new Ruutu(Tila.ON, Tila.ON, Tila.ON, Tila.ON);
            }
        }
        poistaReunat();
    }

    private void arvoTila(Tila tila) {
        Random arpoja = new Random();

        //int y = arpoja.nextInt(this.KORKEUS);
        Suunta suunta;
        int x;
        int y;
        switch (arpoja.nextInt(4)) {
            case 0:
                suunta = Suunta.YLOS;
                x = arpoja.nextInt(this.LEVEYS);
                y = arpoja.nextInt(this.KORKEUS - 1);
                break;
            case 1:
                suunta = Suunta.ALAS;
                x = arpoja.nextInt(this.LEVEYS);
                y = arpoja.nextInt(this.KORKEUS - 1) + 1;
                break;
            case 2:
                suunta = Suunta.OIKEA;
                x = arpoja.nextInt(this.LEVEYS - 1) + 1;
                y = arpoja.nextInt(this.KORKEUS);
                break;
            case 3:
                suunta = Suunta.VASEN;
                x = arpoja.nextInt(this.LEVEYS - 1);
                y = arpoja.nextInt(this.KORKEUS);
                break;
            default:
                throw new IllegalStateException("Arpoja antoi muun luvun kuin 0-3");
        }
        if (taulukko[x][y].getTila(suunta) == Tila.BONUS) {
            arvoTila(tila);
        } else {
            taulukko[x][y].setSuunta(suunta, tila);
        }
    }

    private void poistaReunat() {
        for (int x = 0; x < this.LEVEYS; x++) {
            taulukko[x][0].setSuunta(Suunta.ALAS, Tila.OFF);
            taulukko[x][KORKEUS - 1].setSuunta(Suunta.YLOS, Tila.OFF);
        }
        for (int y = 0; y < this.KORKEUS; y++) {
            taulukko[0][y].setSuunta(Suunta.OIKEA, Tila.OFF);
            taulukko[LEVEYS - 1][y].setSuunta(Suunta.VASEN, Tila.OFF);
        }
    }

    public Ruutu[][] getTaulukko() {
        return taulukko;
    }

    public Pelihahmo getPelaaja() {
        return this.pelaaja;
    }

    public Ruutu getRuutu(int x, int y) {
        return taulukko[x][y];
    }

//    public void setRuutu(int x, int y, Ruutu r) {
//        ruudukko[x][y] = r;
//    }
    public int getLEVEYS() {
        return LEVEYS;
    }

    public int getKORKEUS() {
        return KORKEUS;
    }

    public int[] getHilight() {
        return this.hilight;
    }

    /**
     * Pelaajaa voi liikuttaa kerrallaan yhteen neljästä suunnasta. Ruudukko
     * saattaa muuttua tätä metodia kutsuttaessa.
     *
     * @param suunta Suunta, johon pelaajaa liikutetaan.
     * @return Kohderuudun tila sille suunnalle, johon liikuttiin. Tila      * saadaan <code>Tila</code>-luokasta ja voi      * olla <code>Tila.ON</code>, <code>Tila.OFF</code>      * tai <code>Tila.BONUS</code>. Jos yritettiin liikkua ulos
     * ruudukosta, palautetaan <code>Tila.OFF</code> ja pelaaja siirretään
     * takaisin.
     *
     */
    public Tila liikutaPelaajaa(Suunta suunta) {
        // TODO: muuta ruudukkoa pelaajan liikkumisen yhteydessä
        pelaaja.liikuta(suunta);
        if (this.pelaajaOnRuudukossa()) {
            Tila palautus = taulukko[pelaaja.getX()][pelaaja.getY()].getTila(suunta);
            taulukko[pelaaja.getX()][pelaaja.getY()].setSuunta(suunta, Tila.OFF);
            if (palautus == Tila.ON) {
                arvoTila(Tila.ON);
            }
            if (palautus == Tila.BONUS) {
                arvoTila(Tila.BONUS);
            }
            if (palautus == Tila.OFF) {
                hilight = new int[2];
                hilight[0] = pelaaja.getX();
                hilight[1] = pelaaja.getY();
                pelaaja.liikuta(suunta.vastakkainenSuunta());
            } else {
                hilight[0] = -1;
                hilight[1] = -1;
            }
            return palautus;
        }
        pelaaja.liikuta(suunta.vastakkainenSuunta());
        return Tila.OFF;
    }

    private boolean pelaajaOnRuudukossa() {
        return !(pelaaja.getX() < 0 || pelaaja.getX() >= this.LEVEYS || pelaaja.getY() < 0 || pelaaja.getY() >= this.KORKEUS);
    }
}
