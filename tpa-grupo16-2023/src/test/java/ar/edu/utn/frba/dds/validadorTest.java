package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.validador.Validador;
import ar.edu.utn.frba.dds.validador.reglas.*;
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
        validador.agregarRegla(new EsFuerte(),
                new CredencialesPorDefecto(),
                new Longitud(8,64),
                new TieneMayus(),
                new TieneMinus(),
                new TieneNumero(),
                new TieneCaracEspeciales(),
                new NoRepiteCaracteres()
        );

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
