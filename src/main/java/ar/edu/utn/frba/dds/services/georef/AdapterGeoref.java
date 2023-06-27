package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.localizacion.*;

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

    public Localizacion obtenerLocalizacion(Ubicacion ubicacion) throws IOException {
        return new Localizacion(new Provincia(), new Departamento(), new Localidad(), new Ubicacion(1.1F, 1.1F));
    }

    public Ubicacion obtenerUbicacion(Localidad localidad) throws IOException {
        return localidad.centroide;
    }
}
