package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.comunidades.TipoPerfil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class TipoPerfilConverter {

    public static String convertirAString(TipoPerfil tipoPerfil) {
        String tipo = null;
        switch (tipoPerfil){
            case ADMIN: tipo = "ADMIN"; break;
            case NORMAL: tipo = "NORMAL"; break;
        }
        return tipo;
    }

    public static TipoPerfil convertirAObjeto(String s) {
        TipoPerfil tipo = null;
        switch (s){
            case "NORMAL": tipo = TipoPerfil.NORMAL; break;
            case "ADMIN": tipo = TipoPerfil.ADMIN; break;
        }
        return tipo;
    }
}
