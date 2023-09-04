package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.*;
import ar.edu.utn.frba.dds.repositories.RepoTransaccion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinalizarTransaccionController implements Handler {

    private RepoTransaccion repo;

    public FinalizarTransaccionController(RepoTransaccion repo) {
        super();
        this.repo = repo;
    }


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Transaccion transaccion = ctx.bodyAsClass(Transaccion.class);

        System.out.println("Transaccion recien Patcheada");
        System.out.println(transaccion.toString());

        List<PropuestaFusion> propuestasAceptadas = transaccion.getPropuestasFusionesNuevas()
                .stream()
                .filter(prop -> prop.getAceptada())
                .toList();

        List<PropuestaFusion> propuestasRechazadas = transaccion.getPropuestasFusionesNuevas()
                .stream()
                .filter(prop -> prop.getAceptada())
                .toList();

        /** Obtener las nuevas comunidades fusionadas */
        List<Comunidad> comunidadesFusionadas = new ArrayList<>();

        propuestasAceptadas.forEach(prop -> prop.fusionarComunidades());

        propuestasAceptadas.forEach(prop -> comunidadesFusionadas.add(prop.getComunidadFusionada()));

        transaccion.setComunidadesFusionadas(comunidadesFusionadas);


        /** Obtener las comunidades desactivadas */
        List<Comunidad> comunidadesDesactivadas = new ArrayList<>();

        propuestasAceptadas.forEach(prop -> comunidadesDesactivadas.add(prop.getComunidad1()));
        propuestasAceptadas.forEach(prop -> comunidadesDesactivadas.add(prop.getComunidad2()));

        transaccion.setComunidadesDesactivadas(comunidadesDesactivadas);


        transaccion.setFechaFinTransaccion(LocalDate.now());

        transaccion.setEtapa(EtapaTransaccion.RETORNO_PATCH);

        System.out.println("----------------------------------------");
        System.out.println("Transaccion finalizada");
        System.out.println(transaccion);

        ctx.status(HttpStatus.OK);

        ctx.json(transaccion);
    }
}
