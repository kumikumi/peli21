/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import peli21.Tila;
import peli21.Suunta;
import peli21.domain.Ruudukko;
import peli21.gui.Paivitettava;
import peli21.highscore.Highscorelista;

/**
 * Tämä luokka huolehtii pelin hallinnoimisesta ja pisteidenlaskusta. Luokka
 * tuntee pelilautana käytettävän
 * <code>Ruudukko</code>-olion, välittää sille pelihahmon siirtokäskyt, laskee
 * pisteet ja lopettaa pelin tarvittaessa. Peli siis ei ole tietoinen pelihahmon
 * paikasta tai ruudukon tilasta, se ainoastaan välittää ruudukolle pelihahmon
 * siirtokäskyt ja vastaanottaa palautuksena
 * <code>Effect</code>-enumin muodossa tiedon siirron seurauksesta.
 */
public class Peli implements ActionListener {

    private Highscorelista highscorelista;
    private String pelaajanNimi;
    private boolean alkanut;
    private boolean jatkuu;
    private Timer ajastin;
    private int oletusaika;
    private int aika;
    private int pistelaskuri;
    private Ruudukko ruudukko;
    private Paivitettava paivitettava;
    //private Pelihahmo pelaaja;

    /**
     * Konstruktorissa pelille tulee antaa tiedostopolun osoite, johon
     * highscorelista tallennetaan.
     *
     * @param tiedostopolku Tallennettavan highscorelistan relatiivinen
     * tiedostopolku.
     */
    public Peli(String tiedostopolku) {
        this.ajastin = new Timer(100, this);
        this.alkanut = false;
        this.highscorelista = new Highscorelista(10, tiedostopolku);
    }

    /**
     * Metodille annetaan parametreina tieto pelaajan nimestä ja peliruudukolle
     * annettavat mitat. Näitä mittoja käytetään sopivan
     * <code>Ruudukko</code>-olion luomiseen.
     *
     * @param pelaajanNimi Pelaajalle annettava nimi.
     * @param leveys Luotavan ruudukon leveys (ruutuina).
     * @param korkeus Luotavan ruudukon korkeus (ruutuina).
     */
    public void uusiPeli(String pelaajanNimi, int leveys, int korkeus, int aika) {
        this.alkanut = true;
        this.pelaajanNimi = pelaajanNimi;
        this.pistelaskuri = 0;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.oletusaika = aika;
        this.aika = aika;
        this.paivitettava.paivitaKomponentit();
        this.paivitettava.paivita();
        this.jatkuu = true;
        this.ajastin.start();
    }

    private void lopetaPeli() {
        this.ajastin.stop();
        jatkuu = false;
        highscorelista.lisaa(pelaajanNimi, pistelaskuri);
        highscorelista.tulosta();
    }

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    /**
     * @return Palauttaa <code>true</code> sen jälkeen,      * kun <code>uusiPeli</code>-metodia on kutsuttu ensimmäisen kerran.
     */
    public boolean isAlkanut() {
        return this.alkanut;
    }

    /**
     * @return Palauttaa <code>true</code> silloin, kun peli on käynnissä.
     * Muutoin palautetaan false. (Peli loppuu, kun pelaaja tekee laittoman
     * siirron.)
     */
    public boolean jatkuu() {
        return this.jatkuu;
    }

    public String getPelaajanNimi() {
        return this.pelaajanNimi;
    }

    public void setPaivitettava(Paivitettava piirtoalusta) {
        this.paivitettava = piirtoalusta;
    }

    /**
     * Liikuttaa pelaajaa parametrina annettuun suuntaan. Tämän metodin
     * kutsumisen yhteydessä tämä olio käskee
     * <code>Ruudukko</code>-oliota liikuttamaan pelihahmoa parametrina
     * annettuun suuntaan, ja siirron seurauksesta riippuen kasvattaa
     * pistemäärää yhdellä tai lopettaa pelin. Jos peli on jo päättynyt, siirtoa
     * ei tehdä.
     *
     * @param suunta Suunta, johon pelaajaa liikutetaan.
     */
    public void liikutaPelaajaa(Suunta suunta) {
        if (!jatkuu) {
            return;
        }
        reagoiPalautukseen(ruudukko.liikutaPelaajaa(suunta));
        this.paivitettava.paivita();
        if (!jatkuu) {
            return;
        }
        System.out.println(ruudukko.getPelaaja());
        System.out.println("Pisteet: " + pistelaskuri);
    }

    private void reagoiPalautukseen(Tila e) {
        switch (e) {
            case ON:
                pistelaskuri++;
                aika = oletusaika;
                break;
            case OFF:
                System.out.println("DEATH");
                lopetaPeli();
        }
    }

    public int getPisteet() {
        return this.pistelaskuri;
    }

    public int getAika() {
        return this.aika;
    }

    public int getOletusAika() {
        return this.oletusaika;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuu) {
            return;
        }
        // @TODO: Timer logic here
        aika--;
        this.paivitettava.paivita();
        if (aika == 0) {
            this.lopetaPeli();
        }
    }
}
