import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    Repository repository;

    @BeforeEach
    public void init() {
        assertNotNull(repository);
    }

    @Test
    @DisplayName("Primo test con Mockito")
    public void testService() {
        Service service = new Service(repository);
        when(repository.checkStatus()).thenReturn(true);
        boolean status = service.insert("TEST");
        assertTrue(status);
    }
}
