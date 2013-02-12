/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import peli21.Suunta;

/**
 * Yksinkertainen olio, jolla on kaksi koordinaattia <code>x</code> ja <code>y</code>.
 */
public class Pelihahmo {

    private int x;
    private int y;

/**
 * 
 * @param x x-koordinaatti luontihetkellä.
 * @param y y-koordinaatti luontihetkellä.
 */
    public Pelihahmo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Muuttaa pelihahmon koordinaatteja parametrina annettuun suuntaan liikkumista vastaavalla tavalla.
     * @param suunta Suunta, johon pelihahmoa liikutetaan. Suunta saadaan <code>Suunta-luokasta</code> ja voi olla <code>Suunta.YLOS</code>, <code>Suunta.ALAS</code>, <code>Suunta.VASEN</code> tai <code>Suunta.OIKEA</code>.
     */
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
    /**
     * @return Palauttaa tiedon olion paikasta muodossa "Pelihahmo paikassa (<code>x</code, <code>y</code>)".
     */
    
    @Override
    public String toString() {
        return "Pelihahmo paikassa (" + this.x + "," + this.y + ")";
    }
}
