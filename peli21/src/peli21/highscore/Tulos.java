/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.highscore;

/**
 *
 * @author mikko
 */
public class Tulos implements Comparable<Tulos>{
    private String nimi;
    private int pisteet;
    public Tulos(String nimi, int pisteet) {
        this.nimi = nimi;
        this.pisteet = pisteet;
    }
    
    public int getPisteet() {
        return this.pisteet;
    }
    public String getNimi() {
        return this.nimi;
    }
    
    @Override
    public String toString() {
        return this.nimi + ":" + this.pisteet;
    }

    @Override
    public int compareTo(Tulos t) {
        return t.getPisteet() - this.pisteet;
    }


}
