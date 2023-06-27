package ar.edu.utn.frba.dds.localizacion;

import lombok.Getter;

@Getter
public class Localizacion {
    private Ubicacion ubicacion;
    private Provincia provincia;
    private Departamento departamento;
    private Localidad localidad;

    public Localizacion(Provincia provincia, Departamento departamento, Localidad localidad, Ubicacion ubicacion) {
        this.provincia = provincia;
        this.departamento = departamento;
        this.localidad = localidad;
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "ubicacion=" + ubicacion.toString() +
                ", provincia='" + provincia.nombre + '\'' +
                ", departamento='" + departamento.nombre + '\'' +
                ", localidad='" + localidad.nombre + '\'' +
                '}';
    }
}
