package org.example.reglas;

import org.example.Resultado;

public class noRepiteCaracteres implements Regla {

    public Resultado cumple(String usuario, String contrasenia) {
        if(contrasenia.matches(".*(.)\\1{2,}.*")) {
            return new Resultado(false, "La contrasenia no debe tener 3 o mas caracteres repetidos consecutivos. ");
        }
        else {
            return new Resultado(true, "");
        }
    }
}
