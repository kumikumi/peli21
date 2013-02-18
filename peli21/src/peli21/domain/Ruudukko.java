/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Tila;
import peli21.Suunta;
/**
 *Ruudukko tuntee taulukollisen ruutuja ja pelihahmon. Ruudukko on vastuussa siirtojen toteuttamisesta, 
 * ja raportoi tehtyjen siirtojen vaikutuksista.
 */
public class Ruudukko {

    private Ruutu[][] taulukko;
    private Pelihahmo pelaaja;
    private final int LEVEYS;
    private final int KORKEUS;

    /**
     * Konstruktorissa ruudukolle annetaan parametreina <code>leveys</code> ja <code>korkeus</code>. Ruudukko huolehtii ruutujen alustamisesta ja pelihahmon luonnista.
     * @param leveys
     * @param korkeus 
     */
    public Ruudukko(int leveys, int korkeus) {
        this.LEVEYS = leveys;
        this.KORKEUS = korkeus;
        taulukko = new Ruutu[leveys][korkeus];
        alustaRuudut();
        this.pelaaja = new Pelihahmo(leveys / 2, korkeus / 2);
    }

    private void alustaRuudut() {
        for (int y = 0; y < this.KORKEUS; y++) {
            for (int x = 0; x < this.LEVEYS; x++) {
                taulukko[x][y] = new Ruutu(Tila.ON, Tila.ON, Tila.ON, Tila.ON);
            }
        }
        poistaReunat();
    }
    
    private void poistaReunat() {
        for (int x = 0; x<this.LEVEYS; x++) {
            taulukko[x][0].setSuunta(Suunta.ALAS, Tila.OFF);
            taulukko[x][KORKEUS-1].setSuunta(Suunta.YLOS, Tila.OFF);
        }
        for (int y = 0; y<this.KORKEUS; y++) {
            taulukko[0][y].setSuunta(Suunta.OIKEA, Tila.OFF);
            taulukko[LEVEYS-1][y].setSuunta(Suunta.VASEN, Tila.OFF);
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

    /**
     * Pelaajaa voi liikuttaa kerrallaan yhteen neljästä suunnasta. Ruudukko saattaa muuttua tätä metodia kutsuttaessa.
     * @param suunta Suunta, johon pelaajaa liikutetaan.
     * @return Kohderuudun tila sille suunnalle, johon liikuttiin. Tila saadaan <code>Tila</code>-luokasta ja voi olla <code>Tila.ON</code>, <code>Tila.OFF</code> tai <code>Tila.BONUS</code>. Jos yritettiin liikkua ulos ruudukosta, palautetaan <code>Tila.OFF</code> ja pelaaja siirretään takaisin.
     *
     */
    public Tila liikutaPelaajaa(Suunta suunta) {
        // TODO: muuta ruudukkoa pelaajan liikkumisen yhteydessä
        pelaaja.liikuta(suunta);
        if (this.pelaajaOnRuudukossa()) {
            Tila palautus = taulukko[pelaaja.getX()][pelaaja.getY()].getTila(suunta);
            taulukko[pelaaja.getX()][pelaaja.getY()].setSuunta(suunta, Tila.OFF);
            return palautus;
        }
        pelaaja.liikuta(suunta.vastakkainenSuunta());
        return Tila.OFF;
    }

    private boolean pelaajaOnRuudukossa() {
        return !(pelaaja.getX() < 0 || pelaaja.getX() >= this.LEVEYS || pelaaja.getY() < 0 || pelaaja.getY() >= this.KORKEUS);
    }
}
