package ar.edu.utn.frba.dds.models.serviciosPublicos;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.persistencia.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
@Table
public class Servicio extends Persistente {
    
    @Column
    private String nombre;
    
    @ManyToMany
    private List<AtributoVariable> atributosVariables;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_servicio")
    private EstadoServicio estado;
    
    @Transient
    private List<RegistroDeCambio> registro;

    @ManyToOne(cascade = { CascadeType.ALL})
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    public Servicio() {
        this.atributosVariables = new ArrayList<>();
        this.registro = new ArrayList<>();
    }

    public Servicio(String nombre) {
        this.nombre = nombre;
        this.atributosVariables = new ArrayList<>();
        this.estado = EstadoServicio.DISPONIBLE;
        this.registro = new ArrayList<>();
        // guardamos fecha de creacion del servicio
        this.registrarCambio(EstadoServicio.DISPONIBLE, LocalDateTime.now(), new Usuario("default@mail.com", "default", "default"));
    }

    public void setEstado(EstadoServicio estado, Usuario usuario) {
        this.estado = estado;
        registrarCambio(estado, LocalDateTime.now(), usuario);
    }

    private void registrarCambio(EstadoServicio estadoServicio, LocalDateTime now, Usuario usuario) {
        RegistroDeCambio registroDeCambio = new RegistroDeCambio(estadoServicio, now, usuario);
        this.registro.add(registroDeCambio);
    }

    public void agregarAtributoVar(String nombre, String valor) {
        AtributoVariable nuevoAtributo = new AtributoVariable(nombre, valor);
        this.atributosVariables.add(nuevoAtributo);
    }

    public void eliminarAtributoVar(String nombre){
        this.atributosVariables.removeIf(atributo -> atributo.getNombre_atributo() == nombre);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + this.getId() +
                ", \"nombre\":\"" + nombre + '\"' +
                ", \"atributosVariables\":" + atributosVariables +
                ", \"estado\":" + estado +
                ", \"registro\":" + registro +
                ", \"establecimiento\":" + establecimiento +
                '}';
    }
}
