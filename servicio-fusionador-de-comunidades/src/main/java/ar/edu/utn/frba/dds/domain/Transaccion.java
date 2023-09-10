package ar.edu.utn.frba.dds.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaccion {
    private EtapaTransaccion etapa;
    private List<Comunidad> comunidades;                        //IN
    private List<Comunidad> comunidadesFusionadas;              //OUT
    private List<Comunidad> comunidadesDesactivadas;            //OUT
    private List<PropuestaFusion> propuestasFusionesAntiguas;   //IN
    private List<PropuestaFusion> propuestasFusionesNuevas;     //OUT
    private LocalDate fechaInicioTransaccion;
    private LocalDate fechaFinTransaccion;

    public Transaccion() {
        this.comunidades = new ArrayList<Comunidad>();
        this.comunidadesFusionadas = new ArrayList<Comunidad>();
        this.comunidadesDesactivadas = new ArrayList<Comunidad>();
        this.propuestasFusionesAntiguas = new ArrayList<PropuestaFusion>();
        this.propuestasFusionesNuevas = new ArrayList<PropuestaFusion>();
    }

    @Override
    public String toString() {
        return "Transaccion{ " + this.hashCode() + "\n" +
                "  " + "etapa=" + etapa + "\n" +
                "  " + "comunidades=" + comunidades + "\n" +
                "  " + "comunidadesFusionadas=" + comunidadesFusionadas + "\n" +
                "  " + "comunidadesDesactivadas=" + comunidadesDesactivadas + "\n" +
                "  " + "propuestasFusionesAntiguas=" + propuestasFusionesAntiguas + "\n" +
                "  " + "propuestasFusionesNuevas=" + propuestasFusionesNuevas + "\n" +
                "  " + "fechaInicioTransaccion=" + fechaInicioTransaccion + "\n" +
                "  " + "fechaFinTransaccion=" + fechaFinTransaccion + "\n" +
                '}';
    }
}
