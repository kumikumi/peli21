/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;
import peli21.Suunta;
//import peli21.domain.Pelihahmo;
import peli21.domain.Ruudukko;
import peli21.gui.Paivitettava;
import peli21.highscore.Highscorelista;
//import peli21.gui.Piirtoalusta;

/**
 *
 * @author mikko
 */
public class Peli implements ActionListener {
    //private int leveys;
    //private int korkeus;

    private Highscorelista highscorelista;
    private String pelaajanNimi;
    private boolean jatkuu;
    private Timer ajastin;
    private int pistelaskuri;
    private Ruudukko ruudukko;
    private Paivitettava paivitettava;
    //private Pelihahmo pelaaja;

    public Peli(String pelaajanNimi, int leveys, int korkeus) {
        this.ajastin = new Timer(1000, null);
        ajastin.setInitialDelay(2000);
        //ajastin.addActionListener(this);
        //this.leveys = leveys;
        //this.korkeus = korkeus;
        this.pelaajanNimi = pelaajanNimi;
        this.jatkuu = true;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.pistelaskuri = 0;
        this.highscorelista = new Highscorelista(10);
    }
    
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }

    public void setPaivitettava(Paivitettava piirtoalusta) {
        this.paivitettava = piirtoalusta;
    }
    
    
    public void liikutaPelaajaa(Suunta suunta) {
        if (!jatkuu) {
            return;
        }
        switch (ruudukko.liikutaPelaajaa(suunta)) {
            case SUCCESS:
                pistelaskuri++;
                break;
            case DEATH:
                System.out.println("DEATH");
                jatkuu = false;
                highscorelista.lisaa(pelaajanNimi, pistelaskuri);
                highscorelista.tulosta();
                return;
                
                
        }
        this.paivitettava.paivita();
        System.out.println(ruudukko.getPelaaja());
        System.out.println("Pisteet: " + pistelaskuri);
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
