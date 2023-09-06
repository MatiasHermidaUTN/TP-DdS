package ar.edu.utn.frba.dds.validador.reglas;

import ar.edu.utn.frba.dds.validador.Resultado;

public interface Regla {
    public Resultado cumple(String usuario, String contrasenia);
}
