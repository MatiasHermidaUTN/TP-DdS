package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.localizacion.*;
import ar.edu.utn.frba.dds.services.georef.entities.Direccion;
import ar.edu.utn.frba.dds.services.georef.entities.RtaUbicacion;
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
            System.out.print(unaProvincia.id + ") ");
            System.out.print(unaProvincia.centroide.toString() + "_");
            System.out.print(unaProvincia.nombre + " - ");
        }
    }
    @Test
    public void obtenerDepartamentos() throws Exception {
        Provincia provinciaElegida = new Provincia();
        provinciaElegida.id = "54";
        provinciaElegida.nombre = "Misiones";
        List<Departamento> departamentos = adapterGeoref.obtenerListadoDepartamentos(provinciaElegida);
        System.out.println("\n\nLos departamentos de la provincia " + provinciaElegida.id + ") " + provinciaElegida.nombre + " son:");
        for (Departamento unDepartamento : departamentos) {
            System.out.print(unDepartamento.id + ") ");
            System.out.print(unDepartamento.nombre + ", de la provincia ");
            System.out.print(unDepartamento.provincia.nombre + " \n ");
        }
    }
    @Test
    public void obtenerLocalidades() throws Exception {
        Departamento departamentoElegido = new Departamento();
        departamentoElegido.id = "54063";
        departamentoElegido.nombre = "Iguazú";
        List<Localidad> localidades = adapterGeoref.obtenerListadoLocalidades(departamentoElegido);
        System.out.println("\n\nLas localidades del departamento " + departamentoElegido.id + ") " + departamentoElegido.nombre + " son:");
        for (Localidad unaLocalidad : localidades) {
            System.out.print(unaLocalidad.id + ") ");
            System.out.print(unaLocalidad.nombre + ", del departamento ");
            System.out.print(unaLocalidad.departamento.nombre + " \n");
        }
    }
    @Test
    public void obtenerLocalizacion() throws Exception {
        Localizacion localizacion = adapterGeoref.obtenerLocalizacion(-34.603722, -58.381592);
        System.out.println("\n\nLa localizacion de la lat " + -34.603722 + " y long " + -58.381592 + " es:");
        System.out.print(localizacion.toString());
    }
    @Test
    public void obtenerUbicacion() throws Exception {
        Localidad localidadElegida = new Localidad();
        localidadElegida.nombre = "san nicolas";
        String direccion = "av corrientes 1050";
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(direccion, localidadElegida);
        System.out.println("\n\nunaUbicacion = " + unaUbicacion.toString());
    }
    @Test
    public void estanCerca2Localizaciones() throws Exception {
        Localizacion localizacion1 = new Localizacion(new Ubicacion(-34.60806241126338, -58.39352197717003));
        Localizacion localizacion2 = new Localizacion(new Ubicacion(-34.60846183769753, -58.39895013198136));
        Boolean estanCerca = AdapterCercaniaLocalizacion.estaCerca(localizacion1, localizacion2);
        System.out.println("\n\nestanCerca = " + estanCerca);
    }
}
