package ar.edu.utn.frba.dds.models.localizacion;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Localidad {

    @Id
    @Column(name = "localidad_id")
    public Long id;

    @Column(name = "localidad_nombre")
    public String nombre;

    @Embedded
    public Ubicacion centroide;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departamento_id", referencedColumnName = "departamento_id")
    public Departamento departamento;

    @Override
    public String toString() {
        return "Localidad{" +
                "id='" + id.toString() + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                ", departamento=" + departamento.toString() +
                '}';
    }
}
