/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Tila;
import peli21.Suunta;

/**
 * Ruudulla on neljä <code>Tila</code>-tyyppistä muuttujaa <code>up</code>, <code>down</code>, <code>left</code>, <code>right</code>,
 * jotka ilmaisevat, voiko ruutuun siirtyä liikkumalla kyseisen muuttujan ilmaisemaan suuntaan.
 */
public class Ruutu {

    private Tila up;
    private Tila down;
    private Tila left;
    private Tila right;

/**
 * Ruudun luonnin yhteydessä voidaan antaa eri suunnille tilat järjestyksessä <code>up</code>, <code>down</code>, <code>left</code>, <code>right</code>.
 * @param up
 * @param down
 * @param left
 * @param right 
 */
    public Ruutu(Tila up, Tila down, Tila left, Tila right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /**
     * Oletusarvoisesti kaikki neljä suuntaa saavat arvokseen <code>Tila.OFF</code>.
     */
    public Ruutu() {
        this(Tila.OFF, Tila.OFF, Tila.OFF, Tila.OFF);
    }

    /**
     * 
     * Asettaa suunnan <code>suunta</code> tilaksi <code>tila</code>.
     * 
     * 
     * @param suunta Suunta saadaan <code>Suunta</code>-luokasta ja voi olla <code>Suunta.YLOS</code>, <code>Suunta.ALAS</code>, <code>Suunta.VASEN</code> tai <code>Suunta.OIKEA</code>.
     * @param tila Tila saadaan <code>Tila</code>-luokasta ja voi olla <code>Tila.OFF</code>, <code>Tila.ON</code> tai <code>Tila.BONUS</code>.
     */
    public void setSuunta(Suunta suunta, Tila tila) {
        switch (suunta) {
            case YLOS:
                this.up = tila;
                return;
            case ALAS:
                this.down = tila;
                return;
            case VASEN:
                this.left = tila;
                return;
            case OIKEA:
                this.right = tila;
        }
    }

    /**
     * Palauttaa tiedon parametrina annetun suunnan <code>suunta</code> tilasta.
     * @param suunta Suunta saadaan <code>Suunta</code>-luokasta ja voi olla <code>Suunta.YLOS</code>, <code>Suunta.ALAS</code>, <code>Suunta.VASEN</code> tai <code>Suunta.OIKEA</code>.
     * @return Tila-tyyppinen tieto parametrina annetun suunnan tilasta.
     */
    public Tila getTila(Suunta suunta) {
        switch (suunta) {
            case YLOS:
                return this.up;
            case ALAS:
                return this.down;
            case VASEN:
                return this.left;
            case OIKEA:
                return this.right;
        }
        return Tila.OFF;
    }
}