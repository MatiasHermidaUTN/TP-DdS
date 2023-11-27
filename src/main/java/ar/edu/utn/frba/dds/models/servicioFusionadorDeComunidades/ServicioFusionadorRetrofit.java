package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades;

import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusionDTO;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.Transaccion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioFusionadorRetrofit {
    private static ServicioFusionadorRetrofit instancia = null;
    private static final String urlApi = "http://localhost:8081/api/";
    private Retrofit retrofit;

    private ServicioFusionadorRetrofit() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    public static ServicioFusionadorRetrofit instancia(){
        if(instancia == null){
            instancia = new ServicioFusionadorRetrofit();
        }
        return instancia;
    }

    public List<PropuestaFusionDTO> sugerirFusiones(Transaccion transaccion) throws IOException {
        ServicioFusionadorQueries servicioFusionadorQueries = this.retrofit.create(ServicioFusionadorQueries.class);
        Call<List<PropuestaFusionDTO>> call = servicioFusionadorQueries.sugerirFusiones(transaccion);
        Response<List<PropuestaFusionDTO>> response = call.execute();
        System.out.println("response: " + response);
        System.out.println();
        return response.body();
    }
}

