package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntityDTO {
    private long id;
    private String nombre;
    private Integer miembrosAfectados;
    private List<IncidentDTO> incidentes;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"nombre\":\"" + nombre + '\"' +
                ", \"miembrosAfectados\":" + miembrosAfectados +
                ", \"incidentes\":" + incidentes +
                '}';
    }
}
