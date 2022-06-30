import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalcolatriceTest {

    Calcolatrice calcolatrice;

    @BeforeEach
    public void init() {
        calcolatrice = new Calcolatrice();
    }

    @Test
    @DisplayName("Semplice moltiplicazione 5 * 5")
    public void testMoltiplicazione() {
        //5 * 5 = 25
        assertEquals(25, calcolatrice.moltiplicazione(5,5), "Semplice moltiplicazione 5 * 5");
    }

    @RepeatedTest(5)
    @DisplayName("Semplice moltiplicazione 0 * 9 / 0 * 8")
    public void testMoltiplicazione2() {
        assertEquals(0, calcolatrice.moltiplicazione(0, 9), "Semplice moltiplicazione 0 * 9");

        assertEquals(0, calcolatrice.moltiplicazione(0, 8), "Semplice moltiplicazione 0 * 8");

    }
}
