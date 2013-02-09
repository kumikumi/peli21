/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import peli21.Effect;
import peli21.Suunta;
//import peli21.domain.Pelihahmo;
import peli21.domain.Ruudukko;
import peli21.gui.Paivitettava;
import peli21.highscore.Highscorelista;
//import peli21.gui.Piirtoalusta;

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

    private int leveys;
    private int korkeus;
    private Highscorelista highscorelista;
    private String pelaajanNimi;
    private boolean jatkuu;
    private Timer ajastin;
    private int pistelaskuri;
    private Ruudukko ruudukko;
    private Paivitettava paivitettava;
    //private Pelihahmo pelaaja;

    /**
     * Konstruktorissa pelille tulee antaa tieto pelaajan nimestä ja
     * peliruudukolle annettavat mitat. Näitä mittoja käytetään sopivan
     * <code>Ruudukko</code>-olion luomiseen.
     *
     * @param pelaajanNimi Pelaajalle annettava nimi.
     * @param leveys Luotavan ruudukon leveys (ruutuina).
     * @param korkeus Luotavan ruudukon korkeus (ruutuina).
     */
    public Peli(String pelaajanNimi, int leveys, int korkeus) {
        this.ajastin = new Timer(1000, null);
        ajastin.setInitialDelay(2000);
        //ajastin.addActionListener(this);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.pelaajanNimi = pelaajanNimi;
        this.jatkuu = true;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.pistelaskuri = 0;
        this.highscorelista = new Highscorelista(10);
    }

    public void uusiPeli(String pelaajanNimi, int leveys, int korkeus) {
        this.pelaajanNimi = pelaajanNimi;
        this.pistelaskuri = 0;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.paivitettava.paivitaKomponentit();
        this.paivitettava.paivita();
        this.jatkuu = true;
    }

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

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
     * @param suunta
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

    private void reagoiPalautukseen(Effect e) {
        switch (e) {
            case SUCCESS:
                pistelaskuri++;
                break;
            case DEATH:
                System.out.println("DEATH");
                jatkuu = false;
                highscorelista.lisaa(pelaajanNimi, pistelaskuri);
                highscorelista.tulosta();
        }
    }

    public int getPisteet() {
        return this.pistelaskuri;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuu) {
            return;
        }
        // @TODO: Timer logic here
        pistelaskuri++;

    }
}
