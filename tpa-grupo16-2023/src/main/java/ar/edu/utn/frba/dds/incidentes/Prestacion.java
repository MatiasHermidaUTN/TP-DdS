package ar.edu.utn.frba.dds.incidentes;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Prestacion {

    @Id
    @GeneratedValue
    private Integer prestacion_id;

    @ManyToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "establecimiento_id")
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "servicio_id")
    private Servicio servicio;

    @Transient
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








