package repository.dao;

import config.JPAUtil;
import entities.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.annotations.DialectOverride;
import repository.ICRUD;
import java.util.List;
import java.util.Optional;

public class DCargo implements ICRUD<Cargo, Long> {
    @Override
    public void create(Cargo cargo) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(cargo);
            transaction.commit();
        }catch (Exception e){
            if (transaction.isActive()){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Cargo> getById(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return Optional.ofNullable(em.find(Cargo.class, id));
        }finally {
            em.close();
        }
    }

    @Override
    public List<Cargo> getAll(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("from Cargo", Cargo.class).getResultList();
        }finally{
            em.close();
        }
    }

    @Override
    public void update(Cargo cargo) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(cargo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Optional<Cargo> cargo = Optional.ofNullable(em.find(Cargo.class, id));
            cargo.ifPresent(em::remove);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}