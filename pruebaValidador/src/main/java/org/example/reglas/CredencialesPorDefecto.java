package org.example.reglas;

import org.example.Resultado;

import java.util.Objects;

public class CredencialesPorDefecto implements Regla {
    public Resultado cumple(String usuario, String contrasenia) {

        if(Objects.equals(usuario, contrasenia)) {
            return new Resultado(false, "La contrasenia debe ser distinta del usuario. ");
        }
        else {
            return new Resultado(true, "");
        }
    }
}
