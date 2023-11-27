package ar.edu.utn.frba.dds.models.persistencia;

import javax.persistence.*;
import java.util.function.Supplier;

public class EntityManagerHelper {

    private static EntityManagerFactory emf;

    private static ThreadLocal<EntityManager> threadLocal;

    static {
        try {
            emf = EMFConVariablesDeBaseDeDatos.createEntityManagerFactory();
            threadLocal = new ThreadLocal<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static EntityManagerFactory emf() {
        if(emf == null || !emf.isOpen()) {
            try {
                emf = EMFConVariablesDeBaseDeDatos.createEntityManagerFactory();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return emf;
    }

    private static ThreadLocal<EntityManager> threadLocal() {
        if(threadLocal == null) {
            threadLocal = new ThreadLocal<>();
        }
        return threadLocal;
    }

    public static EntityManager entityManager() {
        return getEntityManager();
    }

    public static EntityManager getEntityManager() {
        EntityManager manager = threadLocal().get();
        if (manager == null || !manager.isOpen()) {
            manager = emf().createEntityManager();
            threadLocal().set(manager);
        }
        return manager;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if(em != null) {
            threadLocal.set(null);
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }

    public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(!tx.isActive()){
            tx.begin();
        }
    }

    public static void commit() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.commit();
        }

    }

    public static void rollback(){
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        if(tx.isActive()){
            tx.rollback();
        }
    }

    public static Query createQuery(String query) {
        return getEntityManager().createQuery(query);
    }

    public static void persist(Object o){
        entityManager().persist(o);
    }

    public static void withTransaction(Runnable action) {
        withTransaction(() -> {
            action.run();
            return null;
        });
    }
    public static <A> A withTransaction(Supplier<A> action) {
        beginTransaction();
        try {
            A result = action.get();
            commit();
            return result;
        } catch(Throwable e) {
            rollback();
            throw e;
        }
    }

    public static Query armarQueryconUnParametroInteger(String sentencia, Integer parametro){
        Query q = EntityManagerHelper.createQuery(sentencia);
        q.setParameter("parametro", parametro);
        return q;
    }


    public static Query armarQueryconUnParametroString(String sentencia, String parametro){
        Query q = EntityManagerHelper.createQuery(sentencia);
        q.setParameter("parametro", parametro);
        return q;
    }

    public void close(){
        EntityManagerHelper.closeEntityManager();
    }

//    public static Query armarQueryconDosParametroInteger(String sentencia, Integer parametro1, ){
//        Query q = EntityManagerHelper.createQuery(sentencia);
//        q.setParameter(":parametro1", parametro1);
//        return q;
//    }

    public  class Generico<T>{
        private T objeto;

        public T getObjeto() {
            return objeto;
        }

        public Generico(T objeto) {
            this.objeto = objeto;
        }
    }

}
