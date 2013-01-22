/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import peli21.domain.Pelihahmo;
import peli21.domain.Ruudukko;

/**
 *
 * @author mikko
 */
public class Peli implements ActionListener{
    //private int leveys;
    //private int korkeus;
    private boolean jatkuu;
    private Timer ajastin;
    private int pistelaskuri;
    private Ruudukko ruudukko;
    private Pelihahmo pelaaja;
    
    public Peli(int leveys, int korkeus) {
        this.ajastin = new Timer(1000, null);
        ajastin.setInitialDelay(2000);
        //ajastin.addActionListener(this);
        //this.leveys = leveys;
        //this.korkeus = korkeus;
        this.jatkuu = true;
        this.ruudukko = new Ruudukko(leveys, korkeus);
        this.pelaaja = new Pelihahmo(leveys/2, korkeus/2);
        this.pistelaskuri = 0;
    }
    
    public Ruudukko getRuudukko() {
        return this.ruudukko;
    }
    
    public Pelihahmo getPelaaja() {
        return this.pelaaja;
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
