package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.localizacion.*;
import ar.edu.utn.frba.dds.models.georef.entities.RtaUbicacion;

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
        List<Departamento> dptos = georefServiceRetrofit.listadoDeDepartamentosDeProvincia(provincia).departamentos;
        dptos.forEach(departamento -> departamento.provincia = provincia);
        return dptos;
    }

    public List<Localidad> obtenerListadoLocalidades(Departamento departamento) throws IOException {
        List<Localidad> localidades = georefServiceRetrofit.listadoDeLocalidadesDeDepartamento(departamento).localidades;
        localidades.forEach(localidad -> localidad.departamento = departamento);
        return localidades;
    }

    public Localizacion obtenerLocalizacionDeLatLon(double latitud, double longitud) throws IOException {
        RtaUbicacion rta = georefServiceRetrofit.dptoYProvDeUbicacion(latitud, longitud);
        Provincia provincia = rta.ubicacion.provincia;
        Departamento departamento = rta.ubicacion.departamento;
        departamento.provincia = provincia;
        Localidad localidad = new Localidad();
        localidad.nombre = "Localidad Hardcodeada pq no se puede obtener de la API";   //TODO: no se puede obtener a partir de lat y lon, hay que hacerlo con listado de localidades
        localidad.localidad_id = 0;
        localidad.departamento = departamento;
        localidad.centroide = new Ubicacion(latitud, longitud);
        return new Localizacion(localidad, new Ubicacion(latitud, longitud));
    }

    public Localizacion obtenerLocalizacion(String nombreProv, String nombreDpto, String nombreLocalidad, String direccion) throws IOException {
        Provincia prov = georefServiceRetrofit.listadoDeProvincias().provincias.stream().filter(provincia -> provincia.nombre.equals(nombreProv)).toList().get(0);
        Departamento dpto = georefServiceRetrofit.listadoDeDepartamentosDeProvincia(prov).departamentos.stream().filter(departamento -> departamento.nombre.equals(nombreDpto)).toList().get(0);
        dpto.provincia = prov;
        Localidad localidad = georefServiceRetrofit.listadoDeLocalidadesDeDepartamento(dpto).localidades.stream().filter(localid -> localid.nombre.equals(nombreLocalidad)).toList().get(0);
        localidad.departamento = dpto;
        Ubicacion ubicacion = obtenerUbicacion(direccion, localidad);
        return new Localizacion(localidad, ubicacion);
    }

    public Ubicacion obtenerUbicacion(String direccion, Localidad localidad) throws IOException {
        return georefServiceRetrofit.listadoDeDirecciones(direccion, localidad).direcciones.get(0).ubicacion;
    }
}
