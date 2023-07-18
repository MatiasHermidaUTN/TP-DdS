package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.localizacion.*;
import ar.edu.utn.frba.dds.services.georef.entities.Direccion;
import ar.edu.utn.frba.dds.services.georef.entities.RtaUbicacion;

import java.io.IOException;
import java.util.List;

public class AdapterGeoref {
    private static AdapterGeoref instancia = null;
    private static GeorefServiceRetrofit georefServiceRetrofit;

    private AdapterGeoref(){
        georefServiceRetrofit = GeorefServiceRetrofit.instancia();
    }

    public static AdapterGeoref instancia(){
        if(instancia == null){
            instancia = new AdapterGeoref();
        }
        return instancia;
    }

    public List<Provincia> obtenerListadoProvincias() throws IOException {
        return georefServiceRetrofit.listadoDeProvincias().provincias;
    }

    public List<Departamento> obtenerListadoDepartamentos(Provincia provincia) throws IOException {
        return georefServiceRetrofit.listadoDeDepartamentosDeProvincia(provincia).departamentos;
    }

    public List<Localidad> obtenerListadoLocalidades(Departamento departamento) throws IOException {
        return georefServiceRetrofit.listadoDeLocalidadesDeDepartamento(departamento).localidades;
    }

    public Localizacion obtenerLocalizacion(double latitud, double longitud) throws IOException {
        RtaUbicacion rta = georefServiceRetrofit.dptoYProvDeUbicacion(latitud, longitud);
        Provincia provincia = rta.ubicacion.provincia;
        Departamento departamento = rta.ubicacion.departamento;
        departamento.provincia = provincia;
        Localidad localidad = new Localidad();
        localidad.nombre = "Localidad Hardcodeada pq no se puede obtener de la API";   //TODO: no se puede obtener a partir de lat y lon, hay que hacerlo con listado de localidades
        localidad.id = "0";
        localidad.departamento = departamento;
        localidad.centroide = new Ubicacion(latitud, longitud);
        return new Localizacion(localidad, new Ubicacion(latitud, longitud));
    }

    public Ubicacion obtenerUbicacion(String direccion, Localidad localidad) throws IOException {
        return georefServiceRetrofit.listadoDeDirecciones(direccion, localidad).direcciones.get(0).ubicacion;
    }
}
