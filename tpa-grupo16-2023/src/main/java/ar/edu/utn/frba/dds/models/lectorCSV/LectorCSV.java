package ar.edu.utn.frba.dds.models.lectorCSV;

import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.OrganismoDeControl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LectorCSV {

    private String nombre;
    private DatosCSV datosCSV;


    String rutaArchivo = "src\\main\\properties\\entidades_organismos.csv";

    public DatosCSV leerCSV() throws Exception {

        try {
            // Abre el archivo de texto para leerlo

            FileReader archivo = new FileReader(rutaArchivo);

            // Crea un bufferedReader para mejorar el rendimiento de la lectura
            BufferedReader buffer = new BufferedReader(archivo);

            String linea = buffer.readLine();
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

    public LectorCSV() {
        datosCSV = new DatosCSV();
    }
}


