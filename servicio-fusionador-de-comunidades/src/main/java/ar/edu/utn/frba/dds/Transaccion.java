package ar.edu.utn.frba.dds;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Transaccion {
    private Long id;
    private EtapaTransaccion etapa;
    private List<Comunidad> comunidades;
    private List<Comunidad> comunidadesFusionadas;
    private List<Comunidad> comunidadesDesactivadas;
    private List<PropuestaFusion> propuestasFusionesAntiguas;
    private List<PropuestaFusion> propuestasFusionesNuevas;
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
                "  " + "id=" + id + "\n" +
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
