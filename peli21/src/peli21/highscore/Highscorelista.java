/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Tämä luokka hallinnoi ja highscore-listaa ja huolehtii listan tallentamisesta tiedostoon.
 */
public class Highscorelista {

    private List<Tulos> tuloslista;
    private int listanKoko;
    private File tiedosto;
/**
 * Konstruktorissa listalle annetaan parametreina listan maksimikoko sekä tallennettavan listan tiedostopolku.
 * @param listanKoko Listan maksimikoko. Jos lista tulee liian täyteen, alhaisin tulos poistetaan listalta.
 * @param tiedostopolku Listan relatiivinen tiedostopolku.
 */
    public Highscorelista(int listanKoko, String tiedostopolku) {
        this.tuloslista = new ArrayList<Tulos>();
        this.listanKoko = listanKoko;
        this.tiedosto = new File(tiedostopolku);
        try {
            lataaLista(tiedosto);
        } catch (FileNotFoundException ex) {
        }
    }

    private void lataaLista(File tiedosto) throws FileNotFoundException {
        Scanner lukija = new Scanner(tiedosto);
        String rivi;
        while (lukija.hasNextLine()) {
            rivi = lukija.nextLine();
            if (rivi.charAt(0) == '#') {
                continue;
            }
            try {
            String[] osat = rivi.split(":");
            this.lisaa(osat[0], Integer.parseInt(osat[1]));
            } catch (Exception e) {
                System.out.println("Jotain meni pieleen highscorelistaa ladattaessa.");
            }
        }
        //Varmistetaan, että ladatut tulokset ovat järjestyksessä.
        Collections.sort(tuloslista);
    }

    private void tallennaLista(File tiedosto) {
        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);

            for (Tulos t : tuloslista) {
                kirjoittaja.write(t.toString() + "\n");
            }

            kirjoittaja.close();
        } catch (java.io.IOException ex) {
            System.out.println("VIRHE: Jotain meni pieleen tiedostoa kirjoitettaessa.");
        }
    }

    /**
     * Lisää tuloksen listaan, jos listan koko oli alle maksimikoon tai lisättävä tulos on jotakin listan tulosta parempi. Tämän jälkeen lista järjestetään uudelleen siten, että parempi tulos on listassa ylempänä.
     * @param nimi Pelaajan nimi.
     * @param pisteet Pelaajan pelissä saama pistemäärä.
     */
    public void lisaa(String nimi, int pisteet) {
        this.tuloslista.add(new Tulos(nimi, pisteet));
        Collections.sort(tuloslista);
        if (tuloslista.size() > this.listanKoko) {
            tuloslista.remove(tuloslista.size() - 1);
        }
        tallennaLista(this.tiedosto);
    }
    
    /**
     * @return Palauttaa tulokset <code>List</code>-tyyppisenä oliona.
     */
    
    public List getTuloslista() {
        return this.tuloslista;
    }

    /**
     * Tulostaa listan <code>Tulos</code>-oliot standarditulostusvirtaan rivi kerrallaan, parempi tulos ensin.
     */
    public void tulosta() {
        for (Tulos t : tuloslista) {
            System.out.println(t);
        }
    }

    /**
     * Tyhjentää listan.
     */
    public void tyhjenna() {
        this.tuloslista = new ArrayList<Tulos>();
        tallennaLista(this.tiedosto);
    }
}
