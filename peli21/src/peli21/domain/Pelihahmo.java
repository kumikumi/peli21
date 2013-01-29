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
public class Pelihahmo {

    private int x;
    private int y;

    public Pelihahmo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void liikuta(Suunta suunta) {
        switch (suunta) {
            case YLOS:
                y--;
                break;
            case ALAS:
                y++;
                break;
            case OIKEA:
                x++;
                break;
            case VASEN:
                x--;
                break;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    public String toString() {
        return "Pelihahmo paikassa (" + this.x + "," + this.y + ")";
    }
}
