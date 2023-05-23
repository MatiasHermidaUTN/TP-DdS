package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;

@Getter
public class Localizacion {
    private String lugar;
    private TipoLocalizacion tipoLocalizacion;
    private Ubicacion ubicacion;

    public Localizacion(String lugar, TipoLocalizacion tipoLocalizacion, Ubicacion ubicacion) {
        this.lugar = lugar;
        this.tipoLocalizacion = tipoLocalizacion;
        this.ubicacion = ubicacion;
    }
}
