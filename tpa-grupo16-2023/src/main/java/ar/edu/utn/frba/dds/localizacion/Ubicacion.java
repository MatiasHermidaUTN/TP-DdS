package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Ubicacion {
    private double lat;
    private double lon;

    public Ubicacion(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Ubicacion() {

    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
