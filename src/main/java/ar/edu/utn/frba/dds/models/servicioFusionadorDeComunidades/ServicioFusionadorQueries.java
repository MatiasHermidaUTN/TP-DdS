package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades;

import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.PropuestaFusionDTO;
import ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities.Transaccion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface ServicioFusionadorQueries {
    @POST("transacciones")
    Call<List<PropuestaFusionDTO>> sugerirFusiones(@Body Transaccion transaccion);
}
