package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoOrganismoDeControl;
import io.javalin.http.Context;

public class CargadorCSVController {
    private RepoEntidad repoEntidad;
    private RepoOrganismoDeControl repoOrganismoDeControl;

    public CargadorCSVController(RepoEntidad repoEntidad, RepoOrganismoDeControl repoOrganismoDeControl) {
        this.repoEntidad = repoEntidad;
        this.repoOrganismoDeControl = repoOrganismoDeControl;
    }

    public void cargarDatos(Context context) {
        //TODO b. Carga masiva de datos de entidades prestadoras y organismos de control
    }
}
