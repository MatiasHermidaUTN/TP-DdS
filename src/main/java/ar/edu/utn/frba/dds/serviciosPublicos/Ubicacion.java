package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ubicacion {
    private Float latitud;
    private Float longitud;

    public Ubicacion(Float latitud, Float longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
