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
import java.util.Objects;
import java.util.stream.Collectors;


public class NewIncidentsControllerTest {
    //Hay que charlar un par de cosas


    public void crear(Establecimiento establecimiento, Servicio servicio, Usuario usuarioApertura) {
        Incidente Incidente;
        usuarioApertura.getPerfiles()
                .stream()
                .map(perfil -> perfil.getComunidad())
                .forEach(comunidad ->
                                incidente = new Incidente(establecimiento, comunidad.getNombre(), servicio, usuarioApertura),
                        comunidad.agregarIncidente(incidente),
                        crear_o_agregar_prestacion(establecimiento, servicio, incidente));


    }

    // buscar_prestacion_por_establecimiento_y_servicio
    // Esto esta mal en la parte de buscar servicio
    /*
    public void crear_o_agregar_prestacion(Establecimiento establecimiento, Servicio servicio_a_buscar, Incidente incidente){
        List<Prestacion> listaPrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();
        for(Prestacion prestacion : listaPrestaciones){
            if(Objects.equals(prestacion.getEstablecimiento().getNombre(), establecimiento.getNombre())){

                // si lo encuentro en el establecimiento, lo busco en los servicios del mismo
                for(Servicio servicio : prestacion.getEstablecimiento().getServicios()){

                    if(Objects.equals(servicio.getNombre(), servicio_a_buscar.getNombre()))
                        // Ya hay una prestacion creada, solo agrego el incidente a la misma
                        prestacion.agregarIncidente(incidente);

                    else{
                        // si no hay prestacion asociada al servicio del establecimiento
                        Prestacion nuevaPrestacion = new Prestacion(establecimiento, servicio_a_buscar);
                        nuevaPrestacion.agregarIncidente(incidente);
                    }
                }
            }

            else{
                // si no hay prestacion del establecimiento
                Prestacion nuevaPrestacion = new Prestacion(establecimiento, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
            }

        }
    }
    */


    // Lo voy a hacer con un filter
    public void crear_o_agregar_prestacion(Establecimiento establecimiento_a_buscar, Servicio servicio_a_buscar, Incidente incidente){

        List<Prestacion> listaPrestaciones = RepoPrestacion.getInstancia().getListaPrestaciones();

        List <Prestacion> listaPrestacionesDelEstablecimiento = listaPrestaciones.stream()
                .filter(prestacion -> prestacion.getEstablecimiento().getNombre() == establecimiento_a_buscar.getNombre())
                .collect(Collectors.toList());

        if(listaPrestacionesDelEstablecimiento == null){
            Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
            nuevaPrestacion.agregarIncidente(incidente);
        }
        else {
            Prestacion prestacionDelServicioDelEstablecimiento =  listaPrestacionesDelEstablecimiento.stream().
                    filter(prestacion -> prestacion.getServicio().getNombre() == servicio_a_buscar.getNombre())
                    .findAny()
                    .orElse(null);

            if(prestacionDelServicioDelEstablecimiento == null){
                Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
            }
            else {
                prestacionDelServicioDelEstablecimiento.agregarIncidente(incidente);
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
