package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.domain.EtapaTransaccion;
import ar.edu.utn.frba.dds.domain.PropuestaFusion;
import ar.edu.utn.frba.dds.domain.SugeridorDeFusiones;
import ar.edu.utn.frba.dds.domain.Transaccion;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.List;

public class IniciarTransaccionController implements Handler {
    private static final Logger logger = Logger.getLogger(IniciarTransaccionController.class.getName());

    public IniciarTransaccionController() {
        super();
    }

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        Transaccion transaccion = ctx.bodyAsClass(Transaccion.class);

        if (this.checkearDatosDeEntrada(transaccion, ctx)) {
            ctx.status(HttpStatus.BAD_REQUEST);
            return;// Si hay algun error en los datos de entrada, devuelve true y envia la respuesta HTTP correspondiente
        }

        //logger.info("Transaccion recien Iniciada");
        //logger.info(transaccion.toString());

        /*Inicio logica*/
        SugeridorDeFusiones sugeridor = new SugeridorDeFusiones();

        List<PropuestaFusion> propuestas = sugeridor.sugerirFusiones(transaccion.getComunidades(), transaccion.getPropuestasFusionesAntiguas());

        transaccion.setPropuestasFusionesNuevas(propuestas);

        transaccion.setEtapa(EtapaTransaccion.RETORNO_POST);

        //transaccion.setFechaInicioTransaccion(LocalDate.now());
        /*Fin logica*/

        //logger.info("----------------------------------------");
        //logger.info("Transaccion con Propuestas de Fusiones Nuevas");
        //logger.info(transaccion.toString());

        ctx.status(HttpStatus.OK);
        ctx.json(transaccion);
    }

    private Boolean checkearDatosDeEntrada(Transaccion transaccion, Context ctx) {

        if(!transaccion.getPropuestasFusionesAntiguas().stream().allMatch(prop -> prop.estaAceptada() || prop.estaRechazada())){
            ctx.result("Hay propuestas de fusiones antiguas no aceptadas");
            return true;
        }
        if(!transaccion.getEtapa().equals(EtapaTransaccion.POSTEAR_TRANSACCION)){
            ctx.result("La transaccion no esta en la etapa correcta");
            return true;
        }
        if(transaccion.getComunidades() == null) {
            ctx.result("No hay comunidades");
            return true;
        }
        if(transaccion.getComunidades().isEmpty()) {
            ctx.result("No hay comunidades");
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
        if(transaccion.getComunidadesFusionadas() == null){
            ctx.result("La lista de comunidades fusionadas es nula, deberia ser lista vacia");
            return true;
        }
        if(!transaccion.getComunidadesFusionadas().isEmpty()){
            ctx.result("Ya hay comunidades fusionadas(no deberia)");
            return true;
        }
        if(transaccion.getComunidadesDesactivadas() == null){
            ctx.result("La lista de comunidades desactivadas es nula, deberia ser lista vacia");
            return true;
        }
        if(!transaccion.getComunidadesDesactivadas().isEmpty()){
            ctx.result("Ya hay comunidades desactivadas(no deberia)");
            return true;
        }
        if(transaccion.getPropuestasFusionesNuevas() == null){
            ctx.result("La lista de propuestas de fusiones nuevas es nula, deberia ser lista vacia");
            return true;
        }
        if(!transaccion.getPropuestasFusionesNuevas().isEmpty()){
            ctx.result("Ya hay propuestas de fusiones nuevas(no deberia)");
            return true;
        }
        if(transaccion.getFechaInicioTransaccion() == null){
            ctx.result("Fecha de inicio de transaccion es nula (no deberia)");
            return true;
        }
        if(transaccion.getFechaInicioTransaccion().isAfter(LocalDate.now())){
            ctx.result("La fecha de inicio de transaccion es posterior a la fecha actual");
            return true;
        }
        if(transaccion.getFechaFinTransaccion() != null){
            ctx.result("Ya hay fecha de fin de transaccion(no deberia)");
            return true;
        }
        return false;
    }

}
