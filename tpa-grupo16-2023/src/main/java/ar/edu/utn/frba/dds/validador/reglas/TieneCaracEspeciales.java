package ar.edu.utn.frba.dds.validador.reglas;

import ar.edu.utn.frba.dds.validador.Resultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TieneCaracEspeciales implements Regla {

    private List<Character> caracteresEspeciales = new ArrayList<>();

    public Resultado cumple(String usuario, String contrasenia) {


        // regex = .*[@$!%?&].*
        String regex = ".*[" + String.valueOf(caracteresEspeciales) + "].*";
        if(contrasenia.matches(regex)) {
            return new Resultado(true, "");
        }
        else {
            return new Resultado(false, "La contrasenia debe tener al menos un caracter especial. ");
        }

    }

    public TieneCaracEspeciales() {
        this.caracteresEspeciales = Arrays.asList('@','$','!','%','?','&');
    }

    public void agregarCaracter(Character c) {
        this.caracteresEspeciales.add(c);
    }

    public void eliminarCaracter(Character c) {
        this.caracteresEspeciales.remove(c);
    }
}
