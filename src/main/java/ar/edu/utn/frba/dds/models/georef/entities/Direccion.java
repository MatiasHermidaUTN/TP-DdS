package ar.edu.utn.frba.dds.models.georef.entities;

import ar.edu.utn.frba.dds.models.localizacion.Ubicacion;

public class Direccion {
    public Ubicacion ubicacion;
    public String nomenclatura;

    @Override
    public String toString() {
        return "Direccion{" +
                ", ubicacion=" + ubicacion.toString() +
                ", nomenclatura='" + nomenclatura + '\'' +
                '}';
    }
}
