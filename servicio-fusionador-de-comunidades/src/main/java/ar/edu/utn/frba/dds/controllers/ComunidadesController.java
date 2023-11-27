package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.domain.PropuestaFusionDTO;
import ar.edu.utn.frba.dds.domain.SugeridorDeFusiones;
import ar.edu.utn.frba.dds.domain.Transaccion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.List;

public class ComunidadesController implements Handler {
    private static final Logger logger = Logger.getLogger(ComunidadesController.class.getName());

    public ComunidadesController() {
        super();
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        System.out.println(ctx.body());
        Transaccion transaccion = ctx.bodyAsClass(Transaccion.class);

        if (this.checkearDatosDeEntrada(transaccion, ctx)) {
            System.out.println("Hay errores en los datos de entrada");
            ctx.result("Hay errores en los datos de entrada");
            ctx.status(HttpStatus.BAD_REQUEST);
            return;// Si hay algun error en los datos de entrada, devuelve true y envia la respuesta HTTP correspondiente
        }
        SugeridorDeFusiones sugeridor = new SugeridorDeFusiones();

        List<PropuestaFusionDTO> propuestas = sugeridor.sugerirFusiones(transaccion.getComunidades(), transaccion.getPropuestasFusion());

        System.out.println("propuestas: " + propuestas);

        propuestas.stream().forEach(prop -> prop.fusionarComunidades());

        ctx.status(HttpStatus.OK);
        ctx.json(propuestas);
    }

    private Boolean checkearDatosDeEntrada(Transaccion transaccion, Context ctx) {

        if(!transaccion.getPropuestasFusion().stream().allMatch(prop -> prop.estaAceptada() || prop.estaRechazada())){
            ctx.result("Hay propuestas de fusiones antiguas no aceptadas");
            return true;
        }
        if(transaccion.getComunidades() == null) {
            ctx.result("No hay comunidades en la transaccion");
            return true;
        }
        if(transaccion.getComunidades().isEmpty()) {
            ctx.result("No hay comunidades en la transaccion");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdServicios() == null)){
            ctx.result("No todas las comunidades tienen servicios");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdServicios().isEmpty())){
            ctx.result("No todas las comunidades tienen servicios");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdEstablecimientos() == null)){
            ctx.result("No todas las comunidades tienen establecimientos");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdEstablecimientos().isEmpty())){
            ctx.result("No todas las comunidades tienen establecimientos");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdUsuarios() == null)){
            ctx.result("No todas las comunidades tienen usuarios");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getIdUsuarios().isEmpty())){
            ctx.result("No todas las comunidades tienen usuarios");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> !comunidad.getActiva())){
            ctx.result("No todas las comunidades estan activas");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getId() == null)){
            ctx.result("No todas las comunidades tienen ID (es necesario que tengan)");
            return true;
        }
        if(transaccion.getComunidades().stream().anyMatch(comunidad -> comunidad.getGradoDeConfianza() == null)){
            ctx.result("No todas las comunidades tienen grado de confianza");
            return true;
        }
        return false;
    }

}