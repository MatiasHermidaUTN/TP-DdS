package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.localizacion.Localidad;
import ar.edu.utn.frba.dds.localizacion.Provincia;
import ar.edu.utn.frba.dds.localizacion.Departamento;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeProvincias;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeDepartamentos;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeLocalidades;
import ar.edu.utn.frba.dds.services.georef.entities.RtaUbicacion;
import ar.edu.utn.frba.dds.services.georef.entities.ListadoDeDirecciones;
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

    public RtaUbicacion dptoYProvDeUbicacion(double lat, double lon) throws IOException {
        Boolean requestConError = true;
        int intentos = 0;
        Response<RtaUbicacion> responseUbicacion = null;
        while (requestConError) {
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<RtaUbicacion> requestUbicacion = georefServiceQuerys.ubicacion(lat, lon);
            responseUbicacion = requestUbicacion.execute();
            intentos++;
            if (!responseUbicacion.isSuccessful() && intentos < 15) {
                System.out.println("GeoRef: " + responseUbicacion.message());
            } else if (intentos >= 15) {
                throw new IOException("GeoRef: " + responseUbicacion.message());
            } else {
                requestConError = false;
            }
        }
        return responseUbicacion.body();
    }

    public ListadoDeProvincias listadoDeProvincias() throws IOException {
        Boolean requestConError = true;
        int intentos = 0;
        Response<ListadoDeProvincias> responseListadoDeProvincias = null;
        while (requestConError) {
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeProvincias> requestProvincias = georefServiceQuerys.provincias();
            responseListadoDeProvincias = requestProvincias.execute();
            intentos++;
            if (!responseListadoDeProvincias.isSuccessful() && intentos < 15) {
                System.out.println("GeoRef: " + responseListadoDeProvincias.message());
            } else if (intentos >= 15) {
                throw new IOException("GeoRef: " + responseListadoDeProvincias.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeProvincias.body();
    }

    public ListadoDeDepartamentos listadoDeDepartamentosDeProvincia(@NotNull Provincia provincia) throws IOException {
        Boolean requestConError = true;
        int intentos = 0;
        Response<ListadoDeDepartamentos> responseListadoDeDepartamentos = null;
        while(requestConError){
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeDepartamentos> requestListadoDeDepartamentos = georefServiceQuerys.departamentos(provincia.id, "id,nombre,centroide,provincia");
            responseListadoDeDepartamentos = requestListadoDeDepartamentos.execute();
            intentos++;
            if (!responseListadoDeDepartamentos.isSuccessful() && intentos < 15) {
                System.out.println("GeoRef: " + responseListadoDeDepartamentos.message());
            } else if (intentos >= 15) {
                throw new IOException("GeoRef: " + responseListadoDeDepartamentos.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeDepartamentos.body();
    }

    public ListadoDeLocalidades listadoDeLocalidadesDeDepartamento(@NotNull Departamento departamento) throws IOException {
        Boolean requestConError = true;
        int intentos = 0;
        Response<ListadoDeLocalidades> responseListadoDeLocalidades = null;
        while(requestConError){
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeLocalidades> requestListadoDeLocalidades = georefServiceQuerys.localidades(departamento.id, "id,nombre,centroide,departamento");
            responseListadoDeLocalidades = requestListadoDeLocalidades.execute();
            intentos++;
            if (!responseListadoDeLocalidades.isSuccessful() && intentos < 15) {
                System.out.println("GeoRef: " + responseListadoDeLocalidades.message());
            } else if (intentos >= 15) {
                throw new IOException("GeoRef: " + responseListadoDeLocalidades.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeLocalidades.body();
    }

    public ListadoDeDirecciones listadoDeDirecciones(String direccion, Localidad localidad) throws IOException {
        Boolean requestConError = true;
        int intentos = 0;
        Response<ListadoDeDirecciones> responseListadoDeDirecciones = null;
        while(requestConError){
            GeorefServiceQuerys georefServiceQuerys = this.retrofit.create(GeorefServiceQuerys.class);
            Call<ListadoDeDirecciones> requestListadoDeDirecciones = georefServiceQuerys.direcciones(direccion, localidad.nombre);
            responseListadoDeDirecciones = requestListadoDeDirecciones.execute();
            intentos++;
            if (!responseListadoDeDirecciones.isSuccessful() && intentos < 15) {
                System.out.println("GeoRef: " + responseListadoDeDirecciones.message());
            } else if (intentos >= 15) {
                throw new IOException("GeoRef: " + responseListadoDeDirecciones.message());
            } else {
                requestConError = false;
            }
        }
        return responseListadoDeDirecciones.body();
    }
}