package ar.edu.utn.frba.dds.localizacion;

public class Localidad {
    public String id;
    public String nombre;
    public Ubicacion centroide;
    public Departamento departamento;

    @Override
    public String toString() {
        return "Localidad{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", centroide=" + centroide.toString() +
                ", departamento=" + departamento.toString() +
                '}';
    }
}
