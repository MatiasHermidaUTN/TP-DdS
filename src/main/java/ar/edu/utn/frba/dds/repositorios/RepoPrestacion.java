package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.incidentes.Prestacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class RepoPrestacion {

    private static RepoPrestacion instancia = null;
    @Getter
    private static List<Prestacion> listaPrestaciones;

    public static void agregarUsuario(Prestacion prestacion) {
        listaPrestaciones.add(prestacion);
    }

    private RepoPrestacion() {
        listaPrestaciones = new ArrayList<>();
    }

    public static RepoPrestacion getInstancia() {
        if (instancia == null) {
            instancia = new RepoPrestacion();
        }
        return instancia;
    }
}