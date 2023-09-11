package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoUsuario implements WithSimplePersistenceUnit {

    public void guardar(Usuario unUsuario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().persist(unUsuario);
        tx.commit();
    }

    public void eliminar(Usuario unUsuario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().remove(unUsuario);
        tx.commit();
    }

    public void modificar(Usuario unUsuario) {
        EntityTransaction tx = entityManager().getTransaction();
        tx.begin();
        entityManager().merge(unUsuario);
        tx.commit();
    }

    public Usuario buscarPorId(Integer id) {
        return entityManager().find(Usuario.class, id);
    }

    public List<Usuario> buscarTodos() {
        return entityManager().createQuery("from " + Usuario.class.getName()).getResultList();
    }
}
