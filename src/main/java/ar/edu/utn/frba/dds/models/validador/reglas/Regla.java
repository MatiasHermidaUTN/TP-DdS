package ar.edu.utn.frba.dds.models.validador.reglas;

import ar.edu.utn.frba.dds.models.validador.Resultado;

public interface Regla {
    public Resultado cumple(String usuario, String contrasenia);
}
