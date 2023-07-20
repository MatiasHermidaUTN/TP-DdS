package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;

public class Provincia {
    public String id;
    @Getter
    public String nombre;
    public Ubicacion centroide;

    @Override
    public String toString() {
        return "Provincia{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                '}';
    }
}
