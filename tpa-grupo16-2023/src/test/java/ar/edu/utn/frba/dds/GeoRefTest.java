package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.localizacion.*;
import ar.edu.utn.frba.dds.models.localizacion.*;
import ar.edu.utn.frba.dds.models.repositorios.reposDeprecados.RepoPrestacionDeprecado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ar.edu.utn.frba.dds.models.georef.AdapterGeoref;

import java.util.List;

import static ar.edu.utn.frba.dds.models.localizacion.AdapterCercaniaLocalizacion.filtrarIncidentesCercanos;


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
            System.out.print(unaProvincia.provincia_id.toString() + ") ");
            System.out.print(unaProvincia.centroide.toString() + "_");
            System.out.print(unaProvincia.nombre + " - ");
        }
    }
    @Test
    public void obtenerDepartamentos() throws Exception {
        Provincia provinciaElegida = new Provincia();
        provinciaElegida.provincia_id = 54;
        provinciaElegida.nombre = "Misiones";
        List<Departamento> departamentos = adapterGeoref.obtenerListadoDepartamentos(provinciaElegida);
        System.out.println("\n\nLos departamentos de la provincia " + provinciaElegida.provincia_id.toString() + ") " + provinciaElegida.nombre + " son:");
        for (Departamento unDepartamento : departamentos) {
            System.out.print(unDepartamento.departamento_id.toString() + ") ");
            System.out.print(unDepartamento.nombre + ", de la provincia ");
            System.out.print(unDepartamento.provincia.nombre + " \n ");
        }
    }
    @Test
    public void obtenerLocalidades() throws Exception {
        Departamento departamentoElegido = new Departamento();
        departamentoElegido.departamento_id = 54063;
        departamentoElegido.nombre = "Iguazú";
        List<Localidad> localidades = adapterGeoref.obtenerListadoLocalidades(departamentoElegido);
        System.out.println("\n\nLas localidades del departamento " + departamentoElegido.departamento_id.toString() + ") " + departamentoElegido.nombre + " son:");
        for (Localidad unaLocalidad : localidades) {
            System.out.print(unaLocalidad.localidad_id.toString() + ") ");
            System.out.print(unaLocalidad.nombre + ", del departamento ");
            System.out.print(unaLocalidad.departamento.nombre + " \n");
        }
    }
    @Test
    public void obtenerLocalizacionDeLatLon() throws Exception {
        Localizacion localizacion = adapterGeoref.obtenerLocalizacionDeLatLon(-34.603722, -58.381592);
        System.out.println("\n\nLa localizacion de la lat " + -34.603722 + " y long " + -58.381592 + " es:");
        System.out.print(localizacion.toString());
    }
    @Test
    public void obtenerLocalizacion() throws Exception {
        Localizacion localizacion = adapterGeoref.obtenerLocalizacion("ciudad autonoma de buenos aires", "comuna 10", "floresta", "rivadavia 8000");
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
    public void estanCerca2Localizaciones() {
//        Ubicacion ubicacion1 = new Ubicacion(-34.60806241126338, -58.39352197717003);
//        Ubicacion ubicacion2 = new Ubicacion(-34.60846183769753, -58.39895013198136);
        Ubicacion ubicacion1 = new Ubicacion(-34.61305310845683, -58.476466730931136);
        Ubicacion ubicacion2 = new Ubicacion(-34.61717542840653, -58.47866320902155);
//        Ubicacion ubicacion1 = new Ubicacion(-34.60213357161358, -58.45502276033098);
//        Ubicacion ubicacion2 = new Ubicacion(-34.60270120372355, -58.45587401620668);
        Boolean estanCerca = AdapterCercaniaLocalizacion.estaDentroDeRadio(ubicacion1, ubicacion2, 500);
        System.out.println("\n\nestanCerca = " + estanCerca);
    }
    @Test
    public void incidentesCercanos() {
        List<Prestacion> listaDePrestaciones = RepoPrestacionDeprecado.getInstancia().getListaPrestaciones();
        List<Incidente> incidentes = listaDePrestaciones.stream().
                flatMap(prestacion -> prestacion.getIncidentes().stream()).
                toList();

        double lat = -34.60806241126338;
        double lon = -58.39352197717003;
        int radio = 500;

        filtrarIncidentesCercanos(incidentes, lat, lon, radio);

        System.out.println("\n\nincidentes.size() = " + incidentes.size());
        for (Incidente unIncidente : incidentes) {
            System.out.println("\n\nunIncidente = " + unIncidente.toString());
        }
    }
}
