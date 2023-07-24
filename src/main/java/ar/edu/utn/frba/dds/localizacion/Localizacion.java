package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;

@Getter
public class Localizacion {
    private Ubicacion ubicacion;
    private Localidad localidad;

    public Localizacion(Localidad localidad, Ubicacion ubicacion) {
        this.localidad = localidad;
        this.ubicacion = ubicacion;
    }
    public Localizacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Localizacion() {
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "ubicacion=" + ubicacion.toString() +
                ", localidad='" + localidad.toString() + '\'' +
                '}';
    }

    public void setLatitud(Double latitud) {
        this.ubicacion.setLat(latitud);
    }

    public void setLongitud(Double longitud) {
        this.ubicacion.setLon(longitud);
    }
}
