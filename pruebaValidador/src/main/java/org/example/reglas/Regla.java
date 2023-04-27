package org.example.reglas;

import org.example.Resultado;

public interface Regla {
    public Resultado cumple(String usuario, String contrasenia);
}
