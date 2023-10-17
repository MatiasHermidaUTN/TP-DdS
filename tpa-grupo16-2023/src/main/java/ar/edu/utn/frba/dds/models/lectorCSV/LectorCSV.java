package ar.edu.utn.frba.dds.models.lectorCSV;

import ar.edu.utn.frba.dds.controllers.EstablecimientoController;
import ar.edu.utn.frba.dds.models.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.models.repositorios.RepoOrganismoDeControl;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LectorCSV {

    private String nombre;
    private String establecimientos;
    private String entidadesControladas;
    private DatosCSV datosCSV;
    private RepoEntidad repoEntidad = new RepoEntidad();
    private RepoOrganismoDeControl repoOrganismoDeControl = new RepoOrganismoDeControl();
    private EstablecimientoController establecimientoController = new EstablecimientoController();


    String rutaArchivo = "src\\main\\properties\\entidades_organismos.csv";

    public DatosCSV leerCSV() throws Exception {

        try {
            // Abre el archivo de texto para leerlo
            FileReader archivo = new FileReader(rutaArchivo);

            // Crea un bufferedReader para mejorar el rendimiento de la lectura
            BufferedReader buffer = new BufferedReader(archivo);

            String linea;
            linea = buffer.readLine();

            // Lee cada línea del archivo
            while (linea != null) {

                String[] celdas = linea.split(",");

                // mapea todos los atributos a las variables temporales
                nombre = celdas[1];

                // instancia la clase correspondiente
                if(Objects.equals(celdas[0], "entidad")){
                    datosCSV.agregarEntidad(new Entidad(nombre));
                } else if (Objects.equals(celdas[0], "organismo")) {
                    datosCSV.agregarOrganismo(new OrganismoDeControl(nombre));
                }
                else
                    System.out.print("No corresponde el tipo. ");

                linea = buffer.readLine();
            }

            // Cierra el archivo y el buffer
            buffer.close();
            archivo.close();

        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return datosCSV;
    }

    public DatosCSV leerCSV(String path) throws Exception {

        try {
            FileReader archivo;
            // Abre el archivo de texto para leerlo

            if(path.isEmpty()){
                archivo = new FileReader(rutaArchivo);
            } else {
                archivo = new FileReader(path);
            }


            // Crea un bufferedReader para mejorar el rendimiento de la lectura
            BufferedReader buffer = new BufferedReader(archivo);

            String linea;
            linea = buffer.readLine();

            // Lee cada línea del archivo
            while (linea != null) {

                String[] celdas = linea.split(",");

                // mapea todos los atributos a las variables temporales
                nombre = celdas[1];
                establecimientos = celdas[3];
                String[] arrayEstablecimientos = establecimientos.split(";");
                entidadesControladas = celdas[5];


                // instancia la clase correspondiente
                if(Objects.equals(celdas[0], "entidad")){
                    repoEntidad.guardar(new Entidad(nombre));
//                    datosCSV.agregarEntidad(new Entidad(nombre));
                    if (!Objects.equals(arrayEstablecimientos[0], "NULL")){
                        arrayEstablecimientos[0] = arrayEstablecimientos[0].replace("[", "");
                        arrayEstablecimientos[arrayEstablecimientos.length-1] = arrayEstablecimientos[arrayEstablecimientos.length-1].replace("]", "");
                        for (Integer i = 0; arrayEstablecimientos.length > i; i+=2){
                            String localidad = arrayEstablecimientos[i+1];
                            System.out.println(localidad);
                            establecimientoController.crearEstablecimento(arrayEstablecimientos[i], repoEntidad.buscarPorNombre(nombre).getId(), localidad);
                        }
                    }
                } else if (Objects.equals(celdas[0], "organismo")) {
                    repoOrganismoDeControl.guardar(new OrganismoDeControl(nombre));
//                    datosCSV.agregarOrganismo(new OrganismoDeControl(nombre));
                    System.out.println(entidadesControladas);
                    OrganismoDeControl organismo = repoOrganismoDeControl.buscarPorNombre(nombre);
                    if(!Objects.equals(entidadesControladas, "NULL")) {
                        organismo.agregarEntidad(repoEntidad.buscarPorNombre(entidadesControladas));
                        repoOrganismoDeControl.modificar(organismo);
                    }
                }
                else
                    System.out.print("No corresponde el tipo. ");

                linea = buffer.readLine();
            }

            // Cierra el archivo y el buffer
            buffer.close();
            archivo.close();

        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return datosCSV;
    }

    public LectorCSV() {
        datosCSV = new DatosCSV();
    }
}


