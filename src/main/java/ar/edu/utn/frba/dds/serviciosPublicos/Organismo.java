package ar.edu.utn.frba.dds.serviciosPublicos;

import ar.edu.utn.frba.dds.repositorios.RepoOrganismo;
import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Localizacion;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Organismo {
    private String nombre;
    @Setter
    private Localizacion localizacion;

    public Organismo(String nombre) {
        this.nombre = nombre;
        RepoOrganismo.getInstancia().agregarOrganismo(this);
    }
}
