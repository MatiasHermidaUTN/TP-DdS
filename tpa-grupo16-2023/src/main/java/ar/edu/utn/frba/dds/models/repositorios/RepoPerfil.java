package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Perfil;
import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoPerfil implements WithSimplePersistenceUnit {
    public void guardar(Perfil unPerfil) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unPerfil);
        tx.commit();
    }

    public void eliminar(Perfil unPerfil) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unPerfil);
        tx.commit();
    }

    public void modificar(Perfil unPerfil) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unPerfil);
        tx.commit();
    }

    public Perfil buscarPorId(Integer id) {
        return entityManager().find(Perfil.class, id);
    }

    public List<Perfil> buscarTodos() {
        return entityManager().createQuery("from " + Perfil.class.getName()).getResultList();
    }

    public RepoPerfil() {
    }
}
