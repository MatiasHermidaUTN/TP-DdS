package ar.edu.utn.frba.dds.lectorCSV;

import ar.edu.utn.frba.dds.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.serviciosPublicos.Establecimiento;
import ar.edu.utn.frba.dds.serviciosPublicos.Organismo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LectorCSV {

    // Variables temporales
    private String nombre;
    // private Map<String,String> atributosVariables;
    // private List<Establecimiento> establecimientos;

    String rutaArchivo = "src\\main\\java\\ar\\edu\\utn\\frba\\dds\\lectorCSV\\entidades_organismos.csv";

    public void cargarCSV() throws Exception {

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
                    new Entidad(nombre);
                } else if (Objects.equals(celdas[0], "organismo")) {
                    new Organismo(nombre);
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
    }

    public LectorCSV() {}
}


