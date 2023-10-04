package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "incidente": controller = new IncidentesController(new RepoIncidente());
            break;
            // aca se instancian todos los controllers con su repositorio (inyeccion de dependencias)
        }
        return controller;
    }
}
