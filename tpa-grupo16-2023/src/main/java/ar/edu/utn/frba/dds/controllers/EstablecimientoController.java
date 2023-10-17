package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.models.localizacion.*;
import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoEstablecimiento;
import ar.edu.utn.frba.dds.models.repositorios.RepoLocalidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoLocalizacion;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;

import java.io.IOException;
import java.util.List;

public class EstablecimientoController {
    private RepoEntidad repoEntidad = new RepoEntidad();
    private RepoLocalizacion repoLocalizacion = new RepoLocalizacion();
    private RepoEstablecimiento repoEstablecimiento = new RepoEstablecimiento();
    private RepoLocalidad repoLocalidad = new RepoLocalidad();
    private AdapterGeoref adapterGeoref = AdapterGeoref.instancia();
    public void crearEstablecimento(String nombre, Integer idEntidad, String localidad) throws Exception{

        Localidad unaLocalidad = repoLocalidad.buscarPorNombre(localidad);
        Ubicacion unaUbicacion = unaLocalidad.centroide;

        Localizacion localizacion = new Localizacion(unaLocalidad, unaUbicacion);

        Entidad entidadEstablecimiento = repoEntidad.buscarPorId(idEntidad);
        entidadEstablecimiento.setLocalizacion(localizacion);

        Establecimiento nuevoEstablecimiento = new Establecimiento(nombre, entidadEstablecimiento, localizacion);
        repoEstablecimiento.guardar(nuevoEstablecimiento);
    }
}
