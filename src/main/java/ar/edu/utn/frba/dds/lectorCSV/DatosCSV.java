package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Organismo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DatosCSV {
    private List<Entidad> listaEntidades;
    private List<Organismo> listaOrganismos;

    public DatosCSV() {
        this.listaOrganismos = new ArrayList<>();
        this.listaEntidades = new ArrayList<>();
    }

    public void agregarEntidad(Entidad entidad) {
        this.listaEntidades.add(entidad);
    }

    public void agregarOrganismo(Organismo organismo) {
        this.listaOrganismos.add(organismo);
    }

    public void mostrarEntidades(){
        for(Entidad entidad : listaEntidades) {
            System.out.println(entidad.getNombre());
        }
    }

    public void mostrarOrganismos(){
        for(Organismo organismo : listaOrganismos) {
            System.out.println(organismo.getNombre());
        }
    }
}
