package ar.edu.utn.frba.dds.services.georef.entities;

import ar.edu.utn.frba.dds.services.georef.entities.*;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioGeoref {
    private static ServicioGeoref instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private ServicioGeoref() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioGeoref instancia(){
        if(instancia== null){
            instancia = new ServicioGeoref();
        }
        return instancia;
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias("id,nombre,centroide", "nombre");
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoDeProvincias unaProvincia(String nombreProvincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefService.provincias("id,nombre,centroide", nombreProvincia, "nombre");
        Response<ListadoDeProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return responseProvinciasArgentinas.body();
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(@NotNull Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id,nombre,centroide", maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }

    public ListadoDeMunicipios unMunicipio(@NotNull Provincia provincia, String nombreMunicipio) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefService.municipios(provincia.id, "id,nombre,centroide", nombreMunicipio, maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return responseListadoDeMunicipios.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(@NotNull Provincia provincia) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id, "id,nombre,centroide", maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
        return responseListadoDeDepartamentos.body();
    }

    public ListadoDeDepartamentos unDepartamento(@NotNull Provincia provincia, String nombreDepartamento) throws IOException {
        GeorefService georefService = this.retrofit.create(GeorefService.class);
        Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefService.departamentos(provincia.id, "id,nombre,centroide", nombreDepartamento, maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
        return responseListadoDeDepartamentos.body();
    }
}