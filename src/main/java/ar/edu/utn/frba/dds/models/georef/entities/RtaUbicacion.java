package ar.edu.utn.frba.dds.models.georef.entities;

import ar.edu.utn.frba.dds.models.localizacion.Departamento;
import ar.edu.utn.frba.dds.models.localizacion.Provincia;

import java.util.List;

public class RtaUbicacion {
    public Parametro parametros;
    public DptoYProv ubicacion;

    public class DptoYProv {
        public Provincia provincia;
        public Departamento departamento;
    }

    private class Parametro {
        public List<String> campos;
        public double lat;
        public double lon;
    }
}
