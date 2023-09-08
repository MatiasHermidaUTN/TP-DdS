package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Comunidad;
import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.TipoPerfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.notificaciones.AdapterMailSender;
import ar.edu.utn.frba.dds.notificaciones.ConfiguracionNotificacion;
import ar.edu.utn.frba.dds.notificaciones.CuandoSucede;
import ar.edu.utn.frba.dds.notificaciones.SinApuros;
import ar.edu.utn.frba.dds.notificaciones.cron.Cron;
import ar.edu.utn.frba.dds.notificaciones.cron.DiaSemana;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.repositorios.RepoUsuario;
import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



public class NewIncidentsControllerTest {
    //Hay que charlar un par de cosas


    public void crearIncidente(Establecimiento establecimiento, Servicio servicio, Usuario usuarioApertura) {

        List<Comunidad> comunidades = usuarioApertura.getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad()).toList();

        for(Comunidad unaComunidad : comunidades) {
            Incidente incidente = new Incidente(establecimiento, unaComunidad.getNombre(), servicio, usuarioApertura);
            unaComunidad.agregarIncidente(incidente);
            crear_o_agregar_prestacion(establecimiento, servicio, incidente);
            
            // notificar a cada miembro
            unaComunidad.getMiembros()
                    .forEach(perfil -> perfil.getUsuario().recibirNotificacionDeAperturaDeIncidente(incidente));
        }

