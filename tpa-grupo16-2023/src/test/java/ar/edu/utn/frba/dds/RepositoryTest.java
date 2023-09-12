package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositoryTest {
    RepoEntidad repo = new RepoEntidad();
    Ubicacion ubicacion = new Ubicacion(20.0,20.0);
    Localizacion localizacion = new Localizacion(ubicacion);
    Entidad entidad1 = new Entidad("NombreGenerico1");

    @BeforeEach
    public void init() {
        entidad1.setLocalizacion(localizacion);
    }

    @Test
    public void crearEntidadTest() {
        repo.guardar(entidad1);
    }

    @Test
    public void testCompleto() {
        repo.guardar(entidad1);
        Entidad entidadBuscada = repo.buscarPorId(entidad1.getId());
        System.out.println("El nombre de la entidad buscada es:" + entidadBuscada.getNombre());

        entidadBuscada.setNombre("NombreGenericoCambiazo");
        repo.modificar(entidadBuscada);

        Entidad entidadBuscadaDespues = repo.buscarPorId(entidadBuscada.getId());
        System.out.println("El nombre de la entidad modificada es:"+ entidadBuscadaDespues.getNombre());

        repo.eliminar(entidadBuscada);

        Assertions.assertEquals(null, repo.buscarPorId(entidadBuscadaDespues.getId()));
    }

}
