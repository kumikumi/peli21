/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Effect;
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
                taulukko[x][y] = new Ruutu(true, true, true, true);
            }
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
     * @return Seuraus tehdylle siirrolle. Jos siirto oli laillinen, palautetaan <code>Effect.SUCCESS</code>.
     * Jos siirto oli laiton, palautetaan <code>Effect.DEATH</code>.
     */
    public Effect liikutaPelaajaa(Suunta suunta) {
        // TODO: muuta ruudukkoa pelaajan liikkumisen yhteydessä
        pelaaja.liikuta(suunta);
        if (this.pelaajaOnRuudukossa() && taulukko[pelaaja.getX()][pelaaja.getY()].isSuunta(suunta)){
            taulukko[pelaaja.getX()][pelaaja.getY()].setSuunta(suunta, false);
            return Effect.SUCCESS;
        }
        pelaaja.liikuta(suunta.vastakkainenSuunta());
        return Effect.DEATH;
    }

    private boolean pelaajaOnRuudukossa() {
        return !(pelaaja.getX() < 0 || pelaaja.getX() >= this.LEVEYS || pelaaja.getY() < 0 || pelaaja.getY() >= this.KORKEUS);
    }
}
