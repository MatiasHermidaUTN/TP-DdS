package ar.edu.utn.frba.dds.models.incidentes;

import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Prestacion extends Persistente {

    @OneToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @OneToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    @Transient
    private List<Incidente> incidentes;

    public Prestacion(Establecimiento establecimiento, Servicio servicio) {
        this.establecimiento = establecimiento;
        this.servicio = servicio;
        this.incidentes = new ArrayList<>();
    }

    public Prestacion() {
        this.incidentes = new ArrayList<>();
    }

    public void agregarIncidente(Incidente incidente){
        this.incidentes.add(incidente);
    }
    public void eliminarIncidente(Incidente incidente){
        this.incidentes.remove(incidente);
    }

    @Override
    public String toString() {
        return "{" +
                "\"establecimiento\":" + establecimiento +
                ", \"servicio\":" + servicio +
                ", \"incidentes\":" + incidentes +
                '}';
    }
}








