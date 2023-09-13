package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.notificaciones.estrategias.CuandoSucede;
import ar.edu.utn.frba.dds.notificaciones.medios.AdapterMailSender;
import ar.edu.utn.frba.dds.repositorios.*;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositoryTest {
    RepoComunidad repoComunidad = new RepoComunidad();
    RepoEntidad repoEntidad = new RepoEntidad();
    RepoIncidente repoIncidente = new RepoIncidente();
    RepoPrestacion repoPrestacion = new RepoPrestacion();
    RepoUsuario repoUsuario = new RepoUsuario();

    @BeforeEach
    public void init() {

    }

    @Test
    public void crearEntidadTest() {
        Entidad nuevaEntidad = new Entidad("EntidadX");
        Ubicacion ubicacion = new Ubicacion(200.0,100.0);
        Localizacion localizacion = new Localizacion(ubicacion);
        nuevaEntidad.setLocalizacion(localizacion);
        repoEntidad.guardar(nuevaEntidad);
    }

    @Test
    public void testABMEntidad() {
        Ubicacion ubicacionABM = new Ubicacion(99.0,99.0);
        Localizacion localizacionABM = new Localizacion(ubicacionABM);
        Entidad entidadABM = new Entidad("EntidadABM");
        entidadABM.setLocalizacion(localizacionABM);

        repoEntidad.guardar(entidadABM);

        Entidad entidadBuscada = repoEntidad.buscarPorId(entidadABM.getId());
        System.out.println("El nombre de la entidad buscada es: " + entidadBuscada.getNombre());

        entidadBuscada.setNombre("EtidadABMModificada");
        repoEntidad.modificar(entidadBuscada);
        System.out.println("Se modifico una entidad");

        Entidad entidadBuscadaModificada = repoEntidad.buscarPorId(entidadBuscada.getId());
        System.out.println("El nombre de la entidad modificada es: "+ entidadBuscadaModificada.getNombre());

        repoEntidad.eliminar(entidadBuscada);
        System.out.println("Se eliminó una entidad");

        Assertions.assertEquals(null, repoEntidad.buscarPorId(entidadBuscadaModificada.getId()));
    }


    @Test
    public void crearUsuarioTest() {
        Ubicacion ubicacion = new Ubicacion(99.0,99.0);
        Localizacion localizacion = new Localizacion(ubicacion);
        Usuario nuevoUsuario = new Usuario("mailABM@gmail.com", "UsuarioABM", "contraseniaABM", localizacion);

        CuandoSucede configNotificacion = new CuandoSucede();
        nuevoUsuario.setConfiguracionNotificacion(configNotificacion);

        AdapterMailSender notificador = new AdapterMailSender();
        nuevoUsuario.setNotificador(notificador);

        repoUsuario.guardar(nuevoUsuario);
    }
    @Test
    public void testABMUsuario() {

        Ubicacion ubicacionABM = new Ubicacion(99.0,99.0);
        Localizacion localizacionABM = new Localizacion(ubicacionABM);
        Usuario usuarioABM = new Usuario("mailABM@gmail.com", "UsuarioABM", "contraseniaABM", localizacionABM);

        CuandoSucede configNotificacion = new CuandoSucede();
        usuarioABM.setConfiguracionNotificacion(configNotificacion);

        AdapterMailSender notificador = new AdapterMailSender();
        usuarioABM.setNotificador(notificador);

        repoUsuario.guardar(usuarioABM);
        System.out.println("Se guarda un usuario");

        Usuario usuarioBuscado = repoUsuario.buscarPorId(usuarioABM.getId());
        System.out.println("El nombre del usuario buscado es: " + usuarioBuscado.getUsuario());

        usuarioBuscado.setUsuario("UsuarioABMModificado");
        repoUsuario.modificar(usuarioBuscado);
        System.out.println("Se modifico un usuario");

        Usuario usuarioBuscadoModificado = repoUsuario.buscarPorId(usuarioBuscado.getId());
        System.out.println("El nombre del usuario modificado es: "+ usuarioBuscadoModificado.getUsuario());

        repoUsuario.eliminar(usuarioBuscado);
        System.out.println("Se eliminó una entidad");

        Assertions.assertEquals(null, repoUsuario.buscarPorId(usuarioBuscadoModificado.getId()));
    }

}
