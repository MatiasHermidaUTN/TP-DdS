package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    public Usuario buscarPorUsuarioYContrasenia(String usuario, String contrasenia) {
        try {
            Query query = entityManager().createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasenia = :contrasenia");
            query.setParameter("usuario", usuario);
            query.setParameter("contrasenia", contrasenia);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        }
    }
}
