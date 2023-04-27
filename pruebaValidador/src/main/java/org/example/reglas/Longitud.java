package org.example.reglas;

import org.example.Resultado;

public class Longitud implements Regla{

    private int min;
    private int max;

    public Resultado cumple(String usuario, String contrasenia) {
        int tamanio = contrasenia.length();

        if( tamanio < min || tamanio > max) {
            return new Resultado(false, "La contrasenia debe tener entre 8 y 64 caracteres. ");
        }
        else{
            return new Resultado(true, "");
        }
    }

    public Longitud(int min, int max) {
        this.min = min;
        this.max = max;
    }
}
