package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.comunidades.TipoPerfil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public class TipoPerfilConverter {

    public static String convertirAString(TipoPerfil tipoPerfil) {
        String tipo = null;
        switch (tipoPerfil){
            case ADMIN: tipo = "ADMIN";
            case NORMAL: tipo = "NORMAL";
        }
        return tipo;
    }

    public static TipoPerfil convertirAObjeto(String s) {
        TipoPerfil tipo = null;
        switch (s){
            case "NORMAL": tipo = TipoPerfil.NORMAL;
            case "ADMIN": tipo = TipoPerfil.ADMIN;
        }
        return tipo;
    }
}
