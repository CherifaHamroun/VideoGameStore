package com.company.persitance;

import com.company.metier.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TransactionPersistance implements ITransactionPersistance {

    public void createClient(ClientEntity c){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.persist(c);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public void updateClientBalance(ClientEntity c,Double balance) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.find(ClientEntity.class, c.getCustomerId());
            c.setAccountBalance(balance);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public int addStock(StockItemEntity s) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.persist(s);
            tr.commit();
            return s.getItemId();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public void addFilm(FilmEntity f) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.persist(f);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public void addJeux(JeuxEntity j) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.persist(j);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public void addRentedItem(RentedItemEntity r) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.persist(r);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public void deleteRentedItem(RentedItemEntity r) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            em.remove(r);
            tr.commit();
        }
        finally {
            if (tr.isActive()){
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

}
