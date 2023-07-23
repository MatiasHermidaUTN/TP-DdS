package ar.edu.utn.frba.dds.localizacion;

import ar.edu.utn.frba.dds.incidentes.Incidente;

import java.util.List;

public class AdapterCercaniaLocalizacion {
    //hardcodeo cuanto vale 100m en deltaLat y deltaLon
    static final double deltaLat100m = -34.60381259247712 + 34.60291238880937;
    static final double deltaLon100m = -58.38373353674702 + 58.38379388666569;
    static final double Distance_Result_100m = Math.sqrt(deltaLat100m * deltaLat100m + deltaLon100m * deltaLon100m);

    public static Boolean estaDentroDeRadio(Ubicacion ubicacion1, Ubicacion ubicacion2, int radioMetros){
        double deltaLat = ubicacion1.getLat() - ubicacion2.getLat();
        double deltaLon = ubicacion1.getLon() - ubicacion2.getLon();
        double Distance_Result = Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);

        double Distance_Result_en_metros = Distance_Result * 100 / Distance_Result_100m;

        //TODO borrar prints que son para chequear y la regla de 3 de abajo:
        //System.out.println("\n\ndeltaLat = " + deltaLat);
        //System.out.println("\n\ndeltaLon = " + deltaLon);
        //System.out.println("\n\nDistance_Result = " + Distance_Result);
        //System.out.println("\n\ndeltaLat100m = " + deltaLat100m);
        //System.out.println("\n\ndeltaLon100m = " + deltaLon100m);
        //System.out.println("\n\nDistance_Result_100m = " + Distance_Result_100m);
        //System.out.println("\n\nDistance_Result_en_metros = " + Distance_Result_en_metros);
        //lat,lon para que de 100m:
        //-34.60381259247712, -58.38373353674702
        //-34.60291238880937, -58.38379388666569
        //dist 0.0009022243381 <-> 100m
        //dist 0.0018044486762 <-> 200m
        //dist 0.0045111216905 <-> 500m
        //dist 0.0090222433810 <-> 1000m

        return Distance_Result_en_metros < radioMetros;
    }

    public static List<Incidente> filtrarIncidentesCercanos(List<Incidente> listaDeIncidentes, double latitud, double longitud, int radioMetros){
        Ubicacion ubicacionActualPersona = new Ubicacion(latitud, longitud);
        listaDeIncidentes.stream().filter(incidente -> estaDentroDeRadio(ubicacionActualPersona, incidente.getEstablecimiento().getLocalizacion().getUbicacion(), radioMetros)).toList();
        return listaDeIncidentes;
    }
}
