package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.lectorCSV.DatosCSV;
import ar.edu.utn.frba.dds.models.lectorCSV.LectorCSV;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

@Ignore
public class CSVtest {

    LectorCSV lectorCSV = new LectorCSV();
    DatosCSV datosCSV = new DatosCSV();

    @Test
    public void testCSVMostrar() throws Exception {
        datosCSV = lectorCSV.leerCSV();

        System.out.println("Entidades: ");
        datosCSV.mostrarEntidades();

        System.out.println("\nOrganismos: ");
        datosCSV.mostrarOrganismos();
    }
}
