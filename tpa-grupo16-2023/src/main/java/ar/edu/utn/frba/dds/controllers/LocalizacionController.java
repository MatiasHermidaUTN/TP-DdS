package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.localizacion.Departamento;
import ar.edu.utn.frba.dds.models.localizacion.Localidad;
import ar.edu.utn.frba.dds.models.localizacion.Provincia;
import ar.edu.utn.frba.dds.models.repositorios.RepoDepartamento;
import ar.edu.utn.frba.dds.models.repositorios.RepoLocalidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoProvincia;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Servicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class LocalizacionController {
    private RepoProvincia repoProvincia;
    private RepoDepartamento repoDepartamento;
    private RepoLocalidad repoLocalidad;

    public LocalizacionController(RepoProvincia repoProvincia, RepoDepartamento repoDepartamento, RepoLocalidad repoLocalidad) {
        this.repoProvincia = repoProvincia;
        this.repoDepartamento = repoDepartamento;
        this.repoLocalidad = repoLocalidad;
    }

    public void provincias(Context context) {
        List<Provincia> provincias = this.repoProvincia.buscarTodos();
        context.json(provincias);
    }

    public void departamentos(Context context) {
        List<Departamento> departamentos = new ArrayList<>();
        if(context.queryParam("provincia") != null) {
            departamentos = repoDepartamento.buscarPorProvincia(Integer.valueOf(context.queryParam("provincia")));
        } else {
            departamentos = repoDepartamento.buscarTodos();
        }

        ObjectMapper ow = new ObjectMapper();
        String departamentosComoJson = null;
        try {
            departamentosComoJson = ow.writeValueAsString(departamentos);
        } catch(JsonProcessingException e) {
            System.out.println("Error al crear el json de departamentos");
        }

        String jsonDepartamentos = "{\"departamentos\":" + departamentosComoJson + "}";
        context.json(jsonDepartamentos);
    }

    public void localidades(Context context) {
        List<Localidad> localidades = new ArrayList<>();
        if(context.queryParam("departamento") != null) {
            localidades = repoLocalidad.buscarPorDepartamento(Integer.valueOf(context.queryParam("departamento")));
        } else {
            localidades = repoLocalidad.buscarTodos();
        }

        ObjectMapper ow = new ObjectMapper();
        String localidadesComoJson = null;
        try {
            localidadesComoJson = ow.writeValueAsString(localidades);
        } catch(JsonProcessingException e) {
            System.out.println("Error al crear el json de localidades");
        }

        String jsonLocalidades = "{\"localidades\":" + localidadesComoJson + "}";
        context.json(jsonLocalidades);
    }
}
