package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoInforme implements WithSimplePersistenceUnit {
    public void guardar(InformeSemanal unInforme) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unInforme);
        tx.commit();
    }

    public void eliminar(InformeSemanal unInforme) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unInforme);
        tx.commit();
    }

    public void modificar(InformeSemanal unInforme) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unInforme);
        tx.commit();
    }

    public InformeSemanal buscarPorId(Integer id) {
        return entityManager().find(InformeSemanal.class, id);
    }

    public List<InformeSemanal> buscarTodos() {
        return entityManager().createQuery("from " + InformeSemanal.class.getName()).getResultList();
    }

    public InformeSemanal buscarUltimo() {
            return  (InformeSemanal) entityManager()
                    .createQuery("from " + InformeSemanal.class.getName() + " order by id desc ")
                    .getResultList().get(0);
    }
}
