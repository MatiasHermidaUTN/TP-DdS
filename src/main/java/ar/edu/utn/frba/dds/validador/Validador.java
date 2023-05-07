package ar.edu.utn.frba.dds.validador;

import ar.edu.utn.frba.dds.validador.reglas.Regla;

import java.util.ArrayList;
import java.util.Collections;
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

    public void agregarRegla(Regla ... reglas) {

        Collections.addAll(this.reglas, reglas);
    }

    public void eliminimarRegla(Regla regla) {
        reglas.remove(regla);
    }

    public Validador() {
        this.reglas = new ArrayList<>();
    }
}