package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.notificaciones.Horario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoHorario implements WithSimplePersistenceUnit {

    public void guardar(Horario unHorario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unHorario);
        tx.commit();
    }

    public void eliminar(Horario unHorario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unHorario);
        tx.commit();
    }

    public void modificar(Horario unHorario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unHorario);
        tx.commit();
    }

    public Horario buscarPorId(Integer id) {
        return entityManager().find(Horario.class, id);
    }

    public List<Horario> buscarTodos() {
        return entityManager().createQuery("from " + Horario.class.getName()).getResultList();
    }
}
