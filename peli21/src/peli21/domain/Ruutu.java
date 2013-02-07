/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Suunta;

/**
 * Ruudulla on neljä boolean-tyyppistä muuttujaa <code>up</code>, <code>down</code>, <code>left</code>, <code>right</code>,
 * jotka ilmaisevat, voiko ruutuun siirtyä liikkumalla kyseisen muuttujan ilmaisemaan suuntaan.
 */
public class Ruutu {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

/**
 * Ruudun luonnin yhteydessä voidaan antaa eri suunnille tilat järjestyksessä <code>up</code>, <code>down</code>, <code>left</code>, <code>right</code>.
 * @param up
 * @param down
 * @param left
 * @param right 
 */
    public Ruutu(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    /**
     * Oletusarvoisesti kaikki neljä suuntaa saavat arvokseen <code>false</code>.
     */
    public Ruutu() {
        this(false, false, false, false);
    }

    /**
     * 
     * Asettaa suunnan <code>suunta</code> tilaksi <code>tila</code>.
     * 
     * 
     * @param suunta
     * @param tila 
     */
    public void setSuunta(Suunta suunta, boolean tila) {
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
     * Palauttaa tiedon parametrina annetun suunnan <suunta> tilasta.
     * @param suunta Suunta saadaan Suunta-luokasta ja voi olla Suunta.YLOS, Suunta.ALAS, Suunta.VASEN tai Suunta.OIKEA.
     * @return Boolean-tyyppinen tieto parametrina annetun suunnan tilasta.
     */
    public boolean isSuunta(Suunta suunta) {
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
        return false;
    }
}