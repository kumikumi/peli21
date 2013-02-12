/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.highscore;

import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class HighscorelistaTest {

    private Highscorelista h;

    public HighscorelistaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void tyhjennysToimiiAina() throws IOException {
        FileWriter kirjoittaja = new FileWriter("src/testihighscore");
        kirjoittaja.write("Rahaa:15\n");
        kirjoittaja.write("Suomen väkiluku:5406018\n");
        kirjoittaja.write("Maailman väkiluku:7097660041\n");
        kirjoittaja.write("Muista:piimää kaupasta\n");
        kirjoittaja.write("`??::;?==:!#kele\n");
        kirjoittaja.close();

        h = new Highscorelista(10, "src/testihighscore");
        h.tyhjenna();
        assertEquals(0, h.getTuloslista().size());
    }

    @Test
    public void validitRivitSaadaanLisattyaTiedostosta() throws IOException {
        FileWriter kirjoittaja = new FileWriter("src/testihighscore");
        kirjoittaja.write("Rahaa:15\n"); //validi
        kirjoittaja.write("Maailman väkiluku:7097660041\n"); //ei validi (ei mahdu int-tyyppiseen muuttujaan)
        kirjoittaja.write("Muista:piimää kaupasta\n"); //ei validi (piimää on jo tarpeeksi)
        kirjoittaja.write("Suomen väkiluku:5406018\n"); //validi
        kirjoittaja.write("`??::;?==:!#kele\n"); //ei validi
        kirjoittaja.close();
        h = new Highscorelista(10, "src/testihighscore");
        assertEquals(2, h.getTuloslista().size());
    }

    @Test
    public void tiedostostaEiLisataLiikaaRiveja() throws IOException {
        FileWriter kirjoittaja = new FileWriter("src/testihighscore");
        kirjoittaja.write("Seppo:15\n");
        kirjoittaja.write("Kari:20\n");
        kirjoittaja.write("Kristo:14\n");
        kirjoittaja.write("Tuomo:9\n");
        kirjoittaja.write("Jukka:18\n");
        kirjoittaja.write("Ossi:12\n");
        kirjoittaja.write("Ismo:31\n");
        kirjoittaja.write("Taneli:1\n");
        kirjoittaja.close();
        
        h = new Highscorelista(5, "src/testihighscore");
        assertEquals(5, h.getTuloslista().size());
        assertEquals("Rivit lisättiin väärässä järjestyksessä!", new Tulos("Kristo", 14), h.getTuloslista().get(4));
    }

    @Test
    public void ListaanLisaysToimii() {
        h = new Highscorelista(10, "src/testihighscore");
        h.tyhjenna();
        h.lisaa("Matias", 15);
        assertEquals(new Tulos("Matias", 15), h.getTuloslista().get(0));
    }

    @Test
    public void ListaanEiVoiLisataLiikaa() {
        h = new Highscorelista(5, "src/testihighscore");
        h.lisaa("Joni", 30);
        h.lisaa("Roope", 25);
        h.lisaa("Markus", 20);
        h.lisaa("Viljami", 15);
        h.lisaa("Juuso", 10);
        h.lisaa("Oskari", 5);
        assertEquals(5, h.getTuloslista().size());
    }
}
