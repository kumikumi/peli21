/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import peli21.Suunta;
import peli21.domain.Pelihahmo;
import peli21.peli.Peli;

/**
 *
 * @author mikko
 */
public class Nappaimistonkuuntelija implements KeyListener {

    private Peli peli;

    public Nappaimistonkuuntelija(Peli peli) {
        this.peli = peli;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                peli.liikutaPelaajaa(Suunta.YLOS);
                break;
            case KeyEvent.VK_DOWN:
                peli.liikutaPelaajaa(Suunta.ALAS);
                break;
            case KeyEvent.VK_LEFT:
                peli.liikutaPelaajaa(Suunta.VASEN);
                break;
            case KeyEvent.VK_RIGHT:
                peli.liikutaPelaajaa(Suunta.OIKEA);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
