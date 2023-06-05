package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.services.georef.ServicioGeoref;
import ar.edu.utn.frba.dds.services.georef.entities.*;
import ar.edu.utn.frba.dds.serviciosPublicos.localizacion.Localizacion;
import ar.edu.utn.frba.dds.serviciosPublicos.Ubicacion;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

/**
 * El sistema deberá permitir mostrar todos los municipios de una provincia seleccionada (de Argentina)
 */
public class EjemploDeUso {

    @SneakyThrows
    public static Localizacion obtenerLocalizacionDeNombresProvMuniDepart(@NotNull String nombre_provincia, String nombre_departamento, String nombre_municipio){
        ServicioGeoref servicioGeoref = ServicioGeoref.instancia();
        Ubicacion ubicacion;

        ListadoDeProvincias listadoDeProvincias = null;
        while (listadoDeProvincias == null) {
            listadoDeProvincias = servicioGeoref.unaProvincia(nombre_provincia);
        }
        listadoDeProvincias.provincias.removeIf(provincia -> !provincia.nombre.equals(nombre_provincia));
        Provincia unaProvincia = listadoDeProvincias.provincias.get(0);
        System.out.println(unaProvincia.id + ") " + unaProvincia.nombre);
        ubicacion = new Ubicacion(unaProvincia.centroide.lat, unaProvincia.centroide.lon);

        if (nombre_municipio != null) {
            ListadoDeMunicipios listadoDeMunicipios = null;
            while (listadoDeMunicipios == null) {
                listadoDeMunicipios = servicioGeoref.unMunicipio(unaProvincia, nombre_municipio);
            }
            Municipio unMunicipio = listadoDeMunicipios.municipios.get(0);
            System.out.println(unMunicipio.id + ") " + unMunicipio.nombre);
            ubicacion.setLatitud(unMunicipio.centroide.lat);
            ubicacion.setLongitud(unMunicipio.centroide.lon);
        }

        if(nombre_departamento != null) {
            ListadoDeDepartamentos listadoDeDepartamentos = null;
            while (listadoDeDepartamentos == null) {
                listadoDeDepartamentos = servicioGeoref.unDepartamento(unaProvincia, nombre_departamento);
            }
            Departamento unDepartamento = listadoDeDepartamentos.departamentos.get(0);
            System.out.println(unDepartamento.id + ") " + unDepartamento.nombre);
            ubicacion.setLatitud(unDepartamento.centroide.lat);
            ubicacion.setLongitud(unDepartamento.centroide.lon);
        }

        return new Localizacion(nombre_provincia, nombre_departamento, nombre_municipio, ubicacion);
    }

    public static void main(String[] args) {
        String nombre_provincia = "Buenos Aires";
        String nombre_departamento = "General Las Heras";
        String nombre_municipio = "General Las Heras";

        Localizacion unaLocalizacion = obtenerLocalizacionDeNombresProvMuniDepart(nombre_provincia, nombre_departamento, nombre_municipio);

        System.out.println("unaLocalizacion = " + unaLocalizacion);

        /*ListadoDeProvincias listadoDeProvinciasArgentinas = servicioGeoref.listadoDeProvincias();
        System.out.println("Seleccione una de las siguientes provincias, ingresando su id:");

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