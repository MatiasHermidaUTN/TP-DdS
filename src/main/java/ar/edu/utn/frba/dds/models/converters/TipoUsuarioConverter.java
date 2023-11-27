package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.comunidades.TipoMiembro;
import ar.edu.utn.frba.dds.models.comunidades.TipoUsuario;

public class TipoUsuarioConverter {

    public static String convertirAString(TipoUsuario tipoUsuario) {
        String tipo = null;
        switch (tipoUsuario){
            case NORMAL: tipo = "NORMAL"; break;
            case ENTIDAD_PRESTADORA: tipo = "ENTIDAD_PRESTADORA"; break;
            case ORGANISMO_CONTROL: tipo = "ORGANISMO_CONTROL"; break;
        }
        return tipo;
    }

    public static TipoUsuario convertirAObjeto(String s) {
        TipoUsuario tipo = null;
        switch (s){
            case "NORMAL": tipo = TipoUsuario.NORMAL; break;
            case "ENTIDAD_PRESTADORA": tipo = TipoUsuario.ENTIDAD_PRESTADORA; break;
            case "ORGANISMO_CONTROL": tipo = TipoUsuario.ORGANISMO_CONTROL; break;
        }
        return tipo;
    }
}
