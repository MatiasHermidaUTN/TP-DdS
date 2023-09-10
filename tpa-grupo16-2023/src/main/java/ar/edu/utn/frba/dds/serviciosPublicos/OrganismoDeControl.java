package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@Table
public class OrganismoDeControl {

    @Id
    @GeneratedValue
    private Integer organismo_id;

    @Column
    private String nombre;

    @ManyToMany
    private List<Entidad> entidadesControladas;

    @ManyToMany
    private List<Servicio> serviciosControlados;

    public OrganismoDeControl(String nombre) {
        this.nombre = nombre;
        this.entidadesControladas = new ArrayList<>();
        this.serviciosControlados = new ArrayList<>();
    }

    public OrganismoDeControl() {
        this.entidadesControladas = new ArrayList<>();
        this.serviciosControlados = new ArrayList<>();
    }

    public void agregarEntidad(Entidad entidad) {
        this.entidadesControladas.add(entidad);
    }

    public void agregarServicio(Servicio servicio) {
        this.serviciosControlados.add(servicio);
    }
}
