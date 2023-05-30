package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.lectorCSV.LectorCSV;
import ar.edu.utn.frba.dds.repositorios.RepoEntidad;
import ar.edu.utn.frba.dds.repositorios.RepoOrganismo;
import org.junit.jupiter.api.Test;


public class CSVtest {

    LectorCSV lectorCSV = new LectorCSV();


    @Test
    public void testCSVMostrar() throws Exception {
        lectorCSV.cargarCSV();

        System.out.println("Entidades: ");
        RepoEntidad.getInstancia().mostrarEntidades();

        System.out.println("\nOrganismos: ");
        RepoOrganismo.getInstancia().mostrarOrganismos();
    }
}
