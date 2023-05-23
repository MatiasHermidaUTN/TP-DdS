package ar.edu.utn.frba.dds.comunidades;

import ar.edu.utn.frba.dds.serviciosPublicos.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Comunidad {
    @Setter
    private String nombre;
    private List<Perfil> miembros;
    private List<Servicio> serviciosDeComunidad;

    public Comunidad(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
        this.serviciosDeComunidad = new ArrayList<>();
    }

    public void agregarMiembros(Perfil ... perfiles){
        Collections.addAll(this.miembros, perfiles);
    }

    public void eliminarMiembro(Perfil perfil){
        this.miembros.remove(perfil);
    }

    public void agregarServicios(Servicio ... servicios){

        Collections.addAll(this.serviciosDeComunidad, servicios);
    }

    public void eliminarServicio(Servicio servicio){

        this.serviciosDeComunidad.remove(servicio);
    }
}
