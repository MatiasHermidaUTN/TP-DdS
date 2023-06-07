package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.services.georef.AdapterGeoref;
import ar.edu.utn.frba.dds.services.georef.GeorefServiceRetrofit;
import ar.edu.utn.frba.dds.services.georef.entities.*;
import ar.edu.utn.frba.dds.serviciosPublicos.Ubicacion;
import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Localizacion;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * El sistema deberá permitir mostrar todos los municipios de una provincia seleccionada (de Argentina)
 */
public class EjemploDeUso {

    @SneakyThrows
    public static void main(String[] args) {
        AdapterGeoref adapterGeoref = AdapterGeoref.instancia();
        List<String> nombresProvincias  = adapterGeoref.obtenerListadoProvincias();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su nombre:");

        for(String unaProvincia : nombresProvincias){
            System.out.print(unaProvincia + " - ");
        }

        Scanner entradaEscaner = new Scanner(System.in);
        System.out.print("\n\nelegir provincia (nombre exacto): ");
        String provinciaElegida = entradaEscaner.nextLine();

        if(!provinciaElegida.isEmpty()) {
            List<String> nombresMunicipios = adapterGeoref.obtenerListadoMunicipios(provinciaElegida);
            System.out.println("\n\nLos municipios de la provincia " + provinciaElegida + " son:");
            for (String unMunicipio : nombresMunicipios) {
                System.out.print(unMunicipio + " - ");
            }

            List<String> nombresDepartamentos = adapterGeoref.obtenerListadoDepartamentos(provinciaElegida);
            System.out.println("\n\nLos departamentos de la provincia " + provinciaElegida + " son:");
            for (String unDepartamento : nombresDepartamentos) {
                System.out.print(unDepartamento + " - ");
            }
        }

        System.out.print("\n\nelegir municipio: ");
        String municipioElegido = entradaEscaner.nextLine();

        System.out.print("\nelegir departamento: ");
        String departamentoElegido = entradaEscaner.nextLine();

        Ubicacion unaUbicacion = adapterGeoref.obtenerUbicacion(provinciaElegida, departamentoElegido, municipioElegido);
        Localizacion unaLocalizacion = new Localizacion(provinciaElegida, departamentoElegido, municipioElegido, unaUbicacion);

        System.out.println("\n\nunaLocalizacion = " + unaLocalizacion);
    }
}