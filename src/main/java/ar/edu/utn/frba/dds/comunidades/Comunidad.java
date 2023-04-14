package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.serviciosPublicos.Estacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Estado;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Comunidad{
    private String nombre;
    private List<Persona> miembros;
    private List<Persona> admins;

    // Deberia tener una lista "servicios de la comunidad" ?


    public Comunidad(String nombre) {
        this.nombre = nombre;
    }

    public Servicio definirServicio(String nombre){
        return new Servicio(nombre);
    }

    // para buscar al servicio deberiamos pasarle en el input un servicio o solo el nombre del servicio?
    public void anunciaEstadoServicio(Estacion estacion, Servicio servicio, Estado estado) {
        // busca donde esta el servicio en la lista
        int indice = estacion.getServicios().indexOf(servicio);
        estacion.getServicios().get(indice).setEstado(estado);
    }

    public void agregarMiembro(Persona persona) {
        miembros.add(persona);
    }

    public void eliminarMiembro(Persona persona) {
        miembros.remove(persona);
    }

    public void agregarAdmin(Persona persona) {
        admins.add(persona);
    }

    public void eliminarAdmin(Persona persona) {
        admins.remove(persona);
    }
}
