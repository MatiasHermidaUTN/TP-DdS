package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.localizacion.Provincia;
import ar.edu.utn.frba.dds.localizacion.Departamento;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeProvincias;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeLocalidades;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class GeorefServiceRetrofit {
    private static GeorefServiceRetrofit instancia = null;
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
        Boolean requestConError = true;
        Response<ListadoDeProvincias> responseListadoDeProvincias = null;
        while (requestConError) {
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeProvincias> requestProvinciasArgentinas = georefServiceQuerys.provincias();
            responseListadoDeProvincias = requestProvinciasArgentinas.execute();
            if (!responseListadoDeProvincias.isSuccessful()) {
                //throw new IOException("GeoRef: " + responseListadoDeProvincias.message());
                System.out.println("GeoRef: " + responseListadoDeProvincias.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeProvincias.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(@NotNull Provincia provincia) throws IOException {
        Boolean requestConError = true;
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = null;
        while(requestConError){
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefServiceQuerys.departamentos(provincia.id, "id,nombre,centroide");
            responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
            if (!responseListadoDeDepartamentos.isSuccessful()) {
                //throw new IOException("GeoRef: " + responseListadoDeDepartamentos.message());
                System.out.println("GeoRef: " + responseListadoDeDepartamentos.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeDepartamentos.body();
    }

    public ListadoDeLocalidades listadoDeLocalidadesDeDepartamento(@NotNull Departamento departamento) throws IOException {
        Boolean requestConError = true;
        Response<ListadoDeLocalidades> responseListadoDeLocalidades = null;
        while(requestConError){
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeLocalidades> requestListadoDeLocalidades = georefServiceQuerys.localidades(departamento.id, "id,nombre,centroide");
            responseListadoDeLocalidades = requestListadoDeLocalidades.execute();
            if (!responseListadoDeLocalidades.isSuccessful()) {
                //throw new IOException("GeoRef: " + responseListadoDeDepartamentos.message());
                System.out.println("GeoRef: " + responseListadoDeLocalidades.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeLocalidades.body();
    }
}