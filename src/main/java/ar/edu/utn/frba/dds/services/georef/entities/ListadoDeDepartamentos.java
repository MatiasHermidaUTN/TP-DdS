package ar.edu.utn.frba.dds.services.georef.entities;

import java.util.List;
import java.util.Optional;

public class ListadoDeDepartamentos {
    public int cantidad;
    public int total;
    public int inicio;
    public Parametro parametros;
    public List<Departamento> departamentos;

    public Departamento departamentoDeNombre(String nombre){
        return this.departamentos.stream()
                .filter(d -> d.nombre.toLowerCase().equals(nombre.toLowerCase()))
                .findFirst().get();
    }

    private class Parametro {
        public List<String> campos;
        public int max;
        public List<String> provincia;
        public String orden;
    }
}
