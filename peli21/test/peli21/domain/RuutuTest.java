/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import org.junit.*;
import static org.junit.Assert.*;
import peli21.Suunta;
import peli21.Tila;

/**
 *
 * @author mikko
 */
public class RuutuTest {

    public RuutuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Testit alkaa tästä.
     */
    @Test
    public void AlasAluksiPoisPaalta() {
        Ruutu ruutu = new Ruutu();
        assertEquals(Tila.OFF, ruutu.getTila(Suunta.ALAS));
    }
    @Test
    public void VasenAluksiPoisPaalta() {
        Ruutu ruutu = new Ruutu();
        assertEquals(Tila.OFF, ruutu.getTila(Suunta.VASEN));
    }
    @Test
    public void OikeaAluksiPoisPaalta() {
        Ruutu ruutu = new Ruutu();
        assertEquals(Tila.OFF, ruutu.getTila(Suunta.OIKEA));
    }
    @Test
    public void YlosAluksiPoisPaalta() {
        Ruutu ruutu = new Ruutu();
        assertEquals(Tila.OFF, ruutu.getTila(Suunta.YLOS));
    }
    @Test
    public void setSuuntaAlasToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.ALAS, Tila.ON);
        assertEquals(Tila.ON, ruutu.getTila(Suunta.ALAS));
    }

    @Test
    public void setSuuntaVasenToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.VASEN, Tila.ON);
        assertEquals(Tila.ON, ruutu.getTila(Suunta.VASEN));
    }

    @Test
    public void setSuuntaOikeaToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.OIKEA, Tila.ON);
        assertEquals(Tila.ON, ruutu.getTila(Suunta.OIKEA));
    }

    @Test
    public void setSuuntaYlosToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.YLOS, Tila.ON);
        assertEquals(Tila.ON, ruutu.getTila(Suunta.YLOS));
    }
}
