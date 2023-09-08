package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.OrganismoDeControl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DatosCSV {
    private List<Entidad> listaEntidades;
    private List<OrganismoDeControl> listaOrganismos;

    public DatosCSV() {
        this.listaOrganismos = new ArrayList<>();
        this.listaEntidades = new ArrayList<>();
    }

    public void agregarEntidad(Entidad entidad) {
        this.listaEntidades.add(entidad);
    }

    public void agregarOrganismo(OrganismoDeControl organismo) {
        this.listaOrganismos.add(organismo);
    }

    public void mostrarEntidades(){
        for(Entidad entidad : listaEntidades) {
            System.out.println(entidad.getNombre());
        }
    }

    public void mostrarOrganismos(){
        for(OrganismoDeControl organismo : listaOrganismos) {
            System.out.println(organismo.getNombre());
        }
    }
}
