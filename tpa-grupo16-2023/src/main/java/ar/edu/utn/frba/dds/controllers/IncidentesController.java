package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidentesController {
    private RepoIncidente repoIncidente;

    public IncidentesController(RepoIncidente repoIncidente) {
        this.repoIncidente = repoIncidente;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repoIncidente.buscarPorComunidad(Integer.parseInt(context.pathParam("id")));

        model.put("incidentes", incidentes);
        context.render("incidentes/incidentes.hbs", model);
    }

    public void show(Context context){
        // TODO
    }

    public void create(Context context){
        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidente.hbs", model);
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

    public void cerrar(Context context){
        // TODO
    }

    public void asignarParametros(Incidente incidente, Context context){
        // TODO
    }

}
