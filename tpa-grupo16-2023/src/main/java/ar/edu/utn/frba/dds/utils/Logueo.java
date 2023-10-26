package ar.edu.utn.frba.dds.utils;

import io.javalin.http.Context;

public class Logueo {

    public static Boolean comprobarLogueo(Context context) {
        if(context.cookie("usuario_id") == null) {
            String hayQueLoguearse = "<script> window.alert(\"Por favor, logueese primero.\");"
                    + "setTimeout(function() { window.location.href = '/login'; }, 0); </script>";
            context.html(hayQueLoguearse);
            return false;
        } else {
            return true;
        }
    }
}
