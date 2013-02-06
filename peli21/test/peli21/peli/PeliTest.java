package peli21.peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author mikko
 */
public class PeliTest {
    
    
    private Peli ruudukkopeli;
    public PeliTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        ruudukkopeli = new Peli("Testaaja", 20, 15);
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
    public void ruudukonKokoAlussaOikea() {
        assertEquals(20, ruudukkopeli.getRuudukko().getLEVEYS());
        assertEquals(15, ruudukkopeli.getRuudukko().getKORKEUS());
    }
}
