package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Organismo;

import java.util.ArrayList;
import java.util.List;

public class RepoOrganismo {
    private static RepoOrganismo instancia = null;
    private static List<Organismo> listaOrganismos;

    public static void agregarOrganismo(Organismo org) {
        listaOrganismos.add(org);
    }

    private RepoOrganismo() {
        listaOrganismos = new ArrayList<>();
    }

    public static RepoOrganismo getInstancia() {
        if (instancia == null) {
            instancia = new RepoOrganismo();
        }
        return instancia;
    }

    public void mostrarOrganismos(){
        for(Organismo org : listaOrganismos){
            System.out.println(org.getNombre());
        }
    }
}
