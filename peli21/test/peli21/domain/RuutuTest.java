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
        assertEquals(false, ruutu.isDown());
        assertEquals(false, ruutu.isSuunta(Suunta.ALAS));
    }
    @Test
    public void isLeftAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isLeft());
        assertEquals(false, ruutu.isSuunta(Suunta.VASEN));
    }
    @Test
    public void isRightAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isRight());
        assertEquals(false, ruutu.isSuunta(Suunta.OIKEA));
    }
    @Test
    public void isUpAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isUp());
        assertEquals(false, ruutu.isSuunta(Suunta.YLOS));
    }
    @Test
    public void setDownToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setDown(true);
        assertEquals(true, ruutu.isDown());
        assertEquals(true, ruutu.isSuunta(Suunta.ALAS));
    }

    @Test
    public void setLeftToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setLeft(true);
        assertEquals(true, ruutu.isLeft());
        assertEquals(true, ruutu.isSuunta(Suunta.VASEN));
    }

    @Test
    public void setRightToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setRight(true);
        assertEquals(true, ruutu.isRight());
        assertEquals(true, ruutu.isSuunta(Suunta.OIKEA));
    }

    @Test
    public void setUpToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setUp(true);
        assertEquals(true, ruutu.isUp());
        assertEquals(true, ruutu.isSuunta(Suunta.YLOS));
    }
}
