package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.incidentes.Incidente;
import ar.edu.utn.frba.dds.repositorios.RepoComunidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
public class Comunidad {

    @Id
    @GeneratedValue
    private Integer comunidad_id;

    @Setter
    @Column(name = "comunidad_nombre")
    private String nombre;

    @Transient
    private List<Perfil> miembros;

    @ManyToMany
    private List<Servicio> serviciosDeComunidad;

    @Transient
    private List<Incidente> incidentes;

    public Comunidad(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
        this.serviciosDeComunidad = new ArrayList<>();
        this.incidentes = new ArrayList<>();
        RepoComunidad.getInstancia().agregarComunidad(this);
    }

    public Comunidad() {

    }

    public void agregarMiembros(Perfil ... perfiles){
        Collections.addAll(this.miembros, perfiles);
    }

    public void eliminarMiembro(Perfil perfil){
        this.miembros.remove(perfil);
    }

    public void agregarServicios(Servicio servicio, Servicio ... servicios){
        this.serviciosDeComunidad.add(servicio);
        Collections.addAll(this.serviciosDeComunidad, servicios);
    }

    public void eliminarServicio(Servicio servicio){

        this.serviciosDeComunidad.remove(servicio);
    }

    public void agregarIncidente(Incidente incidente) {
        this.incidentes.add(incidente);
    }

    public Incidente getIncidenteFromId(Integer idIncidente) {
        return incidentes.stream()
                .filter(incidente -> incidente.getIdIncidente().equals(idIncidente))
                .toList()
                .get(0);
    }
}
