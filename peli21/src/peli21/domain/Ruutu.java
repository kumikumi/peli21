/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Suunta;

/**
 *
 * @author mikko
 */
public class Ruutu {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Ruutu(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public Ruutu() {
        this(false, false, false, false);
    }

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