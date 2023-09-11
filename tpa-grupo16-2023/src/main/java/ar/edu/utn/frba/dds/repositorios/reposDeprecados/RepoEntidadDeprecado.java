package ar.edu.utn.frba.dds.repositorios.reposDeprecados;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoEntidadDeprecado {
    private static RepoEntidadDeprecado instancia = null;
    @Getter
    private static List<Entidad> listaEntidades;

    public static void agregarEntidad(Entidad entidad) {
        listaEntidades.add(entidad);
    }

    private RepoEntidadDeprecado() {
        listaEntidades = new ArrayList<>();
    }

    public static RepoEntidadDeprecado getInstancia() {
        if (instancia == null) {
            instancia = new RepoEntidadDeprecado();
        }
        return instancia;
    }
}