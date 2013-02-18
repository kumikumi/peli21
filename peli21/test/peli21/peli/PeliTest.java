package peli21.peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.SwingUtilities;
import org.junit.*;
import static org.junit.Assert.*;
import peli21.Suunta;
import peli21.domain.Pelihahmo;
import peli21.gui.Kayttoliittyma;

/**
 *
 * @author mikko
 */
public class PeliTest {

    private Peli ruudukkopeli;
    private Kayttoliittyma gui;

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
        ruudukkopeli = new Peli("src/testihighscore");
        gui = new Kayttoliittyma(ruudukkopeli, 40);
        SwingUtilities.invokeLater(gui);
        while (gui.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
        ruudukkopeli.setPaivitettava(gui.getPiirtoalusta());
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
    public void eiAlkanutAlussa() {
        assertEquals(false, ruudukkopeli.isAlkanut());
    }

    @Test
    public void alkanutKunAloitetaanUusiPeli() {
        ruudukkopeli.uusiPeli("Spiderman", 10, 10, 20);
        assertEquals(true, ruudukkopeli.isAlkanut());
    }

    @Test
    public void jatkuuKunAloitetaanUusiPeli() {
        ruudukkopeli.uusiPeli("Sirpa", 10, 10, 20);
        assertEquals(true, ruudukkopeli.jatkuu());
    }

    @Test
    public void ruudukonKokoAlussaOikea() {
        ruudukkopeli.uusiPeli("Testaaja", 20, 15, 20);
        assertEquals(20, ruudukkopeli.getRuudukko().getLEVEYS());
        assertEquals(15, ruudukkopeli.getRuudukko().getKORKEUS());
    }

    @Test
    public void pelaajanLiikutteluToimii() {
        ruudukkopeli.uusiPeli("Batman", 10, 10, 20);
        ruudukkopeli.liikutaPelaajaa(Suunta.YLOS);
        ruudukkopeli.liikutaPelaajaa(Suunta.VASEN);
        ruudukkopeli.liikutaPelaajaa(Suunta.YLOS);
        ruudukkopeli.liikutaPelaajaa(Suunta.VASEN);
        ruudukkopeli.liikutaPelaajaa(Suunta.ALAS);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        Pelihahmo p = ruudukkopeli.getRuudukko().getPelaaja();
        assertEquals(4, p.getX());
        assertEquals(4, p.getY());
    }

    @Test
    public void pisteidenLaskuToimii() {
        ruudukkopeli.uusiPeli("Oskari", 20, 20, 20);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        assertEquals(3, ruudukkopeli.getPisteet());
    }

    @Test
    public void peliLoppuuJosLaitonSiirto() {
        ruudukkopeli.uusiPeli("Konnila", 10, 10, 20);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        ruudukkopeli.liikutaPelaajaa(Suunta.VASEN);
        ruudukkopeli.liikutaPelaajaa(Suunta.OIKEA);
        assertEquals(false, ruudukkopeli.jatkuu());
    }
}
