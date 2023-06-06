package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.*;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class GeorefServiceRetrofit {
    private static GeorefServiceRetrofit instancia = null;
    private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private GeorefServiceRetrofit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GeorefServiceRetrofit instancia(){
        if(instancia == null){
            instancia = new GeorefServiceRetrofit();
        }
        return instancia;
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
        Call<ListadoDeProvincias> requestProvinciasArgentinas = georefServiceQuerys.provincias("id,nombre,centroide", "nombre");
        Response<ListadoDeProvincias> responseListadoDeProvincias = requestProvinciasArgentinas.execute();
        if (!responseListadoDeProvincias.isSuccessful()){
            throw new IOException("GeoRef: " + responseListadoDeProvincias.message());
        }
        return responseListadoDeProvincias.body();
    }

    private Provincia obtenerProvinciaPorNombre(String nombreProvincia) throws IOException {
        return this.listadoDeProvincias().provinciaDeNombre(nombreProvincia);
    }

    public ListadoDeMunicipios listadoDeMunicipiosDeProvincia(@NotNull String nombreProvincia) throws IOException {
        Provincia provincia = this.obtenerProvinciaPorNombre(nombreProvincia);
        GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
        Call<ListadoDeMunicipios> requestListadoDeMunicipios = georefServiceQuerys.municipios(provincia.id, "id,nombre,centroide", maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        if (!responseListadoDeMunicipios.isSuccessful()){
            throw new IOException("GeoRef: " + responseListadoDeMunicipios.message());
        }
        return responseListadoDeMunicipios.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(@NotNull String nombreProvincia) throws IOException {
        Provincia provincia = this.obtenerProvinciaPorNombre(nombreProvincia);
        GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
        Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefServiceQuerys.departamentos(provincia.id, "id,nombre,centroide", maximaCantidadRegistrosDefault, "nombre");
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
        if (!responseListadoDeDepartamentos.isSuccessful()){
            throw new IOException("GeoRef: " + responseListadoDeDepartamentos.message());
        }
        return responseListadoDeDepartamentos.body();
    }
}