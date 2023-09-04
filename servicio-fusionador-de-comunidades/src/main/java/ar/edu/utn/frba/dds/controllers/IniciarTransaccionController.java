package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.EtapaTransaccion;
import ar.edu.utn.frba.dds.PropuestaFusion;
import ar.edu.utn.frba.dds.SugeridorDeFusiones;
import ar.edu.utn.frba.dds.Transaccion;
import ar.edu.utn.frba.dds.repositories.RepoTransaccion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.List;

public class IniciarTransaccionController implements Handler {

    private RepoTransaccion repo;

    public IniciarTransaccionController(RepoTransaccion repo) {
        super();
        this.repo = repo;
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Transaccion transaccion = ctx.bodyAsClass(Transaccion.class);

        System.out.println("Transaccion recien Iniciada");
        System.out.println(transaccion.toString());

        /*Inicio logica*/
        SugeridorDeFusiones sugeridor = new SugeridorDeFusiones();

        List<PropuestaFusion> propuestas = sugeridor.sugerirFusiones(transaccion.getComunidades(), transaccion.getPropuestasFusionesAntiguas());

        transaccion.setPropuestasFusionesNuevas(propuestas);

        transaccion.setEtapa(EtapaTransaccion.RETORNO_POST);
        /*Fin logica*/

        System.out.println("----------------------------------------");
        System.out.println("Transaccion con Propuestas de Fusiones Nuevas");
        System.out.println(transaccion);

        ctx.status(HttpStatus.OK);

        ctx.json(transaccion);
    }

}
