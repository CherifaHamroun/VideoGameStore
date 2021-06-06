package com.company.metier;

import com.company.persitance.ITransactionPersistance;
import com.company.persitance.TransactionPersistance;

import java.util.Date;

public interface ITransactionMetier {
    public void addCustomer(String nom, Double solde);
    public void addStock( String titre, double rentalPrice, String param, int isFilm);
    public void CheckOut(String customer, Date dueDate, String item);
    public void CheckIn(String customer,String item);

}
