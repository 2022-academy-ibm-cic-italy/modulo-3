import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class CounterTest {
    
    public CounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("setUpClass");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("tearDownClass");
    }
    
    @Before
    public void setUp() {
        System.out.println("setUp");
    }
    
    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

    /**
     * Test of increase method, of class Counter.
     */
    @Test
    public void testIncrease() {
        System.out.println("stiamo testando il metodo increase");
        Counter instance = new Counter(10); // parte setup
        instance.increase(); // parte call
        int expectedValue = 11;
        assertEquals("Error increased value", expectedValue, instance.getValue());
    }

    /**
     * Test of decrease method, of class Counter.
     */
    @Test
    public void testDecrease() {
        System.out.println("Stiamo testando il metodo decrease");
        Counter instance = new Counter(0);
        instance.decrease();
        int expectedValue = 0;
        assertEquals("Error decreased value", expectedValue, instance.getValue());
        // metodo alternativo di assert
        //assertTrue("Error decreased value", expectedValue == instance.getValue());
    }

    /**
     * Test of getValue method, of class Counter.
     */
    @Ignore
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Counter instance = new Counter(5);
        int expResult = 5;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }
}