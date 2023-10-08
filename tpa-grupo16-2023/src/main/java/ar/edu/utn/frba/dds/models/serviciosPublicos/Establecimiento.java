package ar.edu.utn.frba.dds.models.serviciosPublicos;

import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Establecimiento extends Persistente {

    @Column
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "entidad_id", referencedColumnName = "id")
    private Entidad entidad;

    @OneToOne
    @JoinColumn(name = "localizacion_id", referencedColumnName = "id")
    private Localizacion localizacion;

    @Transient
    private List<Servicio> servicios;

    public Establecimiento() {this.servicios = new ArrayList<Servicio>();}

    public Establecimiento(String nombre) {
        this.nombre = nombre;
        this.servicios = new ArrayList<Servicio>();
    }

    public Establecimiento(String nombre, Entidad entidad, Localizacion localizacion) {
        this.nombre = nombre;
        this.entidad = entidad;
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

    @Override
    public String toString() {
        return "{" +
                "\"nombre\":\"" + nombre + '\"' +
                ", \"entidad\":" + entidad +
                ", \"localizacion\":" + localizacion +
                ", \"servicios\":" + servicios +
                '}';
    }
}
