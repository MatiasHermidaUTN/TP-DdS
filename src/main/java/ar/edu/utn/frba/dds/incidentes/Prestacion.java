package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Prestacion {
    private Establecimiento establecimiento;
    private Servicio servicio;
    private List<Incidente> incidentes;

    public Prestacion(Establecimiento establecimiento, Servicio servicio) {
        this.establecimiento = establecimiento;
        this.servicio = servicio;
        this.incidentes = new ArrayList<>();
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }
    public void eliminarIncidente(Incidente incidente){
        this.incidentes.remove(incidente);
    }
}








