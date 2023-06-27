package ar.edu.utn.frba.dds.localizacion;

public class Provincia {
    public String id;
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
