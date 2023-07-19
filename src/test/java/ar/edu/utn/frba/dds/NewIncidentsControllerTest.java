package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.comunidades.Comunidad;
import ar.edu.utn.frba.dds.comunidades.Perfil;
import ar.edu.utn.frba.dds.comunidades.TipoPerfil;
import ar.edu.utn.frba.dds.comunidades.Usuario;
import ar.edu.utn.frba.dds.incidentes.EstadoIncidente;
import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.incidentes.Prestacion;
import ar.edu.utn.frba.dds.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;


public class NewIncidentsControllerTest {
    //Hay que charlar un par de cosas


    public void crear(Establecimiento establecimiento, Servicio servicio, Usuario usuarioApertura) {
        usuarioApertura.getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad())
                .forEach(comunidad -> comunidad.agregarIncidente(
                        new Incidente(establecimiento, comunidad.getNombre(), servicio, usuarioApertura)
                        ));

        // agregar incidente a la prestacion (no se si va acá pero ustedes confien)
        crear_o_agregar_prestacion(establecimiento, servicio);
    }

    // Si el servicio del establecimiento tiene la prestacion ya creada
    public void crear_o_agregar_prestacion(Establecimiento establecimiento, Servicio servicio) {
        if(buscar_prestacion_por_establecimiento(establecimiento.getNombre()))
        {
            Prestacion prestacion = new Prestacion(establecimiento, servicio);
            prestacion.agregarIncidente(new Incidente());
        }
        else {

        }

    }

    public Boolean buscar_prestacion_por_establecimiento_y_servicio(String nombreEstablecimiento, String nombreServicio){
        List<Prestacion> listaPrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();
        for(Prestacion prestacion : listaPrestaciones){
            if(prestacion.getEstablecimiento().getNombre() == nombreEstablecimiento){
                // si lo encuentro en el establecimiento, lo busco en los servicios del mismo
                for( : )
            }
        }
    }

    public void cerrarEnComunidad(Integer idIncidente, Perfil perfilCierre, LocalDateTime horarioCierre) {
        Incidente incidenteACerrar = perfilCierre.getComunidad().getIncidenteFromId(idIncidente);

        incidenteACerrar.setUsuarioCierre(perfilCierre.getUsuario());
        incidenteACerrar.setHorarioCierre(LocalDateTime.now());
        incidenteACerrar.setEstado(EstadoIncidente.RESUELTO);
    }

    @Test
    public void controladorIncidentesTest() throws Exception {
        Establecimiento mcDonalds = new Establecimiento("McDonalds");

        mcDonalds.agregarServicios( new Servicio("banio"),
                                    new Servicio("escalera"),
                                    new Servicio("wifi"));

        Comunidad comunidad1 = new Comunidad("comunidad1");
        Comunidad comunidad2 = new Comunidad("comunidad2");


        Perfil perfil1 = new Perfil("JuanP", comunidad1, TipoPerfil.NORMAL);
        Perfil perfil2 = new Perfil("JuanP", comunidad2, TipoPerfil.NORMAL);
        Usuario jp = new Usuario("JuanP@gmail.com", "JP", "1234");
        jp.agregarPerfil(perfil1);
        jp.agregarPerfil(perfil2);
    }
}
