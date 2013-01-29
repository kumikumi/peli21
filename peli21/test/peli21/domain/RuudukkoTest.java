/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import org.junit.*;
import static org.junit.Assert.*;
import peli21.Effect;
import peli21.Suunta;

/**
 *
 * @author mikko
 */
public class RuudukkoTest {

    private Ruudukko pelilauta;

    public RuudukkoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        pelilauta = new Ruudukko(20, 10);
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
    public void pelihahmoAlussaKeskella() {
        assertEquals(10, pelilauta.getPelaaja().getX());
        assertEquals(5, pelilauta.getPelaaja().getY());
    }
    
    @Test
    public void pelihahmoLiikkuuOikein() {
        pelilauta.liikutaPelaajaa(Suunta.YLOS);
        pelilauta.liikutaPelaajaa(Suunta.YLOS);
        pelilauta.liikutaPelaajaa(Suunta.YLOS);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.VASEN);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.ALAS);
        pelilauta.liikutaPelaajaa(Suunta.ALAS);
        pelilauta.liikutaPelaajaa(Suunta.YLOS);
        assertEquals(12, pelilauta.getPelaaja().getX());
        assertEquals(3, pelilauta.getPelaaja().getY());
    }
    
    @Test
    public void pelaajaMeneeOikeaanReunaanMuttaEiYli() {
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        pelilauta.liikutaPelaajaa(Suunta.OIKEA);
        assertEquals(Effect.SUCCESS, pelilauta.liikutaPelaajaa(Suunta.OIKEA));
        assertEquals(19, pelilauta.getPelaaja().getX());
        assertEquals(Effect.DEATH, pelilauta.liikutaPelaajaa(Suunta.OIKEA));
        assertEquals(19, pelilauta.getPelaaja().getX());
    }
    
    public void pelaajaEiMeneOikeanReunanYli() {
        
    }
}
