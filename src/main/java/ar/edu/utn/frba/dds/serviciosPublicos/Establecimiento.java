package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Establecimiento {
    private String nombre;
    private Localizacion localizacion;
    private List<Servicio> servicios;

    public Establecimiento(String nombre) {
        this.nombre = nombre;
    }

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

    public Establecimiento() {
    }

    public void agregarServicios(Servicio servio1, Servicio ... servicios){
        this.servicios.add(servio1);
        this.servicios.addAll(List.of(servicios));
    }

    public void eliminarServicio(Servicio servicio){
        this.servicios.remove(servicio);
    }
}
