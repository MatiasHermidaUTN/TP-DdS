package ar.edu.utn.frba.dds.localizacion;

public class Departamento {
    public String id;
    public String nombre;
    public Ubicacion centroide;
    public Provincia provincia;

    @Override
    public String toString() {
        return "Departamento{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                ", provincia=" + provincia.toString() +
                '}';
    }
}
