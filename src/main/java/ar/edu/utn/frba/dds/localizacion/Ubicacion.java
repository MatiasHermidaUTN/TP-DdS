package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ubicacion {
    private double latitud;
    private double longitud;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
