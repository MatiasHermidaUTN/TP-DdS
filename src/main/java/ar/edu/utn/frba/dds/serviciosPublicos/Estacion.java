package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Estacion {
    private String nombre;
    private Ubicacion ubicacion;
    private List<Servicio> servicios;

    // Deberiamos poner los servicios default
    public Estacion(String nombre, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public boolean tieneServicio(Servicio servicio) {
        return servicios.contains(servicio);
    }

    public Estado estadoServicio(Servicio servicio) {
        if (tieneServicio(servicio)){
            return servicio.getEstado();
        }
        else {
            return null; // si no lo encuentra
        }
    }
}
