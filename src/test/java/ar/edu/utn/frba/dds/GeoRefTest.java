package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.localizacion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.utn.frba.dds.services.georef.AdapterGeoref;

import java.util.List;


public class GeoRefTest {
    AdapterGeoref adapterGeoref = null;

    @BeforeEach
    public void init(){
        adapterGeoref = AdapterGeoref.instancia();
    }

    @Test
    public void obtenerProvincias() throws Exception {
        List<Provincia> provincias  = adapterGeoref.obtenerListadoProvincias();
        for(Provincia unaProvincia : provincias){
            System.out.print(unaProvincia.nombre + " - ");
        }
    }
    @Test
    public void obtenerDepartamentos() throws Exception {
        Provincia provinciaElegida = new Provincia();
        provinciaElegida.id = 54;
        List<Departamento> departamentos = adapterGeoref.obtenerListadoDepartamentos(provinciaElegida);
        System.out.println("\n\nLos departamentos de la provincia " + provinciaElegida + " son:");
        for (Departamento unDepartamento : departamentos) {
            System.out.print(unDepartamento.nombre + " - ");
        }
    }
    @Test
    public void obtenerLocalidades() throws Exception {
        Departamento departamentoElegido = new Departamento();
        departamentoElegido.id = 54063;
        List<Localidad> localidades = adapterGeoref.obtenerListadoLocalidades(departamentoElegido);
        System.out.println("\n\nLas localidades del departamento " + departamentoElegido + " son:");
        for (Localidad unaLocalidad : localidades) {
            System.out.print(unaLocalidad.nombre + " - ");
        }
    }
    @Test
    public void obtenerLocalizacion() throws Exception {
        Departamento departamentoElegido = new Departamento();
        departamentoElegido.id = 54063;
        List<Localidad> localidades = adapterGeoref.obtenerListadoLocalidades(departamentoElegido);
        System.out.println("\n\nLas localidades del departamento " + departamentoElegido + " son:");
        for (Localidad unaLocalidad : localidades) {
            System.out.print(unaLocalidad.nombre + " - ");
        }
    }
    @Test
    public void obtenerUbicacion() throws Exception {
        Localidad localidadElegida = new Localidad();
        localidadElegida.id = 54063;
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(localidadElegida);

        System.out.println("\n\nunaUbicacion = " + unaUbicacion);
    }
}
