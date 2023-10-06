package ar.edu.utn.frba.dds.models.localizacion;

import javax.persistence.*;

@Entity
@Table
public class Departamento {

    @Id
    @Column(name = "departamento_id")
    public Integer id;

    @Column(name = "departamento_nombre")
    public String nombre;

    @Embedded
    public Ubicacion centroide;

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "provincia_id")
    public Provincia provincia;

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + id.toString() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                ", provincia=" + provincia.toString() +
                '}';
    }
}
