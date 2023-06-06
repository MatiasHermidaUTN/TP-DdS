package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.serviciosPublicos.Ubicacion;

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

    public List<String> obtenerListadoProvincias() throws IOException {
        return georefServiceRetrofit.listadoDeProvincias().provincias.stream().map(provincia -> provincia.nombre).toList();
    }

    public List<String> obtenerListadoMunicipios(String nombreProvincia) throws IOException {
        return georefServiceRetrofit.listadoDeMunicipiosDeProvincia(nombreProvincia).municipios.stream().map(municipio -> municipio.nombre).toList();
    }

    public List<String> obtenerListadoDepartamentos(String nombreProvincia) throws IOException {
        return georefServiceRetrofit.listadoDeDepartamentosDeProvincia(nombreProvincia).departamentos.stream().map(departamento -> departamento.nombre).toList();
    }

    public Ubicacion obtenerUbicacion(String nombreProvincia, String nombreDepartamento, String nombreMunicipio) throws IOException {
        Float latitud = null;
        Float longitud = null;
        if (!nombreDepartamento.isEmpty()){
            latitud = georefServiceRetrofit.listadoDeDepartamentosDeProvincia(nombreProvincia).departamentoDeNombre(nombreDepartamento).centroide.lat;
            longitud = georefServiceRetrofit.listadoDeDepartamentosDeProvincia(nombreProvincia).departamentoDeNombre(nombreDepartamento).centroide.lon;
        } else if (!nombreMunicipio.isEmpty()) {
            latitud = georefServiceRetrofit.listadoDeMunicipiosDeProvincia(nombreProvincia).municipioDeNombre(nombreMunicipio).centroide.lat;
            longitud = georefServiceRetrofit.listadoDeMunicipiosDeProvincia(nombreProvincia).municipioDeNombre(nombreMunicipio).centroide.lon;
        } else {
            latitud = georefServiceRetrofit.listadoDeProvincias().provinciaDeNombre(nombreProvincia).centroide.lat;
            longitud = georefServiceRetrofit.listadoDeProvincias().provinciaDeNombre(nombreProvincia).centroide.lon;
        }
        return new Ubicacion(latitud, longitud);
    }
}
