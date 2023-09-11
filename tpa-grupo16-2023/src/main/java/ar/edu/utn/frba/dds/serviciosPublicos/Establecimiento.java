package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.localizacion.Localizacion;
import ar.edu.utn.frba.dds.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Establecimiento extends Persistente {

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "entidad_id")
    private Entidad entidad;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "localizacion_id")
    private Localizacion localizacion;

    @Transient
    private List<Servicio> servicios;

    public Establecimiento() {this.servicios = new ArrayList<Servicio>();}

    public Establecimiento(String nombre) {
        this.nombre = nombre;
        this.servicios = new ArrayList<Servicio>();
    }

    public Establecimiento(String nombre, Localizacion localizacion) {
        this.nombre = nombre;
        this.localizacion = localizacion;
        this.servicios = new ArrayList<Servicio>();
    }

    public EstadoServicio estadoServicio(Servicio servicio) {
        return servicio.getEstado();
    }
    public Boolean tieneServicio(Servicio servicio){
        return this.servicios.contains(servicio);
    }

    public void agregarServicios(Servicio servicio1, Servicio ... servicios){
        this.servicios.add(servicio1);
        this.servicios.addAll(List.of(servicios));
    }

    public void eliminarServicio(Servicio servicio){
        this.servicios.remove(servicio);
    }

}
