package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Ignore
public class ContextTest implements SimplePersistenceTest {


    @Test
    void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {
        });
    }

}