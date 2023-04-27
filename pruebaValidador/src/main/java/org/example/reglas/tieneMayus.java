package org.example.reglas;

import org.example.Resultado;

public class tieneMayus implements Regla {

    public Resultado cumple(String usuario, String contrasenia) {
        for (int i = 0; i < contrasenia.length(); i++) {
            if (Character.isUpperCase(contrasenia.charAt(i))) {
                return new Resultado(true, "");
            }
        }
        return new Resultado(false, "La contrasenia debe tener al menos una mayuscula. ");
    }
}
