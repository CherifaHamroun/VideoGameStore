package com.company;

import com.company.Client;
import com.company.Jeux;
import com.company.RentedItem;
import com.company.StockItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionProcessor {
    private Map<String, Client> mapcus=new HashMap<String, Client>();;
    private Map<String, StockItem> mapstk=new HashMap<String, StockItem>();
    private List<RentedItem> listrented=new ArrayList<>();

    public TransactionProcessor() {
        this.mapcus = new HashMap<String, Client>();
        this.mapstk = new HashMap<String, StockItem>();
        this.listrented = new ArrayList<RentedItem>();
    }

    public TransactionProcessor(Map<String, Client> mapcus,Map<String, StockItem> mapstk,List<RentedItem> listrented) {
    	this.mapcus = mapcus;
        this.mapstk = mapstk;
        this.listrented = listrented;
    }
    
    public void CheckOut(String custom, Date dueDate, String  item){
        Client customer = mapcus.get(custom);
        StockItem it = mapstk.get(item);
        RentedItem ri = new RentedItem(customer.getCustomerID(),it.getItemID(),dueDate);
        customer.setAccountBalance(customer.getAccountBalance() + it.getRentalPrice());
        listrented.add(ri);
    }
    public void CheckIn(String custom, int itemID ,String item){
        listrented.removeIf( T -> (T.getItemID() == itemID));
        Client customer = mapcus.get(custom);
        StockItem it = mapstk.get(item);
        customer.setAccountBalance(customer.getAccountBalance() - it.getRentalPrice());
    }
    public void AddCustomer( String nom ){
        if (!mapcus.containsKey(nom)){
            Client cl = new Client(mapcus.size()+1,0,nom);
            mapcus.put(nom,cl);
        }

    }
    public void AddStock( String titre, float rentalPrice, String param, int isFilm){
        if (!mapstk.containsKey(titre)){
            if (isFilm == 1) {
                Film fl = new Film(mapstk.size()+1,rentalPrice,titre,param);
                mapstk.put(titre,fl);
            }
            else {
                Jeux j = new Jeux(mapstk.size()+1,rentalPrice,titre,param );
                mapstk.put(titre,j);
            }

        }
    }
    
    public Map<String, Client> getClientList() {
		return this.mapcus;
	}
    public Map<String,StockItem> getStockList() {
		return this.mapstk;
	}
    public List<RentedItem> getRentedList() {
		return this.listrented;
	}
}
