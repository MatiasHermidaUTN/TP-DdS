package ar.edu.utn.frba.dds.models.repositorios.reposDeprecados;

import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoPrestacionDeprecado {

    private static RepoPrestacionDeprecado instancia = null;
    @Getter
    private static List<Prestacion> listaPrestaciones;

    public static void agregarPrestacion(Prestacion prestacion) {
        listaPrestaciones.add(prestacion);
    }

    private RepoPrestacionDeprecado() {
        listaPrestaciones = new ArrayList<>();
    }

    public static RepoPrestacionDeprecado getInstancia() {
        if (instancia == null) {
            instancia = new RepoPrestacionDeprecado();
        }
        return instancia;
    }
}