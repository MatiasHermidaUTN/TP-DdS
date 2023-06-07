package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Ubicacion;
import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Localizacion;
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
        List<String> nombresProvincias  = adapterGeoref.obtenerListadoProvincias();
        for(String unaProvincia : nombresProvincias){
            System.out.print(unaProvincia + " - ");
        }
    }
    @Test
    public void obtenerMunicipios() throws Exception {
        String provinciaElegida = "misiones";
        List<String> nombresMunicipios = adapterGeoref.obtenerListadoMunicipios(provinciaElegida);
        System.out.println("\n\nLos municipios de la provincia " + provinciaElegida + " son:");
        for (String unMunicipio : nombresMunicipios) {
            System.out.print(unMunicipio + " - ");
        }
    }
    @Test
    public void obtenerDepartamentos() throws Exception {
        String provinciaElegida = "misiones";
        List<String> nombresDepartamentos = adapterGeoref.obtenerListadoDepartamentos(provinciaElegida);
        System.out.println("\n\nLos departamentos de la provincia " + provinciaElegida + " son:");
        for (String unDepartamento : nombresDepartamentos) {
            System.out.print(unDepartamento + " - ");
        }
    }
    @Test
    public void obtenerUbicacion() throws Exception {
        String provinciaElegida = "misiones";
        String departamentoElegido = "Iguazú";
        String municipioElegido = "Iguazú";
        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(provinciaElegida, departamentoElegido, municipioElegido);
        Localizacion unaLocalizacion = new Localizacion(provinciaElegida, departamentoElegido, municipioElegido, unaUbicacion);

        System.out.println("\n\nunaLocalizacion = " + unaLocalizacion);
    }
}
