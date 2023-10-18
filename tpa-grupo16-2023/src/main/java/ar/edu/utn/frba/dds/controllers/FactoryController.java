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

            case "usuario": controller = new UsuariosController(new RepoUsuario(),new RepoLocalidad(), new RepoComunidad(), new RepoPerfil(), new RepoHorario(), AdapterGeoref.instancia(), new Validador(), new RepoProvincia(), new RepoEntidad(), new RepoServicio());
            break;

            case "comunidad": controller = new ComunidadController(new RepoComunidad());
            break;

            case "cargadorCSV": controller = new CargadorCSVController(new RepoEntidad(), new RepoOrganismoDeControl());
            break;

            case "entidades": controller = new EntidadesController(new RepoEntidad(), new RepoEstablecimiento(), new RepoServicio());
            break;

            case "informes": controller = new InformesController(new RepoInforme());
            break;

            case "localizacion": controller = new LocalizacionController(new RepoProvincia(), new RepoDepartamento(), new RepoLocalidad());
            break;
        }
        return controller;
    }
}
