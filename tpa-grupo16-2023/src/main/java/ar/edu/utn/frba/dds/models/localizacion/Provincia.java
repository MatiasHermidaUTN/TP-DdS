package ar.edu.utn.frba.dds.models.localizacion;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
public class Provincia {

    @Id
    @Column(name = "provincia_id")
    public Integer id;

    @Getter
    @Column(name = "provincia_nombre")
    public String nombre;

    @Embedded
    public Ubicacion centroide;

    @Override
    public String toString() {
        return "Provincia{" +
                "id='" + id.toString() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                '}';
    }
}
