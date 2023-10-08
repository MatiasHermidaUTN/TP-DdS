package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities;

import lombok.Getter;

@Getter
public class EntidadesConNivelDeImpacto {
    public Double nuevoValor;
    public long id;
    public String nombre;

    @Override
    public String toString() {
        return "EntidadesConNivelDeImpacto{" +
                "\"nuevoValor\":" + nuevoValor +
                ", \"id\":" + id +
                ", \"nombre\":\"" + nombre + '\"' +
                '}';
    }
}
