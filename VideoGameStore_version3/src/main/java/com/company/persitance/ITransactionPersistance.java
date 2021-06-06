package com.company.persitance;

import com.company.metier.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public interface ITransactionPersistance {
    public void createClient(ClientEntity c) ;
    public void updateClientBalance(ClientEntity c,Double balance) ;

    public int addStock(StockItemEntity s) ;
    public void addFilm(FilmEntity f) ;
    public void addJeux(JeuxEntity j) ;

    public void addRentedItem(RentedItemEntity r) ;
    public void deleteRentedItem(RentedItemEntity r) ;


}