        List<Usuario> usuarioList = RepoUsuario.getListaUsuarios(); //TODO este RepoUsuario se deberia hacer con Hibernate
        usuarioList.stream()
                .filter(
                        unUsuario -> unUsuario.getServiciosInteres().contains(servicio) &&
                        unUsuario.getEntidadesInteres()
                                .contains(establecimiento.getEntidad())
                )
                // se lo mandamos a un solo perfil de cada usuario para que no reciba notificaciones repetidas (por cada uno de sus perfiles)
                .forEach(usuario -> usuario.
                        recibirNotificacionDeAperturaDeIncidente(new Incidente(establecimiento, "Servicio Interes Particular", servicio, usuarioApertura)));
    }

    public void crear_o_agregar_prestacion(Establecimiento establecimiento_a_buscar, Servicio servicio_a_buscar, Incidente incidente){

        List<Prestacion> listaPrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();

        List <Prestacion> listaPrestacionesDelEstablecimiento = listaPrestaciones.stream()
                .filter(prestacion -> prestacion.getEstablecimiento().getNombre() == establecimiento_a_buscar.getNombre())
                .collect(Collectors.toList());

        if(listaPrestacionesDelEstablecimiento == null){
            Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
            nuevaPrestacion.agregarIncidente(incidente);
            RepoPrestacion.agregarPrestacion(nuevaPrestacion);
        }
        else {
            Prestacion prestacionDelServicioDelEstablecimiento =  listaPrestacionesDelEstablecimiento.stream()
                    .filter(prestacion -> prestacion.getServicio().getNombre() == servicio_a_buscar.getNombre())
                    .findAny()
                    .orElse(null);

            if(prestacionDelServicioDelEstablecimiento == null){
                Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
                RepoPrestacion.agregarPrestacion(nuevaPrestacion);
            }
            else {
                prestacionDelServicioDelEstablecimiento.agregarIncidente(incidente);
            }
        }
    }


    public void cerrarEnComunidad(Integer idIncidente, Perfil perfilCierre, LocalDateTime horarioCierre) {
        Comunidad unaComunidad = perfilCierre.getComunidad();
        Incidente incidenteACerrar = unaComunidad.getIncidenteFromId(idIncidente);

        incidenteACerrar.setUsuarioCierre(perfilCierre.getUsuario());
        incidenteACerrar.setHorarioCierre(LocalDateTime.now());
        incidenteACerrar.setEstado(EstadoIncidente.RESUELTO);
        
        // notificar a cada miembro
        unaComunidad.getMiembros()
                .forEach(perfil -> perfil.getUsuario().recibirNotificacionDeCierreDeIncidente(incidenteACerrar));
    }

    @Test
    public void controladorIncidentesTest() throws Exception {
        Establecimiento mcDonalds = new Establecimiento("McDonalds");

        Entidad entidadMcDonalds = new Entidad("entidad de mcdonalds?");
        entidadMcDonalds.agregarEstablecimientos(mcDonalds);
        mcDonalds.setEntidad(entidadMcDonalds);

        Servicio banio = new Servicio("baño");
        Servicio escalera = new Servicio("escalera");
        Servicio wifi = new Servicio("wifi");

        mcDonalds.agregarServicios(banio, escalera, wifi);

        Comunidad comunidad1 = new Comunidad("comunidad1");
        Comunidad comunidad2 = new Comunidad("comunidad2");

        Perfil perfil1 = new Perfil("JuanP", comunidad1, TipoPerfil.NORMAL);
        Perfil perfil2 = new Perfil("JuanP", comunidad2, TipoPerfil.NORMAL);

        comunidad1.agregarMiembros(perfil1);
        comunidad2.agregarMiembros(perfil2);

        Usuario julianPolaco = new Usuario("adalessandro@frba.utn.edu.ar", "julianPolaco", "1234");
        julianPolaco.agregarEntidadInteres(entidadMcDonalds);
        julianPolaco.agregarServicioInteres(banio);
        ConfiguracionNotificacion configuracionNotificacion = new CuandoSucede(julianPolaco);
        configuracionNotificacion.setNotificador(new AdapterMailSender());
        julianPolaco.setConfiguracionNotificacion(configuracionNotificacion);

        julianPolaco.agregarPerfil(perfil1);
        julianPolaco.agregarPerfil(perfil2);

        perfil1.setUsuario(julianPolaco);
        perfil2.setUsuario(julianPolaco);


        crearIncidente(mcDonalds, banio, julianPolaco);

    }

    /*
    @Test
    public void incidente_sin_apuros() throws Exception {
        Establecimiento mcDonalds = new Establecimiento("McDonalds");

        Entidad entidadMcDonalds = new Entidad("entidad de mcdonalds?");
        entidadMcDonalds.agregarEstablecimientos(mcDonalds);
        mcDonalds.setEntidad(entidadMcDonalds);

        Servicio banio = new Servicio("baño");
        Servicio escalera = new Servicio("escalera");
        Servicio wifi = new Servicio("wifi");

        mcDonalds.agregarServicios(banio, escalera, wifi);

        Comunidad comunidad1 = new Comunidad("comunidad1");
        Comunidad comunidad2 = new Comunidad("comunidad2");

        Perfil perfil1 = new Perfil("JuanP", comunidad1, TipoPerfil.NORMAL);
        Perfil perfil2 = new Perfil("JuanP", comunidad2, TipoPerfil.NORMAL);

        comunidad1.agregarMiembros(perfil1);
        comunidad2.agregarMiembros(perfil2);

        Usuario julianPolaco = new Usuario("leofierens@frba.utn.edu.ar", "julianPolaco", "1234");
        julianPolaco.agregarEntidadInteres(entidadMcDonalds);
        julianPolaco.agregarServicioInteres(banio);

        ConfiguracionNotificacion configuracionNotificacion = new SinApuros(julianPolaco);
        configuracionNotificacion.setNotificador(new AdapterMailSender());
        configuracionNotificacion.agregarHorario(DiaSemana.VIERNES, "18", "37"); //aca va el horario que se quiere testear
        julianPolaco.setConfiguracionNotificacion(configuracionNotificacion);

        julianPolaco.agregarPerfil(perfil1);
        julianPolaco.agregarPerfil(perfil2);

        perfil1.setUsuario(julianPolaco);
        perfil2.setUsuario(julianPolaco);

        crearIncidente(mcDonalds, banio, julianPolaco);

        Thread.sleep(300000);

    }
    */

}
