package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes;

import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntidadesConNivelDeImpacto;
import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntityDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class CalcNivelImpactoRetrofit {
    private static CalcNivelImpactoRetrofit instancia = null;
    private static final String urlApi = "http://localhost:8081/api/";
    private Retrofit retrofit;

    private CalcNivelImpactoRetrofit() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static CalcNivelImpactoRetrofit instancia(){
        if(instancia == null){
            instancia = new CalcNivelImpactoRetrofit();
        }
        return instancia;
    }

    public List<EntidadesConNivelDeImpacto> calcNivImpactoEntidades(List<EntityDTO> entidades) throws IOException {
        CalcNivelImpactoQuerys calcNivelImpactoQuerys = this.retrofit.create(CalcNivelImpactoQuerys.class);
        Call<List<EntidadesConNivelDeImpacto>> call = calcNivelImpactoQuerys.calcNivImpactoEntidades(entidades);
        Response<List<EntidadesConNivelDeImpacto>> response = call.execute();
        return response.body();
    }
}
