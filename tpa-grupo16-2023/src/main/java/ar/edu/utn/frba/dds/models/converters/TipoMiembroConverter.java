package ar.edu.utn.frba.dds.models.converters;

import ar.edu.utn.frba.dds.models.comunidades.TipoMiembro;

public class TipoMiembroConverter {
    public static String convertirAString(TipoMiembro tipoMiembro) {
        String tipo = null;
        switch (tipoMiembro){
            case AFECTADO: tipo = "AFECTADO";
            case OBSERVADOR: tipo = "OBSERVADOR";
        }
        return tipo;
    }

    public static TipoMiembro convertirAObjeto(String s) {
        TipoMiembro tipo = null;
        switch (s){
            case "AFECTADO": tipo = TipoMiembro.AFECTADO;
            case "OBSERVADOR": tipo = TipoMiembro.OBSERVADOR;
        }
        return tipo;
    }
}
