package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.repositorios.RepoComunidad;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadController {
    private RepoComunidad repoComunidad;

    public ComunidadController(RepoComunidad repoComunidad) {
        this.repoComunidad = repoComunidad;
    }

    public void index(Context context){
        Map<String, Object> model = new HashMap<>();
//        List<Incidente> incidentes = this.repoComunidad.buscarPorComunidad(Integer.parseInt(context.pathParam("comunidad_id")));

//        // buscamos el nombre del servicio y del establecimiento
//        Prestacion prestacion = RepoPrestacion.buscarPorId();
//
//        Establecimiento establecimiento = repoEstablecimiento.buscarPorId(prestacion.getEstablecimientoId());
//        Servicio servicio = repoServicio.buscarPorId(prestacion.getEstablecimientoId())

//        model.put("incidentes", incidentes);
//        context.render("incidentes/incidentes.hbs", model);
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
