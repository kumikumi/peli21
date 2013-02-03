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
 * @author mikko
 */
public class Highscorelista {
    
    private List<Tulos> tuloslista;
    private int listanKoko;
    private File tiedosto;
    
    public Highscorelista(int listanKoko) {
        this.tuloslista = new ArrayList<Tulos>();
        this.listanKoko = listanKoko;
        this.tiedosto = new File("src/highscore");
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
            String[] osat = rivi.split(":");
            this.tuloslista.add(new Tulos(osat[0], Integer.parseInt(osat[1])));
        }
        //Varmistetaan, että ladatut tulokset ovat järjestyksessä.
        Collections.sort(tuloslista);
    }
    
    private void tallennaLista(File tiedosto) {
        try {
            FileWriter kirjoittaja = new FileWriter(tiedosto);
            
            for (Tulos t : tuloslista) {
                kirjoittaja.write(t.toString()+ "\n");
            }
            
            kirjoittaja.close();
        } catch (java.io.IOException ex) {
            System.out.println("VIRHE: Jotain meni pieleen tiedostoa kirjoitettaessa.");
        }
    }

    public void lisaa(String nimi, int pisteet) {
        if (tuloslista.size() == this.listanKoko) {
            tuloslista.remove(tuloslista.size() - 1);
        }
        this.tuloslista.add(new Tulos(nimi, pisteet));
        Collections.sort(tuloslista);
        tallennaLista(this.tiedosto);
    }
    
    public void tulosta() {
        for (Tulos t : tuloslista) {
            System.out.println(t);
        }
    }
    
    public void tyhjenna() {
        this.tuloslista = new ArrayList<Tulos>();
        tallennaLista(this.tiedosto);
    }
}
