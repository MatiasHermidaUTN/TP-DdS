package ar.edu.utn.frba.dds.validador.reglas;

import ar.edu.utn.frba.dds.validador.Resultado;

public class TieneMinus implements Regla {

    public Resultado cumple(String usuario, String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isLowerCase(contrasenia.charAt(i))) {
                return new Resultado(true, "");
            }
        }
        return new Resultado(false, "La contrasenia debe tener al menos una minuscula. ");
    }
}
