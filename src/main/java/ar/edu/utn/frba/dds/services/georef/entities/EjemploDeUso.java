package ar.edu.utn.frba.dds.services.georef.entities;

import ar.edu.utn.frba.dds.serviciosPublicos.Localizacion;
import ar.edu.utn.frba.dds.serviciosPublicos.TipoLocalizacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Ubicacion;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

/**
 * El sistema deberá permitir mostrar todas los municipios de una provincia seleccionada (de Argentina)
 */
public class EjemploDeUso {

    @SneakyThrows
    public Localizacion obtenerLocalizacion(String provincia, String departamento, String municipio){
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();

        ListadoDeProvincias listadoDeProvincias = servicioGeoref.unaProvincia(provincia);
        Provincia provincia0 = listadoDeProvincias.provincias.get(0);
        System.out.println(provincia0.id + ") " + provincia0.nombre);

        ListadoDeDepartamentos listadoDeDepartamentos = servicioGeoref.unDepartamento(provincia0, departamento);
        Departamento departamento0 = listadoDeDepartamentos.departamentos.get(0);
        System.out.println(departamento0.id + ") " + departamento0.nombre);

        ListadoDeMunicipios listadoDeMunicipios = servicioGeoref.unMunicipio(provincia0, municipio);
        Municipio municipio0 = listadoDeMunicipios.municipios.get(0);
        System.out.println(municipio0.id + ") " + municipio0.nombre);

        return new Localizacion(provincia0.nombre, TipoLocalizacion.PROVINCIA, new Ubicacion(provincia0.getLatitud(), provincia0.getLongitud()));
    }

    public void main(String[] args) throws IOException {
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

        Localizacion pepe = obtenerLocalizacion("Buenos Aires", "General Las Heras", "General Las Heras");

        /*ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();

        for(Provincia unaProvincia:listadoDeProvinciasArgentinas.provincias){
            System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        }

        System.out.println("elegir provincia:");
        Scanner entradaEscaner = new Scanner(System.in);
        int idProvinciaElegida = Integer.parseInt(entradaEscaner.nextLine());
        Optional<Provincia> posibleProvincia = listadoDeProvinciasArgentinas.provinciaDeId(idProvinciaElegida);

        if(posibleProvincia.isPresent()) {
            Provincia provinciaSeleccionada = posibleProvincia.get();
//            System.out.println("elegir: municipios / departamentos");
//            Scanner entradaEscaner2 = new Scanner(System.in);
            String aLeer = "municipios"; //entradaEscaner2.nextLine();
            String aLeer2 = "departamentos";

            if (aLeer.equals("municipios")) {
                ListadoDeMunicipios municipiosDeLaProvincia = servicioGeoref.listadoDeMunicipiosDeProvincia(provinciaSeleccionada);
                System.out.println("Los municipios de la provincia " + provinciaSeleccionada.nombre + " son:");
                for (Municipio unMunicipio : municipiosDeLaProvincia.municipios) {
                    System.out.println(unMunicipio.id + ") " + unMunicipio.nombre);
                }
            }
            if (aLeer2.equals("departamentos")) {
                ListadoDeDepartamentos departamentosDeLaProvincia = servicioGeoref.listadoDeDepartamentosDeProvincia(provinciaSeleccionada);
                System.out.println("Los departamentos de la provincia " + provinciaSeleccionada.nombre + " son:");
                for (Departamento unDepartamento : departamentosDeLaProvincia.departamentos) {
                    System.out.println(unDepartamento.id + ") " + unDepartamento.nombre);
                }
            }
        } else {
            System.out.println("No existe la provincia seleccionada");
        }*/
    }
}