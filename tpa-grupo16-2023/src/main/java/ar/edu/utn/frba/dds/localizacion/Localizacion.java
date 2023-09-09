package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Localizacion {

    @Id
    @GeneratedValue
    private Integer localizacion_id;

    @Embedded
    private Ubicacion ubicacion;

    // esto hay que reveerlo, si guardamos todos los datos de la localizacion o solo el nombre de la localidad
    // lo primero implicaria persistir la localidad, el departamento y la provincia en cuestion
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
