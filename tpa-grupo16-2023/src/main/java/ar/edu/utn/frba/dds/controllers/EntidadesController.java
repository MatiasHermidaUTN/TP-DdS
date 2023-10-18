package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.localizacion.Localizacion;
import ar.edu.utn.frba.dds.models.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoEstablecimiento;
import ar.edu.utn.frba.dds.models.repositorios.RepoServicio;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadesController {

    private RepoEntidad repoEntidad;
    private RepoEstablecimiento repoEstablecimiento;
    private RepoServicio repoServicio;

    public EntidadesController(RepoEntidad repoEntidad, RepoEstablecimiento repoEstablecimiento, RepoServicio repoServicio) {
        this.repoEntidad = repoEntidad;
        this.repoEstablecimiento = repoEstablecimiento;
        this.repoServicio = repoServicio;
    }

    public void entidades(Context context) {
        List<Entidad> entidad = repoEntidad.buscarTodos();
        ObjectMapper ow = new ObjectMapper();
        String entidadesComoJson = null;
        try {
            entidadesComoJson = ow.writeValueAsString(entidad);
        } catch(JsonProcessingException e) {
            System.out.println("Error al crear el json de entidades");
        }
        String jsonEntidades = "{\"entidades\":" + entidadesComoJson + "}";
        context.json(jsonEntidades);
    }
    public void establecimientos(Context context) {
        List<Establecimiento> establecimientos = new ArrayList<>();
        if(context.queryParam("entidad") != null) {
            establecimientos = repoEstablecimiento.buscarPorEntidad(Integer.valueOf(context.queryParam("entidad")));
        } else {
            establecimientos = repoEstablecimiento.buscarTodos();
        }
        ObjectMapper ow = new ObjectMapper();
        String establecimientosComoJson = null;
        try {
            establecimientosComoJson = ow.writeValueAsString(establecimientos);
        } catch(JsonProcessingException e) {
            System.out.println("Error al crear el json de establecimientos");
        }
        String jsonEstablecimientos = "{\"establecimientos\":" + establecimientosComoJson + "}";
        context.json(jsonEstablecimientos);
    }

    public void servicios(Context context) {
        List<Servicio> servicios = new ArrayList<>();
        if(context.queryParam("establecimiento") != null) {
            servicios = repoServicio.buscarPorEstablecimiento(Integer.valueOf(context.queryParam("establecimiento")));
        } else {
            servicios = repoServicio.buscarTodos();
        }
        ObjectMapper ow = new ObjectMapper();
        String serviciosComoJson = null;
        try {
            serviciosComoJson = ow.writeValueAsString(servicios);
        } catch(JsonProcessingException e) {
            System.out.println("Error al crear el json de servicios");
        }
        String jsonServicios = "{\"servicios\":" + serviciosComoJson + "}";
        context.json(jsonServicios);
    }
}
