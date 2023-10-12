package ar.edu.utn.frba.dds.models.servicioFusionadorDeComunidades.Entities;

import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.converters.LocalDateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
public class PropuestaFusionDTO {
    Integer id;
    ComunidadDTO comunidad1;
    ComunidadDTO comunidad2;
    ComunidadDTO comunidadFusionada;
    ComunidadDTO comunidad1desactivada;
    ComunidadDTO comunidad2desactivada;
    EstadoPropuestaFusion estado;
    LocalDateTime fechaDePropuesta;
}

