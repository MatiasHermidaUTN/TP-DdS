package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeMunicipios;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("ubicacion")
    Call<ListadoDeProvincias> ubicacion(@Query("lat") int lat, @Query("lon") int lon);

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos, @Query("orden") String orden);

    @GET("provincias")
    Call<ListadoDeProvincias> provincias(@Query("campos") String campos, @Query("nombre") String nombre, @Query("orden") String orden);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max, @Query("orden") String orden);

    @GET("municipios")
    Call<ListadoDeMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("nombre") String nombre, @Query("max") int max, @Query("orden") String orden);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max, @Query("orden") String orden);

    @GET("departamentos")
    Call<ListadoDeDepartamentos> departamentos(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("nombre") String nombre, @Query("max") int max, @Query("orden") String orden);

}