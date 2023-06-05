package ar.edu.utn.frba.dds.serviciosPublicos.localizacion;

import ar.edu.utn.frba.dds.serviciosPublicos.Ubicacion;
import lombok.Getter;

@Getter
public class Localizacion {
    private Ubicacion ubicacion;
    private String provincia; //deberia ser clase Provincia?
    private String departamento; //deberia ser clase Departamento?
    private String municipio; //deberia ser clase Municipio?

    public Localizacion(String provincia, String departamento, String municipio, Ubicacion ubicacion) {
        this.provincia = provincia;
        this.departamento = departamento;
        this.municipio = municipio;
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "ubicacion=" + ubicacion.toString() +
                ", provincia='" + provincia + '\'' +
                ", departamento='" + departamento + '\'' +
                ", municipio='" + municipio + '\'' +
                '}';
    }
}
