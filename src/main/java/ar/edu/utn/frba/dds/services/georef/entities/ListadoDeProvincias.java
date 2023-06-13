package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;
import java.util.Optional;

public class ListadoDeProvincias {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Provincia> provincias;

    public Optional<Provincia> provinciaDeId(int id){
        return this.provincias.stream()
                .filter(p -> p.id == id)
                .findFirst();
    }

    public Provincia provinciaDeNombre(String nombre){
        return this.provincias.stream()
                .filter(p -> p.nombre.toLowerCase().equals(nombre.toLowerCase()))
                .findFirst().get();
    }

    private class Parametro {
        public List<String> campos;
        public int max;
        public String orden;
    }
}