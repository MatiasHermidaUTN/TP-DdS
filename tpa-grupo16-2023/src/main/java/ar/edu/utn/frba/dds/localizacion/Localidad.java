package ar.edu.utn.frba.dds.localizacion;

import javax.persistence.*;

@Entity
@Table
public class Localidad {

    @Id
    @GeneratedValue
    public Integer localidad_id;

    @Column(name = "localidad_nombre")
    public String nombre;

    @Embedded
    public Ubicacion centroide;

    @ManyToOne
    @JoinColumn(name = "departamento_id", referencedColumnName = "departamento_id")
    public Departamento departamento;

    @Override
    public String toString() {
        return "Localidad{" +
                "id='" + localidad_id.toString() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                ", departamento=" + departamento.toString() +
                '}';
    }
}
