package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Organismo {
    private String nombre;

    public Organismo(String nombre) {
        this.nombre = nombre;
    }
}
