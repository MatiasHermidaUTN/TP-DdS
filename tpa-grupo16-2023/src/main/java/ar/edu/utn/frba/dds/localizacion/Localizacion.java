package ar.edu.utn.frba.dds.localizacion;

import ar.edu.utn.frba.dds.persistencia.Persistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Localizacion extends Persistente {

    @Embedded
    private Ubicacion ubicacion;

    @Transient
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
