package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrganismoDeControl {
    private String nombre;

    private List<Entidad> entidadesControladas;
    private List<Servicio> serviciosControlados;

    public OrganismoDeControl(String nombre) {
        this.nombre = nombre;
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
