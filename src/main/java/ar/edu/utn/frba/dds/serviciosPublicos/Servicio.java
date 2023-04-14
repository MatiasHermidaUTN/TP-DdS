package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Servicio {
    private String nombre;
    private Estado estado;

    // donde inicializamos el estado?

    public Servicio(String nombre) {
        this.nombre = nombre;
    }
}
