package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class LineaTransporte {
    private String nombre;
    private TipoTransporte tipoTransporte;
    private List<Estacion> estaciones;

    public LineaTransporte(String nombre, TipoTransporte tipoTransporte, List<Estacion> estaciones) {
        this.nombre = nombre;
        this.tipoTransporte = tipoTransporte;
        this.estaciones = estaciones;
    }

    public Estacion primeraEstacion() {
        return estaciones.get(0);
    }

    public Estacion ultimaEstacion() {
        return estaciones.get(estaciones.size() - 1);
    }
}
