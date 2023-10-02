package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;

import javax.naming.Context;

public class IncidentesController {
    private RepoIncidente repoIncidente;

    public IncidentesController(RepoIncidente repoIncidente) {
        this.repoIncidente = repoIncidente;
    }

    public void index(Context context){
        // TODO
    }

    public void show(Context context){
        // TODO
    }

    public void create(Context context){
        // TODO
    }

    public void save(Context context){
        // TODO
    }

    public void edit(Context context){
        // TODO
    }

    public void delete(Context context){
        // TODO
    }

    public void asignarParametros(Incidente incidente, Context context){
        // TODO
    }

}
