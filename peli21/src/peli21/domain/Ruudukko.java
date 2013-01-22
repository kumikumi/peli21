/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

/**
 *
 * @author mikko
 */
public class Ruudukko {

    private Ruutu[][] ruudukko;
    private int leveys;
    private int korkeus;

    public Ruudukko(int leveys, int korkeus) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        ruudukko = new Ruutu[leveys][korkeus];
        alustaRuudut();
        // Todo: siirrä alustuslogiikka pelin vastuulle
    }

    private void alustaRuudut() {
        for (int y = 0; y < this.korkeus; y++) {
            for (int x = 0; x < this.leveys; x++) {
                ruudukko[x][y] = new Ruutu();
            }
        }
    }

    public Ruutu getRuutu(int x, int y) {
        return ruudukko[x][y];
    }

    public void setRuutu(int x, int y, Ruutu r) {
        ruudukko[x][y] = r;
    }
}
