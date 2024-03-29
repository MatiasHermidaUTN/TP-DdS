package ar.edu.utn.frba.dds.models.lectorCSV;

import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoOrganismoDeControl;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DatosCSV {
    private List<Entidad> listaEntidades;
    private List<OrganismoDeControl> listaOrganismos;
    static RepoOrganismoDeControl repoOrganismoDeControl = new RepoOrganismoDeControl();
    static RepoEntidad repoEntidad = new RepoEntidad();

    public DatosCSV() {
        this.listaOrganismos = new ArrayList<>();
        this.listaEntidades = new ArrayList<>();
    }

    public void agregarEntidad(Entidad entidad) {
        this.listaEntidades.add(entidad);
        repoEntidad.guardar(entidad);
    }

    public void agregarOrganismo(OrganismoDeControl organismo) {
        this.listaOrganismos.add(organismo);
        repoOrganismoDeControl.guardar(organismo);
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
