package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.comunidades.Usuario;
import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class RepoUsuario implements WithSimplePersistenceUnit {

    public void guardar(Usuario unUsuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unUsuario);
        EntityManagerHelper.commit();
    }

    public void eliminar(Usuario unUsuario) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unUsuario);
        EntityManagerHelper.commit();
    }

    public void modificar(Usuario unUsuario) {
        this.guardar(unUsuario);
    }

    public Usuario buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, id);
    }

    public List<Usuario> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + Usuario.class.getName()).getResultList();
    }

    public Usuario buscarPorUsuarioYContrasenia(String usuario, String contrasenia) {
        try {
            Query query = EntityManagerHelper.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasenia = :contrasenia");
            query.setParameter("usuario", usuario);
            query.setParameter("contrasenia", contrasenia);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        }
    }
}
