package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes;

import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntidadesConNivelDeImpacto;
import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntityDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface CalcNivelImpactoQuerys {
    @POST("entidades")
    Call<List<EntidadesConNivelDeImpacto>> calcNivImpactoEntidades(@Body List<EntityDTO> entidades); //@Body
}
