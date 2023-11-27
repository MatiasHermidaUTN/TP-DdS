package ar.edu.utn.frba.dds.models.repositorios;

import ar.edu.utn.frba.dds.models.persistencia.EntityManagerHelper;
import ar.edu.utn.frba.dds.models.ranking.InformeSemanal;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Entidad;
import ar.edu.utn.frba.dds.models.serviciosPublicos.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import javax.persistence.EntityTransaction;
import java.util.List;

public class RepoInforme implements WithSimplePersistenceUnit {
    public void guardar(InformeSemanal unInforme) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(unInforme);
        EntityManagerHelper.commit();
    }

    public void eliminar(InformeSemanal unInforme) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(unInforme);
        EntityManagerHelper.commit();
    }

    public void modificar(InformeSemanal unInforme) {
        this.guardar(unInforme);
    }

    public InformeSemanal buscarPorId(Integer id) {
        return EntityManagerHelper
                .getEntityManager()
                .find(InformeSemanal.class, id);
    }

    public List<InformeSemanal> buscarTodos() {
        return EntityManagerHelper.createQuery("from " + InformeSemanal.class.getName()).getResultList();
    }

    public InformeSemanal buscarUltimo() {
            return  (InformeSemanal) EntityManagerHelper
                    .createQuery("from " + InformeSemanal.class.getName() + " order by id desc ")
                    .getResultList().get(0);
    }
}
