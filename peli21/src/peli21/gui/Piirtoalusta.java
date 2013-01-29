/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import peli21.domain.Pelihahmo;
import peli21.domain.Ruudukko;
import peli21.domain.Ruutu;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class Piirtoalusta extends JPanel implements Paivitettava{
    //private Peli peli;
    private int sivunPituus;
    private Ruudukko peliruudukko;
    private Pelihahmo pelihahmo;
    
    public Piirtoalusta(Peli peli, int palanSivunPituus) {
        //this.peli = peli;
        this.sivunPituus = palanSivunPituus;
        this.peliruudukko = peli.getRuudukko();
        this.pelihahmo = peliruudukko.getPelaaja();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Ruutu[][] ruudukko = peli.getRuudukko().getRuutuTaulukko();
        
        g.setColor(Color.BLACK);
        
        for (int x = 0; x < peliruudukko.getLEVEYS(); x++) {
            for (int y = 0; y < peliruudukko.getKORKEUS(); y++) {
                g.drawRect(x*sivunPituus, y*sivunPituus, sivunPituus, sivunPituus);
                // TODO: Piirrä ruutuun nuolet
                
            }
        }
        
        g.setColor(Color.RED);
        g.fillOval(pelihahmo.getX()*sivunPituus, pelihahmo.getY()*sivunPituus, sivunPituus, sivunPituus);
        
    }
    
    @Override
    public void paivita() {
        super.repaint();
    }
    
}
