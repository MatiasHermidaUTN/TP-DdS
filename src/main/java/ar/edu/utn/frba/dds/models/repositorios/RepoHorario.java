package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.notificaciones.Horario;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoHorario implements WithSimplePersistenceUnit {

    public void guardar(Horario unHorario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unHorario);
        EntityManagerHelper.commit();
    }

    public void eliminar(Horario unHorario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unHorario);
        EntityManagerHelper.commit();
    }

    public void modificar(Horario unHorario) {
        this.guardar(unHorario);
    }

    public Horario buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Horario.class, id);
    }

    public List<Horario> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Horario.class.getName()).getResultList();
    }
}
