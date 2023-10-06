package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.localizacion.*;
import ar.edu.utn.frba.dds.models.notificaciones.estrategias.CuandoSucede;
import ar.edu.utn.frba.dds.models.notificaciones.medios.AdapterMailSender;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RepositoryTest {
    RepoComunidad repoComunidad = new RepoComunidad();
    RepoEntidad repoEntidad = new RepoEntidad();
    RepoIncidente repoIncidente = new RepoIncidente();
    RepoPrestacion repoPrestacion = new RepoPrestacion();
    RepoUsuario repoUsuario = new RepoUsuario();
    RepoProvincia repoProvincia = new RepoProvincia();
    RepoDepartamento repoDepartamento = new RepoDepartamento();
    RepoLocalidad repoLocalidad = new RepoLocalidad();

    AdapterGeoref adapterGeoref = AdapterGeoref.instancia();

    @BeforeEach
    public void initb() {

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
        Usuario nuevoUsuario = new Usuario("mailX@gmail.com", "UsuarioX", "contraseniaX", localizacion);

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
        System.out.println("Se eliminó un usuario");

        Assertions.assertEquals(null, repoUsuario.buscarPorId(usuarioBuscadoModificado.getId()));
    }

    @Test
    public void crearComunidadTest() {
        Comunidad nuevaComunidad = new Comunidad("ComunidadX");

        repoComunidad.guardar(nuevaComunidad);
    }
    @Test
    public void testABMComunidad() {
        Comunidad comunidadAMB = new Comunidad("ComunidadAMB");

        repoComunidad.guardar(comunidadAMB);
        System.out.println("Se guarda una Comunidad");

        Comunidad comunidadBuscada = repoComunidad.buscarPorId(comunidadAMB.getId());
        System.out.println("El nombre de la comunidad buscada es: " + comunidadBuscada.getNombre());

        comunidadBuscada.setNombre("ComunidadABMModificada");
        repoComunidad.modificar(comunidadBuscada);
        System.out.println("Se modifico una comunidad");

        Comunidad comunidadBuscadaModificada = repoComunidad.buscarPorId(comunidadBuscada.getId());
        System.out.println("El nombre de la comunidad modificada es: "+ comunidadBuscadaModificada.getNombre());

        repoComunidad.eliminar(comunidadBuscada);
        System.out.println("Se eliminó una comunidad");

        Assertions.assertEquals(null, repoComunidad.buscarPorId(comunidadBuscadaModificada.getId()));
    }

    @Test
    public void buscarIncidentesPorComunidad() {
        List<Incidente> incidentes = new ArrayList<>();
        incidentes =  repoIncidente.buscarPorComunidad(2);

        for(Incidente incidente : incidentes)
            System.out.println(incidente.getId());
    }

    @Test
    public void cargaDatosGeoref() throws IOException {
        List<Provincia> provincias = adapterGeoref.obtenerListadoProvincias();
        repoProvincia.guardarMuchas(provincias);
        for (Provincia unaProvincia : provincias) {
            List<Departamento> departamentos = adapterGeoref.obtenerListadoDepartamentos(unaProvincia);
            repoDepartamento.guardarMuchos(departamentos);
            for (Departamento unDepartamento : departamentos) {
                List<Localidad> localidades = adapterGeoref.obtenerListadoLocalidades(unDepartamento);
                repoLocalidad.guardarMuchas(localidades);
            }
        }
    }
    @Test
    public void cargarDatos() {
        Ubicacion ubicacion1 = new Ubicacion(99.0,99.0);
        Ubicacion ubicacion2 = new Ubicacion(20.0,109.0);
        Ubicacion ubicacion3 = new Ubicacion(80.0,40.0);
        Ubicacion ubicacion4 = new Ubicacion(30.0,70.0);
        Localizacion localizacion = new Localizacion(ubicacion1);
        Usuario nuevoUsuario = new Usuario("mailX@gmail.com", "UsuarioX", "contraseniaX", localizacion);

        CuandoSucede configNotificacion = new CuandoSucede();
        nuevoUsuario.setConfiguracionNotificacion(configNotificacion);

        AdapterMailSender notificador = new AdapterMailSender();
        nuevoUsuario.setNotificador(notificador);

        repoUsuario.guardar(nuevoUsuario);

        Incidente nuevoIncidente = new Incidente();

        nuevoIncidente.setHorarioApertura(LocalDateTime.now());
        nuevoIncidente.setHorarioCierre(LocalDateTime.now().minusHours(10));
        nuevoIncidente.setObservaciones("Esto es una observacion");
        nuevoIncidente.setUsuarioApertura(nuevoUsuario);
        nuevoIncidente.setUsuarioCierre(nuevoUsuario);


        repoIncidente.guardar(nuevoIncidente);
    }
}
