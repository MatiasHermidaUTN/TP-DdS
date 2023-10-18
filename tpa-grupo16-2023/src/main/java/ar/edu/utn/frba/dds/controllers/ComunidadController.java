package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
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
        // TODO
    }

    public void show(Context context){
        Comunidad comunidad = this.repoComunidad.buscarPorId(Integer.valueOf(context.pathParam("comunidad_id")));
        Map<String, Object> model = new HashMap<>();
        model.put("comunidad", comunidad);

        Boolean activa = comunidad.getActiva();
        String estadoComunidad = "";
        if(activa != null && activa)
            estadoComunidad = "ACTIVA";
        else if(activa != null)
            estadoComunidad = "NO ACTIVA";
        model.put("estadoComunidad", estadoComunidad);

        context.render("comunidades/comunidad.hbs", model);
    }

    public void create(Context context){
        // TODO
    }

    public void procesar_creacion(Context context) {
        //TODO
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
