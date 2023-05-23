package ar.edu.utn.frba.dds.serviciosPublicos;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegistroDeCambio {

    @Getter
    private Map<LocalDateTime, EstadoServicio> registroDeCambio;

    public void registrarCambio(EstadoServicio estadoServicio, LocalDateTime tiempo) {
        this.registroDeCambio.put(tiempo, estadoServicio);
    }

    public RegistroDeCambio() {
        this.registroDeCambio = new HashMap<LocalDateTime, EstadoServicio>();
    }
}
