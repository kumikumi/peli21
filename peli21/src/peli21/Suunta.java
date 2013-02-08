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
    
    /**
     * Palauttaa tälle suunnalle vastakkaisen suunnan.
     * @return Jos suunta on <code>YLOS</code>, metodi palauttaa <code>ALAS</code>. Jos suunta on <code>ALAS</code>, metodi palauttaa <code>YLOS</code>. Jos suunta on <code>OIKEA</code>, metodi palauttaa <code>VASEN</code>. Jos suunta on <code>VASEN</code>, metodi palauttaa <code>OIKEA</code>.
     */
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
