package com.company.persitance;

import com.company.metier.ClientEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ClientPersistance {
    public void CreateClient(ClientEntity c){
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
    }
