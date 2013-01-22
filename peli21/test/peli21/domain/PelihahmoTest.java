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
public class PelihahmoTest {
    
    private Pelihahmo batman;

    
    public PelihahmoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        batman = new Pelihahmo(3, 2);
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
    public void paikkaAsetetaanOikeinKonstruktorissa() {
        assertEquals(3, batman.getX());
        assertEquals(2, batman.getY());
    }
    
    @Test
    public void xKoordinaatinMuuttaminenOnnistuu() {
        batman.setX(7);
        assertEquals(7, batman.getX());
        assertEquals(2, batman.getY());
    }
    @Test
    public void yKoordinaatinMuuttaminenOnnistuu() {
        batman.setY(9);
        assertEquals(3, batman.getX());
        assertEquals(9, batman.getY());
    }
}
