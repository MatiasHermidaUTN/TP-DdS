package ar.edu.utn.frba.dds.validador.reglas;

import ar.edu.utn.frba.dds.validador.Resultado;

public class TieneNumero implements Regla {

    public Resultado cumple(String usuario, String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isDigit(contrasenia.charAt(i))) {
                return new Resultado(true, "");
            }
        }
        return new Resultado(false, "La contrasenia debe tener al menos una mayuscula. ");
    }
}
