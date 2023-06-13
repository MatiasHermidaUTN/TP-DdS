package ar.edu.utn.frba.dds.validador.reglas;

import ar.edu.utn.frba.dds.validador.Resultado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EsFuerte implements Regla {

    private String rutaArchivo = "src\\main\\properties\\top_10000_peores_contrasenias.txt";

    public Resultado cumple(String usuario, String contrasenia) {
        try {
            // Abre el archivo de texto para leerlo

            FileReader archivo = new FileReader(rutaArchivo);

            // Crea un bufferedReader para mejorar el rendimiento de la lectura
            BufferedReader buffer = new BufferedReader(archivo);

            String linea = buffer.readLine();

            // Lee cada línea del archivo
            while (linea != null) {
                if (linea.contains(contrasenia)) {
                    // Cierra el archivo y el buffer
                    buffer.close();
                    archivo.close();
                    return new Resultado(false, "La contrasenia es debil. ");
                }
                linea = buffer.readLine();
            }

            // Cierra el archivo y el buffer
            buffer.close();
            archivo.close();

        }
        catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return new Resultado(true, "");
    }

    public EsFuerte() {}
}
