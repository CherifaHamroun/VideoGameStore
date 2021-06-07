package com.company.persitance;

import com.company.metier.ClientEntity;
import com.company.metier.RentedItemEntity;
import com.company.metier.StockItemEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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
            cl.setCustomerID(id);
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
            String st = "SELECT dueDate FROM RentedItem WHERE customerID=?1 AND itemID=?2";
            Query q = em.createNativeQuery(st);
            q.setParameter(1, customer);
            q.setParameter(2,item);
            List result = q.getResultList();
            Date date= (Date) result.get(0);
            RentedItemEntity s = new RentedItemEntity();
            s.setCustomerId(customer);
            s.setItemId(item);
            s.setDueDate(date);
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
    public List<ClientEntity> getClients() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNamedQuery(ClientEntity.FIND_ALL);
            tr.commit();
            return q.getResultList();
        }
        finally {
            if (tr.isActive()) {
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }
    public List<StockItemEntity> getItems() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNamedQuery(StockItemEntity.FIND_ALL);
            tr.commit();
            return q.getResultList();
        }
        finally {
            if (tr.isActive()) {
                tr.rollback();
            }
            em.close();
            emf.close();
        }
    }
    public String getTypeParam(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            String st = "SELECT actor FROM Film WHERE itemID=?1";
            Query q = em.createNativeQuery(st);
            q.setParameter(1, id);
            List<String> result = (List<String>)q.getResultList();
            String s= "";
            if (result.size()!=0){
                s="Film;"+result.get(0);
            }
            else{
                Query q2 = em.createNativeQuery("SELECT Platform FROM Jeux WHERE itemID=?1");
                q2.setParameter(1, id);
                List result2 = q2.getResultList();
                s="Jeux;"+result2.get(0);

            }
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
    public String isCheckedOut(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            String st = "SELECT itemID FROM RentedItem WHERE itemID=?1";
            Query q = em.createNativeQuery(st);
            q.setParameter(1, id);
            List<String> result = (List<String>)q.getResultList();
            String s= "";
            if (result.size()==0){
                s="No";
            }
            else s="Yes";
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
    public List<RentedItemEntity> overdueItems() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNamedQuery(RentedItemEntity.FIND_OVERDUE);
            tr.commit();
            return q.getResultList();
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
    public StockItemEntity findItemById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNativeQuery("SELECT rentalPrice,title FROM StockItem WHERE itemID =?1");
            q.setParameter(1, id);
            Object[] result = (Object[])q.getSingleResult();
            Double rent= (Double) result[0];
            String tit = (String) result[1];
            StockItemEntity st = new StockItemEntity();
            st.setTitle(tit);
            st.setRentalPrice(rent);
            st.setItemId(id);
            tr.commit();
            return st;
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
    public List<Integer> findFilmByActor(String actor) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr= em.getTransaction();
        try{
            tr.begin();
            Query q = em.createNativeQuery("SELECT itemID FROM Film WHERE actor =?1");
            q.setParameter(1, actor);
            List<Integer> result = (List<Integer>) q.getResultList();
            tr.commit();
            return result;
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
