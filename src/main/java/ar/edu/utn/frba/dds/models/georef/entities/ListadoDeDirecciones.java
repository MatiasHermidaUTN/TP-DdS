package ar.edu.utn.frba.dds.models.georef.entities;

import java.util.List;

public class ListadoDeDirecciones {
    public String cantidad;
    public String total;
    public String inicio;
    public Parametro parametros;
    public List<Direccion> direcciones;

    private class Parametro {
        public List<String> campos;
        public Direccion direccion;
        public String localidad;
    }
}
