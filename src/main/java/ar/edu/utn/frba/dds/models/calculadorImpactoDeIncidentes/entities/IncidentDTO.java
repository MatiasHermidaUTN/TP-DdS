package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentDTO {
    private String descripcion;
    private Integer tiempoResolucion;
    private Boolean resuelto;

    @Override
    public String toString() {
        return "{" +
                "\"descripcion\":\"" + descripcion + '\"' +
                ", \"tiempoResolucion\":" + tiempoResolucion +
                ", \"resuelto\":" + resuelto +
                '}';
    }
}
