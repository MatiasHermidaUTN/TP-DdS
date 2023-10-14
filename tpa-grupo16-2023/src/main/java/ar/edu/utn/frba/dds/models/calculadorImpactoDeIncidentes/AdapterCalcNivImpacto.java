package ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes;

import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntidadesConNivelDeImpacto;
import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.EntityDTO;
import ar.edu.utn.frba.dds.models.calculadorImpactoDeIncidentes.entities.IncidentDTO;
import ar.edu.utn.frba.dds.models.comunidades.Comunidad;
import ar.edu.utn.frba.dds.models.incidentes.Incidente;
import ar.edu.utn.frba.dds.models.incidentes.Prestacion;
import ar.edu.utn.frba.dds.models.repositorios.RepoIncidente;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdapterCalcNivImpacto {
    private static AdapterCalcNivImpacto instance = null;
    private static CalcNivelImpactoRetrofit calcNivelImpactoRetrofit;

    private AdapterCalcNivImpacto(){
        calcNivelImpactoRetrofit = CalcNivelImpactoRetrofit.instancia();
    }

    public static AdapterCalcNivImpacto instancia(){
        if(instance == null){
            instance = new AdapterCalcNivImpacto();
        }
        return instance;
    }

    public List<Entidad> calcularNivelDeImpacto(List<Entidad> entidades, List<Prestacion> prestaciones, LocalDateTime fechaDeSemana) throws IOException {
        List<EntityDTO> entidadesDTO = new ArrayList<>();

        for(Entidad entidad : entidades){
            // obtengo las prestaciones de la entidad
            List<Prestacion> prestacionesDeEntidad = prestaciones.stream().
                    filter(prestacion -> prestacion.getEstablecimiento().getEntidad().getId().equals(entidad.getId())).
                    toList();

            // genero la EntityDTO
            EntityDTO entidadDTO = this.entidadToEntidadDTO(entidad, prestacionesDeEntidad, fechaDeSemana);
            // agrego la entidadDTO a la lista de entidadesDTO
            entidadesDTO.add(entidadDTO);
        }

//        System.out.println("entidadesDTO: " + entidadesDTO);
//        System.out.println();

        //CONSULTA A LA API
        List<EntidadesConNivelDeImpacto> listEntidadesNivImpacto = calcNivelImpactoRetrofit.calcNivImpactoEntidades(entidadesDTO);

//        System.out.println("listEntidadesNivImpacto: " + listEntidadesNivImpacto);
//        System.out.println();

        List<Entidad> entidadesRankeadas = new ArrayList<>();
        for (EntidadesConNivelDeImpacto entidadConNivelDeImpacto : listEntidadesNivImpacto){
            // obtengo la entidad
            Entidad entidad = entidades.stream().
                    filter(entidad1 -> entidad1.getId() == entidadConNivelDeImpacto.getId()).
                    findFirst().
                    get();
            // agrego la entidad a la lista de entidadesRankeadas
            entidadesRankeadas.add(entidad);
        }
        return entidadesRankeadas;
    }

    private EntityDTO entidadToEntidadDTO(Entidad entidad, List<Prestacion> prestacionesDeEntidad, LocalDateTime fechaDeSemana){
        EntityDTO entidadDTO = new EntityDTO();
        // seteo los atributos de entidadDTO
        entidadDTO.setId(entidad.getId());
        entidadDTO.setNombre(entidad.getNombre());

        // logica para setear miembrosAfectados
        Integer miembrosAfectados = 0;
        List<IncidentDTO> incidentDTOs = new ArrayList<>();

        List<Incidente> todosLosIncidentes = new RepoIncidente().buscarTodos();
        List<Incidente> incidentesDeLaSemana = todosLosIncidentes.stream().
                filter(incidente -> incidente.seReportoEnLaSemanaDeLaFecha(fechaDeSemana)).
                toList();

        for (Prestacion prestacion : prestacionesDeEntidad){
            List<Incidente> incidentesDeLaPrestacion = incidentesDeLaSemana.stream().
                    filter(incidente -> incidente.getPrestacion().getId().equals(prestacion.getId())).toList();

            for (Incidente incidente : incidentesDeLaPrestacion){
                // sumo los miembros afectados de los incidentes de la semana
                miembrosAfectados += incidente.getMiembrosAfectados();

                // genero el incidentDTO
                IncidentDTO incidentDTO = this.incidenteToIncidenteDTO(incidente);
                incidentDTOs.add(incidentDTO);
            }
        }

        entidadDTO.setMiembrosAfectados(miembrosAfectados);
        entidadDTO.setIncidentes(incidentDTOs);

        return entidadDTO;
    }

    private IncidentDTO incidenteToIncidenteDTO(Incidente incidente){
        IncidentDTO incidentDTO = new IncidentDTO();
        // seteo los atributos de incidentDTO
        incidentDTO.setDescripcion(incidente.getObservaciones());
        incidentDTO.setResuelto(incidente.estaResuelto());
        incidentDTO.setTiempoResolucion( (int) incidente.minutosEntreAperturaYCierre());
        return incidentDTO;
    }
}
