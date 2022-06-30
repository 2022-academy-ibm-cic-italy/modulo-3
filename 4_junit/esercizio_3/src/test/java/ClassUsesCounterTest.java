import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

public class ClassUsesCounterTest {
    
    public ClassUsesCounterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of multiplyCounterValue method, of class ClassThatUsesCounter.
     */
    @Test
    public void testMultiplyCounterValue() {
        System.out.println("multiplyCounterValue");
        int multiplier = 2;
        CounterInterface mock = Mockito.mock(CounterInterface.class);
        Mockito.when(mock.getValue()).thenReturn(6).thenReturn(5);
        ClassUsesCounter instance = new ClassUsesCounter(mock);
        int expResult = 12;
        int result = instance.multiplyCounterValue(multiplier);
        assertEquals("Error", expResult, result);
        expResult = 10;
        result = instance.multiplyCounterValue(multiplier);
        assertEquals("Error", expResult, result);
    }
}