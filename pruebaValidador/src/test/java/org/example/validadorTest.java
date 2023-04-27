package org.example;

import org.example.reglas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class validadorTest {

    Validador validador;

    String usuario;
    String contrasenia;

    @BeforeEach
    public void init(){
        this.usuario = "tumama87";
        this.contrasenia = "Absakdjalañ%sldt3tありがとうございました。β";

        this.validador = new Validador();
        validador.agregarRegla(new EsFuerte());
        validador.agregarRegla(new CredencialesPorDefecto());
        validador.agregarRegla(new Longitud(8,64));
        validador.agregarRegla(new tieneMayus());
        validador.agregarRegla(new tieneMinus());
        validador.agregarRegla(new tieneNumero());
        validador.agregarRegla(new tieneCaracEspeciales());
        validador.agregarRegla(new noRepiteCaracteres());

    }


    @Test
    public void tiene_una_mayus() {
        Assertions.assertTrue(true);
    }

    @Test
    public void es_fuerte(){
        Assertions.assertTrue(new EsFuerte().cumple(usuario, contrasenia).isValor());
    }

    @Test
    public void validarRegistroUsuario() {

        boolean valor = validador.logear(usuario, contrasenia).isValor();
        String mensaje = validador.logear(usuario, contrasenia).getMensajeError();
        System.out.println(valor + " - " + mensaje);
        Assertions.assertTrue(valor);
    }
}
