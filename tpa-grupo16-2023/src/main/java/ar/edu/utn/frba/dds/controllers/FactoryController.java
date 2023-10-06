package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoUsuario;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "incidente": controller = new IncidentesController(new RepoIncidente());
            break;

            case "usuario": controller = new UsuariosController(new RepoUsuario());
            break;
        }
        return controller;
    }
}
