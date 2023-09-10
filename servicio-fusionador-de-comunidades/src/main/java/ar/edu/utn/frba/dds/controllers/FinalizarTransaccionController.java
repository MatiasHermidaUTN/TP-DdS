package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.domain.Comunidad;
import ar.edu.utn.frba.dds.domain.EtapaTransaccion;
import ar.edu.utn.frba.dds.domain.PropuestaFusion;
import ar.edu.utn.frba.dds.domain.Transaccion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FinalizarTransaccionController implements Handler {
    private static final Logger logger = Logger.getLogger(FinalizarTransaccionController.class.getName());


    public FinalizarTransaccionController() {
        super();
    }


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Transaccion transaccion = ctx.bodyAsClass(Transaccion.class);

        if (this.checkearDatosDeEntrada(transaccion, ctx)) {
            return;// Si hay algun error en los datos de entrada, devuelve true y envia la respuesta HTTP correspondiente
        }

        //logger.info("Transaccion recien Patcheada");
        //logger.info(transaccion.toString());

        List<PropuestaFusion> propuestasAceptadas = transaccion.getPropuestasFusionesNuevas()
                .stream()
                .filter(prop -> prop.estaAceptada())
                .toList();

        /* Obtener las nuevas comunidades fusionadas */
        List<Comunidad> comunidadesFusionadas = new ArrayList<>();

        propuestasAceptadas.forEach(prop -> prop.fusionarComunidades());

        propuestasAceptadas.forEach(prop -> comunidadesFusionadas.add(prop.getComunidadFusionada()));

        transaccion.setComunidadesFusionadas(comunidadesFusionadas);


        /* Obtener las comunidades desactivadas */
        List<Comunidad> comunidadesDesactivadas = new ArrayList<>();

        propuestasAceptadas.forEach(prop -> comunidadesDesactivadas.add(prop.getComunidad1()));
        propuestasAceptadas.forEach(prop -> comunidadesDesactivadas.add(prop.getComunidad2()));

        transaccion.setComunidadesDesactivadas(comunidadesDesactivadas);


        transaccion.setFechaFinTransaccion(LocalDate.now());

        transaccion.setEtapa(EtapaTransaccion.RETORNO_PATCH);

        //logger.info("----------------------------------------");
        //logger.info("Transaccion finalizada");
        //logger.info(transaccion.toString());

        ctx.status(HttpStatus.OK);

        ctx.json(transaccion);
    }

    private Boolean checkearDatosDeEntrada(Transaccion transaccion, Context ctx) {
        if(!transaccion.getPropuestasFusionesNuevas().stream().allMatch(prop -> prop.estaAceptada() || prop.estaRechazada())){
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Hay propuestas de fusiones nuevas con aceptacion indefinida");
            return true;
        }
        return false;
    }
}
