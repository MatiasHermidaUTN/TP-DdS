package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidad {

    private static RepoEntidad instancia = null;
    private static List<Entidad> listaEntidades;

    public static void agregarEntidad(Entidad entidad) {
        listaEntidades.add(entidad);
    }

    private RepoEntidad() {
        listaEntidades = new ArrayList<>();
    }

    public static RepoEntidad getInstancia() {
        if (instancia == null) {
            instancia = new RepoEntidad();
        }
        return instancia;
    }

    public void mostrarEntidades(){
        for(Entidad entidad : listaEntidades){
            System.out.println(entidad.getNombre());
        }
    }
}