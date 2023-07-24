package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidad {
    private static RepoEntidad instancia = null;
    @Getter
    private static List<Entidad> listaEntidades;

    public static void agregarPrestacion(Entidad entidad) {
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
}