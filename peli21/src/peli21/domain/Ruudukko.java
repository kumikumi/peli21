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
    private Koordinaatit pelaaja;
    private Koordinaatit hilight;
    private final int LEVEYS;
    private final int KORKEUS;
    //private int[] hilight;

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
        this.pelaaja = new Koordinaatit(leveys / 2, korkeus / 2);
//        for (int i = 0; i < 25; i++) {
//            arvoTila(Tila.OFF);
//        }
        hilight = new Koordinaatit(-1, -1);
        for (int y = 0; y < this.KORKEUS; y++) {
            for (int x = 0; x < this.LEVEYS; x++) {
                taulukko[x][y] = new Ruutu(Tila.ON, Tila.ON, Tila.ON, Tila.ON);
            }
        }
    }

    /**
     * Tekee pelin kannalta tarpeellisen ruudukon alustamisen.
     */
    public void alustaRuudut() {
        arvoOffTilojaSuhteessaRuudukonKokoon();
        arvoBonuksiaSuhteessaRuudukonKokoon();
        poistaReunat();
    }

    private void arvoTila(Tila tila) {
        //Arpoo parametrina annetun tilan johonkin ruudukon nuolen paikalle
        //Poikkeuksena se, että ei koskaan bonuksen paikalle eikä koskaan reunalle
        Random arpoja = new Random();

        Suunta suunta;
        int x;
        int y;
        switch (arpoja.nextInt(4)) {
            case 0:
                suunta = Suunta.YLOS;
                //ei voi ilmestyä alalaitaan
                x = arpoja.nextInt(this.LEVEYS);
                y = arpoja.nextInt(this.KORKEUS - 1);
                break;
            case 1:
                suunta = Suunta.ALAS;
                //ei voi ilmestyä ylälaitaan
                x = arpoja.nextInt(this.LEVEYS);
                y = arpoja.nextInt(this.KORKEUS - 1) + 1;
                break;
            case 2:
                suunta = Suunta.OIKEA;
                //ei voi ilmestyä vasemmalle reunalle
                x = arpoja.nextInt(this.LEVEYS - 1) + 1;
                y = arpoja.nextInt(this.KORKEUS);
                break;
            case 3:
                suunta = Suunta.VASEN;
                //ei voi ilmestyä oikealle reunalle
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

    private void arvoOnTilojaSuhteessaRuudukonKokoon() {
        for (int i = 0; i < LEVEYS * KORKEUS; i = i + 40) {
            arvoTila(Tila.ON);
        }
    }

    private void arvoOffTilojaSuhteessaRuudukonKokoon() {
        for (int i = 0; i < LEVEYS * KORKEUS; i = i + 2) {
            arvoTila(Tila.OFF);
        }
    }

    private void arvoBonuksiaSuhteessaRuudukonKokoon() {
        for (int i = 0; i < LEVEYS * KORKEUS; i = i + 40) {
            arvoTila(Tila.BONUS);
        }
    }

    public Ruutu[][] getTaulukko() {
        return taulukko;
    }

    public Koordinaatit getPelaaja() {
        return this.pelaaja;
    }

    public Ruutu getRuutu(int x, int y) {
        return taulukko[x][y];
    }

    public int getLEVEYS() {
        return LEVEYS;
    }

    public int getKORKEUS() {
        return KORKEUS;
    }

    /**
     * Jos
     * <code>liikutaPelaajaa(Suunta suunta)</code> -metodia on kutsuttu tavalla,
     * joka saa aikaan laittoman siirron jossa ollaan yritetty siirtyä toiseen
     * ruutuun, tämä metodi palauttaa Koordinaatit-olion, jossa on viittaus
     * tuohon ruutuun. Jos viimeisin siirto ei ollut laiton tai jos yritettiin
     * liikkua ruudukon ulkopuolelle, tämä metodi palauttaa
     * <code>Koordinaatit</code> -olion, joka osoittaa paikkaan (-1, -1).
     *
     * @return Osoitus viime siirron yritettyyn kohderuutuun, mikäli siirto oli
     * laiton.
     */
    public Koordinaatit getHilight() {
        return this.hilight;
    }

    /**
     * Pelaajaa voi liikuttaa kerrallaan yhteen neljästä suunnasta. Ruudukko
     * saattaa muuttua tätä metodia kutsuttaessa.
     *
     * @param suunta Suunta, johon pelaajaa liikutetaan.
     * @return Kohderuudun tila sille suunnalle, johon liikuttiin. Tila *
     * saadaan <code>Tila</code>-luokasta ja voi * * *
     * olla <code>Tila.ON</code>, <code>Tila.OFF</code> * * *
     * tai <code>Tila.BONUS</code>. Jos yritettiin liikkua ulos ruudukosta,
     * palautetaan <code>Tila.OFF</code> ja pelaaja siirretään takaisin.
     *
     */
    public Tila liikutaPelaajaa(Suunta suunta) {
        pelaaja.liikuta(suunta);

        if (!this.pelaajaOnRuudukossa()) {
            pelaaja.liikuta(suunta.vastakkainenSuunta());
            hilight.setX(-1);
            hilight.setY(-1);
            return Tila.OFF;
        }

        Tila palautus = taulukko[pelaaja.getX()][pelaaja.getY()].getTila(suunta);
        taulukko[pelaaja.getX()][pelaaja.getY()].setSuunta(suunta, Tila.OFF);
        arvoOnTilojaSuhteessaRuudukonKokoon();
        reagoiPalautukseen(palautus, suunta);
        return palautus;
    }

    private void reagoiPalautukseen(Tila tila, Suunta suunta) {
        switch (tila) {
            case BONUS:
                arvoTila(Tila.BONUS);
            case ON:
                hilight.setX(-1);
                hilight.setY(-1);
                break;
            case OFF:
                hilight.setX(pelaaja.getX());
                hilight.setY(pelaaja.getY());
                pelaaja.liikuta(suunta.vastakkainenSuunta());
        }
    }

    private boolean pelaajaOnRuudukossa() {
        return !(pelaaja.getX() < 0 || pelaaja.getX() >= this.LEVEYS || pelaaja.getY() < 0 || pelaaja.getY() >= this.KORKEUS);
    }
}
