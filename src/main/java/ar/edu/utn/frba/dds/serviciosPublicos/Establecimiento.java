package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;

    public EstadoServicio estadoServicio(Servicio servicio) {
        return servicio.getEstado();
    }
    public Boolean tieneServicio(Servicio servicio){
        return this.servicios.contains(servicio);
    }

    public Establecimiento(String nombre, Localizacion localizacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.servicios = new ArrayList<>();
    }

    public void agregarServicios(Servicio ... servicios){
        Collections.addAll(this.servicios, servicios);
    }

    public void eliminarServicio(Servicio servicio){
        this.servicios.remove(servicio);
    }
}
