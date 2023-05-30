package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;

@Getter
public class Localizacion {
    private String lugar;
    private TipoLocalizacion tipoLocalizacion;
    private Ubicacion ubicacion;
    /*
    private String provincia; //deberia ser clase Provincia
    private String departamento; //deberia ser clase Departamento
    private String municipio; //deberia ser clase Municipio
     */

    public Localizacion(String lugar, TipoLocalizacion tipoLocalizacion, Ubicacion ubicacion) {
        this.lugar = lugar;
        this.tipoLocalizacion = tipoLocalizacion;
        this.ubicacion = ubicacion;
    }
    /*
    public Localizacion(Provincia provincia, Departamento departamento, Municipios municipios, Ubicacion ubicacion) {
        this.provincia = provincia;
        this.departamento = departamento;
        this.municipio = municipio;
        this.ubicacion = ubicacion;
    }
     */
}
