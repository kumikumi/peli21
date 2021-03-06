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
import peli21.gui.PaivitysTyyppi;
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
    private int bonuslaskuri;
    private int pisteet;
    private Ruudukko ruudukko;
    private Paivitettava paivitettava;
    private PaivitysTyyppi paivitysTyyppi;
    private boolean pelaajaSaiEnnatyksen;
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
        this.ajastin.stop();
        this.alkanut = true;
        this.pelaajanNimi = pelaajanNimi;
        this.pisteet = 0;
        this.bonuslaskuri = 0;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.ruudukko.alustaRuudut();
        this.oletusaika = aika;
        this.aika = aika;
        this.paivitettava.paivitaKomponentit();
        this.paivitettava.paivita(PaivitysTyyppi.KAIKKI);
        this.jatkuu = true;
        this.pelaajaSaiEnnatyksen = false;
    }

    private void lopetaPeli() {
        this.ajastin.stop();
        jatkuu = false;
        pelaajaSaiEnnatyksen = highscorelista.lisaa(pelaajanNimi, pisteet);
        //highscorelista.tulosta();
        System.out.print(highscorelista);
    }

    /**
     * Asettaa ruudukon. Voidaan käyttää testaamiseen.
     *
     * @param ruudukko
     */
    public void setRuudukko(Ruudukko ruudukko) {
        this.ruudukko = ruudukko;
    }

    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    /**
     * @return Palauttaa <code>true</code> sen jälkeen, kun peli on kerran
     * aloitettu eli kun <code>uusiPeli</code>-metodia on kutsuttu ensimmäisen
     * kerran.
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
        this.ajastin.start();
        if (!jatkuu) {
            return;
        }
        reagoiPalautukseen(ruudukko.liikutaPelaajaa(suunta));
        this.paivitettava.paivita(PaivitysTyyppi.KAIKKI);
        if (!jatkuu) {
            return;
        }
    }

    private void reagoiPalautukseen(Tila tila) {
        switch (tila) {
            case BONUS:
                //Jos pelaaja sai bonuksen, alaspäin laskeva bonuslaskuri laitetaan täyteen (20).
                //Bonuksesta saa lisäksi 8+2 pistettä.
                aika = oletusaika;
                bonuslaskuri = 20;
                pisteet += 8;

            //Tämän lisäksi tapahtuvat kaikki "ON"-tilaa koskevat asiat:
            case ON:

                if (aika + 4 > oletusaika) {
                    aika = oletusaika;
                } else {
                    aika = aika + 4;
                }

                if (bonuslaskuri > 0) {
                    pisteet = pisteet + 2; //Jos bonus on päällä, tavalliset pisteet tulevat kaksinkertaisena.
                    //aika = oletusaika; //Jos bonus on päällä, niin ajan saa onnistuneesta siirrosta täyteen.
                } else {
                    pisteet++;

//                    if (aika+aika/2 > oletusaika) {
//                        aika = oletusaika;
//                    } else {
//                        aika+=aika/2; //Jos ollaan normaalitilassa, aika 1.5-kertaistuu joka onnistuneesta siirrosta.
                    //aika += (oletusaika - aika) / 2; //Toinen vaihtoehto olis että aika täytetään puoliksi.
//                    }
                }
                break;
            case OFF: //Ahaa! Pelaaja on töpeksinyt
                if (bonuslaskuri > 0) { //Jos bonusta oli, otetaan se pois
                    bonuslaskuri = 0;
                } else { // Muussa tapauksessa kaveri potkaisee tyhjää
                    System.out.println("Peli päättyi.");
                    lopetaPeli();
                }
        }
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public int getAika() {
        return this.aika;
    }

    public int getOletusAika() {
        return this.oletusaika;
    }

    public int getBonusLaskuri() {
        return this.bonuslaskuri;
    }

    public Highscorelista getHighscore() {
        return this.highscorelista;
    }

    public boolean pelaajaSaiEnnatyksen() {
        return this.pelaajaSaiEnnatyksen;
    }

    public boolean deadLock() {
        return ruudukko.deadLock();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jatkuu) {
            return;
        }
        this.paivitysTyyppi = PaivitysTyyppi.HUD;
        if (bonuslaskuri > 0) {
            bonuslaskuri--;
            if (bonuslaskuri == 0) {
                this.paivitysTyyppi = PaivitysTyyppi.KAIKKI;
            }
        }
        // @TODO: Timer logic here
        aika--;
        if (aika == 0) {
            this.paivitysTyyppi = PaivitysTyyppi.KAIKKI;
            if (bonuslaskuri > 0) {
                aika = Math.min(oletusaika, bonuslaskuri);
                bonuslaskuri = 0;
            } else {
                this.lopetaPeli();
            }
        }
        this.paivitettava.paivita(this.paivitysTyyppi);
    }
}
