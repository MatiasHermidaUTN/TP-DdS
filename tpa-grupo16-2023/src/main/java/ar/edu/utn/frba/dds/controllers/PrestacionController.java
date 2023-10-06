package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoPrestacion;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoPrestacionDeprecado;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;

import java.util.List;
import java.util.stream.Collectors;

public class PrestacionController {

    private RepoPrestacion repoPrestacion;

    public PrestacionController(RepoPrestacion repoPrestacion) {
        this.repoPrestacion = repoPrestacion;
    }

    public void crear_o_agregar_prestacion(Establecimiento establecimiento_a_buscar, Servicio servicio_a_buscar, Incidente incidente){

        List<Prestacion> listaPrestaciones = repoPrestacion.buscarTodos();

        List <Prestacion> listaPrestacionesDelEstablecimiento = listaPrestaciones.stream()
                .filter(prestacion -> prestacion.getEstablecimiento().getNombre() == establecimiento_a_buscar.getNombre())
                .toList();

        if(listaPrestacionesDelEstablecimiento.isEmpty()){
            Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
            nuevaPrestacion.agregarIncidente(incidente);
            repoPrestacion.guardar(nuevaPrestacion);
        }
        else {
            Prestacion prestacionDelServicioDelEstablecimiento =  listaPrestacionesDelEstablecimiento.stream()
                    .filter(prestacion -> prestacion.getServicio().getNombre() == servicio_a_buscar.getNombre())
                    .findAny()
                    .orElse(null);

            if(prestacionDelServicioDelEstablecimiento == null){
                Prestacion nuevaPrestacion = new Prestacion(establecimiento_a_buscar, servicio_a_buscar);
                nuevaPrestacion.agregarIncidente(incidente);
                repoPrestacion.guardar(nuevaPrestacion);
            }
            else {
                prestacionDelServicioDelEstablecimiento.agregarIncidente(incidente);
            }
        }
    }
}
