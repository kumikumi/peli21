/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peli21.domain;

import org.junit.*;
import static org.junit.Assert.*;

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
    }
    @Test
    public void isLeftAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isLeft());
    }
    @Test
    public void isRightAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isRight());
    }
    @Test
    public void isUpAluksiFalse() {
        Ruutu ruutu = new Ruutu();
        assertEquals(false, ruutu.isUp());
    }
    @Test
    public void setDownToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setDown(true);
        assertEquals(true, ruutu.isDown());
    }
    
    @Test
    public void setLeftToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setLeft(true);
        assertEquals(true, ruutu.isLeft());
    }
    
    @Test
    public void setRightToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setRight(true);
        assertEquals(true, ruutu.isRight());
    }
    
    @Test
    public void setUpToimii() {
        Ruutu ruutu = new Ruutu();
        ruutu.setUp(true);
        assertEquals(true, ruutu.isUp());
    }
}
