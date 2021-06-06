package com.company.persitance;

import com.company.metier.ClientEntity;
import com.company.metier.RentedItemEntity;
import com.company.metier.StockItemEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.Date;

public class QueryPersistance implements IQueryPersistance {

    @Override
    public ClientEntity findCustomerByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNativeQuery("SELECT customerID,accountBalance FROM Client WHERE name =?1");
            q.setParameter(1, name);
            Object[] result = (Object[])q.getSingleResult();
            int id= (Integer) result[0];
            Double  n = (Double) result[1];
            ClientEntity cl = new ClientEntity();
            cl.setAccountBalance(n);
            cl.setCustomerId(id);
            cl.setName(name);
            tr.commit();
            return cl;
        }
        finally {
            if (tr.isActive()) {
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public StockItemEntity findStockByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNativeQuery("SELECT itemID,rentalPrice FROM StockItem WHERE title=?1");
            q.setParameter(1, title);
            Object[] result = (Object[])q.getSingleResult();
            int id= (Integer) result[0];
            Double  n = (Double) result[1];
            StockItemEntity s = new StockItemEntity();
            s.setRentalPrice(n);
            s.setItemId(id);
            s.setTitle(title);
            tr.commit();
            return s;
        }
        finally {
            if (tr.isActive()) {
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }

    @Override
    public RentedItemEntity findRentedItem(int customer, int item) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNativeQuery("SELECT dueDate FROM RentedItem WHERE customerID=?1 AND itemID=?2");
            q.setParameter(1, customer);
            q.setParameter(2, item);
            Object[] result = (Object[])q.getSingleResult();
            java.sql.Date n = (java.sql.Date) result[0];
            RentedItemEntity s = new RentedItemEntity();
            s.setDueDate(n);
            s.setItemId(item);
            s.setCustomerId(customer);
            tr.commit();
            return s;
        }
        finally {
            if (tr.isActive()) {
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }
}
