package ar.edu.utn.frba.dds.lectorCSV;

import com.opencsv.CSVReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LectorCSV {

    public static List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }

    public static List<String[]> leerTodo() throws Exception {
        Path path = Paths.get(
                ClassLoader.getSystemResource("addresses.csv").toURI()
        );
        return LectorCSV.readAllLines(path);
    }

    public void cargarCSV() throws Exception {
        List<String[]> leido;
        leido = this.leerTodo();
        String[] a = leido.get(0);
        for (int i = 0; i <a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}
