package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.repositorios.*;
import ar.edu.utn.frba.dds.models.validador.Validador;

public class FactoryController {

    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "incidente": controller = new IncidentesController(new RepoIncidente(), new RepoPerfil(), new RepoEntidad(), new RepoEstablecimiento(), new RepoServicio(), new RepoUsuario(), new RepoComunidad());
            break;

            case "usuario": controller = new UsuariosController(new RepoUsuario(),new RepoLocalidad(), new RepoComunidad(), new RepoPerfil(), AdapterGeoref.instancia(), new Validador());
            break;

            case "comunidad": controller = new ComunidadController(new RepoComunidad());
            break;
        }
        return controller;
    }
}
