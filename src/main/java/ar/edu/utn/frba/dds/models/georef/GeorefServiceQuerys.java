package ar.edu.utn.frba.dds.models.georef;

import ar.edu.utn.frba.dds.models.georef.entities.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefServiceQuerys {
    @GET("ubicacion")
    Call<RtaUbicacion> ubicacion(@Query("lat") double lat, @Query("lon") double lon);

    @GET("provincias?orden=nombre")
    Call<ListadoDeProvincias> provincias();

    @GET("provincias?orden=nombre")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos);

    @GET("departamentos?max=200&orden=nombre")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") String idProvincia);

    @GET("departamentos?max=200&orden=nombre")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") String idProvincia, @Query("campos") String campos);

    @GET("localidades?max=200&orden=nombre")
    Call<ListadoDeLocalidades> localidades(@Query("departamento") String idDepartamento);

    @GET("localidades?max=5&orden=nombre")
    Call<ListadoDeLocalidades> localidades(@Query("departamento") String idDepartamento, @Query("campos") String campos);

    @GET("direcciones")
    Call<ListadoDeDirecciones> direcciones(@Query("direccion") String direccion, @Query("localidad") String nombreLocalidad);
}