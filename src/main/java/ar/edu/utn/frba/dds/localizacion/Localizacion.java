package ar.edu.utn.frba.dds.serviciosPublicos.localizacion;

import lombok.Getter;

@Getter
public class Localizacion {
    private Ubicacion ubicacion;
    private Provincia provincia; //deberia ser clase Provincia?
    private Departamento departamento; //deberia ser clase Departamento?
    private Localidad localidad; //deberia ser clase Municipio?

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
                ", provincia='" + provincia + '\'' +
                ", departamento='" + departamento + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
