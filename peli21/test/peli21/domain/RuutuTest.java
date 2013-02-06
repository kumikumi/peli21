/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import org.junit.*;
import static org.junit.Assert.*;
import peli21.Suunta;

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
    public void isDownAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isSuunta(Suunta.ALAS));
    }
    @Test
    public void isLeftAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isSuunta(Suunta.VASEN));
    }
    @Test
    public void isRightAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isSuunta(Suunta.OIKEA));
    }
    @Test
    public void isUpAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isSuunta(Suunta.YLOS));
    }
    @Test
    public void setSuuntaAlasToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.ALAS, true);
        assertEquals(true, ruutu.isSuunta(Suunta.ALAS));
    }

    @Test
    public void setSuuntaVasenToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.VASEN, true);
        assertEquals(true, ruutu.isSuunta(Suunta.VASEN));
    }

    @Test
    public void setSuuntaOikeaToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.OIKEA, true);
        assertEquals(true, ruutu.isSuunta(Suunta.OIKEA));
    }

    @Test
    public void setSuuntaYlosToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setSuunta(Suunta.YLOS, true);
        assertEquals(true, ruutu.isSuunta(Suunta.YLOS));
    }
}
