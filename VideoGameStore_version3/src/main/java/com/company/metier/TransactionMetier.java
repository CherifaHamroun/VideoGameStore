package com.company.metier;

import com.company.persitance.IQueryPersistance;
import com.company.persitance.ITransactionPersistance;
import com.company.persitance.QueryPersistance;
import com.company.persitance.TransactionPersistance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class TransactionMetier implements ITransactionMetier {
    ITransactionPersistance tp = new TransactionPersistance();
    IQueryPersistance qp = new QueryPersistance();
    public void addCustomer(String nom, Double solde) {
        ClientEntity cl = new ClientEntity();
        cl.setName(nom);
        cl.setAccountBalance(solde);
        tp.createClient(cl);
    }

    @Override

    public void addStock( String titre, double rentalPrice, String param, int isFilm){
            StockItemEntity stk = new StockItemEntity();
            stk.setTitle(titre);
            stk.setRentalPrice(rentalPrice);
            int id = tp.addStock(stk);
            if (isFilm == 1) {
                FilmEntity fl = new FilmEntity();
                fl.setActor(param);
                fl.setItemId(id);
                tp.addFilm(fl);
            }
            else {
                JeuxEntity j = new JeuxEntity();
                j.setPlatform(param);
                j.setItemId(id);
                tp.addJeux(j);
            }
    }

    @Override
    public void CheckOut(String customer, Date dueDate, String item) {
        ClientEntity c = qp.findCustomerByName(customer);
        StockItemEntity s = qp.findStockByTitle(item);
        RentedItemEntity ri = new RentedItemEntity();
        ri.setCustomerId(c.getCustomerID());
        java.sql.Date sqlDate = new java.sql.Date(dueDate.getTime());
        ri.setDueDate(sqlDate);
        ri.setItemId(s.getItemId());
        if (c.getAccountBalance() - s.getRentalPrice()>0) {
            tp.updateClientBalance(c,c.getAccountBalance() - s.getRentalPrice());
            tp.addRentedItem(ri);
        }
        else System.out.println("Solde Non Sufficient");
    }

    @Override
    public void CheckIn(String customer, String item) {
        StockItemEntity s = qp.findStockByTitle(item);
        ClientEntity c = qp.findCustomerByName(customer);
        RentedItemEntity r = qp.findRentedItem(c.getCustomerID(),s.getItemId());
        //System.out.println(r);
        tp.deleteRentedItem(r);
    }
}
