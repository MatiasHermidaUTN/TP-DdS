package org.example;

import org.example.reglas.Regla;

import java.util.ArrayList;
import java.util.List;

public class Validador
{
    public List<Regla> reglas;

    public Resultado logear(String usuario, String contrasenia) {

        boolean valor = true;
        String mensajeError = "";

        for(Regla regla : reglas) {
            valor = valor && regla.cumple(usuario, contrasenia).isValor();
            mensajeError = mensajeError.concat(regla.cumple(usuario, contrasenia).getMensajeError());
        }

        return new Resultado(valor, mensajeError);
    }

    public void agregarRegla(Regla regla) {
        reglas.add(regla);
    }

    public void eliminimarRegla(Regla regla) {
        reglas.remove(regla);
    }

    public Validador() {
        this.reglas = new ArrayList<>();
    }
}