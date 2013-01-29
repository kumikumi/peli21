/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21;

/**
 *
 * @author mikko
 */
public enum Suunta {
    YLOS,
    ALAS,
    OIKEA,
    VASEN;
    public Suunta vastakkainenSuunta() {
        switch (this) {
            case YLOS: return ALAS;
            case ALAS: return YLOS;
            case OIKEA: return VASEN;
            case VASEN: return OIKEA;
        default: throw new IllegalStateException("Jotain meni pieleen: Suunnalla " + this + " ei ole vastakkaista suuntaa.");
        }
    }
}
