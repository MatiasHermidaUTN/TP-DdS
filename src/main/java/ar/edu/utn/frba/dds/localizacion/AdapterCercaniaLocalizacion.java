package ar.edu.utn.frba.dds.localizacion;

public class AdapterCercaniaLocalizacion {
    private static double margenCercania = 0.006; //aprox 500m

    public static Boolean estaCerca(Localizacion localizacion1, Localizacion localizacion2){
        double x = localizacion1.getUbicacion().getLat() - localizacion2.getUbicacion().getLat();
        double y = localizacion1.getUbicacion().getLon() - localizacion2.getUbicacion().getLon();
        double Distance_Result = Math.sqrt(x*x + y*y);

        System.out.println("\n\nDistance_Result = " + Distance_Result);

        return Distance_Result < margenCercania;
    }
}
