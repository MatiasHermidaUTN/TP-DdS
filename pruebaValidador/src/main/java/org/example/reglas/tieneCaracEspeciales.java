package org.example.reglas;

import org.example.Resultado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class tieneCaracEspeciales implements Regla {

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

    public tieneCaracEspeciales() {
        this.caracteresEspeciales = Arrays.asList('@','$','!','%','?','&');
    }

    public void agregarCaracter(Character c) {
        this.caracteresEspeciales.add(c);
    }

    public void eliminarCaracter(Character c) {
        this.caracteresEspeciales.remove(c);
    }
}
