import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    //PUNTO 2
    @Mock
    Database databaseMock;

    @Test
    public void testQuery()  {
        assertNotNull(databaseMock);
        when(databaseMock.isAvailable()).thenReturn(true);
        Service t  = new Service(databaseMock);
        boolean check = t.query("* from t");
        assertTrue(check);
    }
    //FINE PUNTO 2

    //PUNTO 3
    @Test
    void ensureMockitoReturnsTheConfiguredValue() {

        // setto valore di ritorno
        when(databaseMock.getUniqueId()).thenReturn(42);

        Service service = new Service(databaseMock);

        assertEquals(service.toString(), "Using database with id: 42");
    }
    //FINE PUNTO 3

    //PUNTO 4
    @Test
    public void testVerify(@Mock Database database)  {

        // setto valore di ritorno
        when(database.getUniqueId()).thenReturn(43);


        // chiamo i metodi
        database.setUniqueId(12);
        database.getUniqueId();
        database.getUniqueId();


        // verifico che il metodo setUniqueId è stato chiamato con il valore 12
        verify(database).setUniqueId(ArgumentMatchers.eq(12));

        // verifico che il metodo getUniqueId è stato chiamato 2 volte
        verify(database, times(2)).getUniqueId();

        // alternative per verificare se: il metodo non è stato mai chiamato, chiamato almeno una volta oppure chiamato X volte
        verify(database, never()).isAvailable();
        verify(database, never()).setUniqueId(13);
        verify(database, atLeastOnce()).setUniqueId(12);
        verify(database, atLeast(2)).getUniqueId();

        // This let's you check that no other methods where called on this object.
        // You call it after you have verified the expected method calls.
        verifyNoMoreInteractions(database);
    }
    //FINE PUNTO 4

}