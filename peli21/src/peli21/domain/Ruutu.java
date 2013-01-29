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

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
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

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}