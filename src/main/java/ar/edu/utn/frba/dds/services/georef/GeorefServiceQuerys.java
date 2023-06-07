package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefServiceQuerys {
    /*@GET("ubicacion")
    Call<ListadoDeProvincias> ubicacion(@Query("lat") Float lat, @Query("lon") Float lon);*/

    @GET("provincias?orden=nombre")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

    @GET("municipios?max=200&orden=nombre")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("departamentos?max=200&orden=nombre")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos);
}