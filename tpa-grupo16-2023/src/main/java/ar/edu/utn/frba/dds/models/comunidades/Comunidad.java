package ar.edu.utn.frba.dds.models.comunidades;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Comunidad extends Persistente {

    @Column(name = "comunidad_nombre")
    private String nombre;

    @Transient
    private List<Perfil> miembros;

    @ManyToMany
    private List<Servicio> serviciosDeComunidad;

    @ManyToMany
    private List<Establecimiento> establecimientosDeComunidad;

    @Transient
    private List<Incidente> incidentes;

    @Enumerated(EnumType.STRING)
    @Column(name = "grado_de_confianza")
    private GradoDeConfianza gradoDeConfianza;

    @Column(name = "activa")
    private Boolean activa;

    public Comunidad(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
        this.serviciosDeComunidad = new ArrayList<>();
        this.establecimientosDeComunidad = new ArrayList<>();
        this.incidentes = new ArrayList<>();
    }

    public Comunidad() {
        this.miembros = new ArrayList<>();
        this.serviciosDeComunidad = new ArrayList<>();
        this.establecimientosDeComunidad = new ArrayList<>();
        this.incidentes = new ArrayList<>();
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
                .filter(incidente -> incidente.getId().equals(idIncidente))
                .toList()
                .get(0);
    }

    public Integer getCantPerfilesAfectados() {
        return this.miembros.stream().filter(Perfil::esAfectado).toList().size();
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + this.getId() +
                ", \"nombre\":\"" + nombre + '\"' +
                ", \"miembros\":" + miembros +
                ", \"serviciosDeComunidad\":" + serviciosDeComunidad +
                ", \"establecimientosDeComunidad\":" + establecimientosDeComunidad +
                ", \"incidentes\":" + incidentes +
                ", \"gradoDeConfianza\":\"" + gradoDeConfianza + '\"' +
                ", \"activa\":" + activa +
                '}';
    }

    public List<Integer> getIdUsuarios() {
        return this.miembros.stream().map(Perfil::getId).toList();
    }

    public List<Integer> getIdEstablecimientos() {
        return this.establecimientosDeComunidad.stream().map(Establecimiento::getId).toList();
    }

    public List<Integer> getIdServicios() {
        return this.serviciosDeComunidad.stream().map(Servicio::getId).toList();
    }
}
