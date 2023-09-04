package ar.edu.utn.frba.dds.services.georef.entities;

import ar.edu.utn.frba.dds.localizacion.Localidad;

import java.util.List;

public class ListadoDeLocalidades {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Localidad> localidades;

    public Localidad localidadDeNombre(String nombre){
        return this.localidades.stream()
                .filter(l -> l.nombre.toLowerCase().equals(nombre.toLowerCase()))
                .findFirst().get();
    }

    private class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincia;
        public String orden;
    }
}
