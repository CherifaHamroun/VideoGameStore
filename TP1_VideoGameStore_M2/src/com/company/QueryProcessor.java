package com.company;

import com.company.Client;

import java.util.*;

public class QueryProcessor {
    private  Map<String, Client> mapcus;
    private Map<String, StockItem> mapstk;
    private List<RentedItem> listrented;
    
    public int FindByTitle(String titre){
        int found = 0;
        for (Map.Entry<String, StockItem> entry : mapstk.entrySet()) {
            if( entry.getValue().getTitle().equalsIgnoreCase(titre) ) { found = 1;break;}
        }
        return found;
    }
    public List<Film> ndByActor(String nomActeur){
        List<Film> mesfilms= new ArrayList<Film>();
        for (Map.Entry<String, StockItem> entry : mapstk.entrySet()) {
            System.out.println(entry.getValue().getClass().getSimpleName());
            if( entry.getValue().getClass().getSimpleName().equals("Film") ) {
                Film film = (Film) entry.getValue();
                if (film.getActor().equalsIgnoreCase(nomActeur)) {
                    mesfilms.add(film);
                }
            }
        }
        return mesfilms;
    }

    public QueryProcessor(Map<String, Client> mapcus, Map<String, StockItem> mapstk, List<RentedItem> listrented) {
        this.mapcus = mapcus;
        this.mapstk = mapstk;
        this.listrented = listrented;
    }

    public int IsCheckedOut(StockItem item){
        int found = 0;
        Iterator<RentedItem> rentediterator = listrented.iterator();
        while(rentediterator.hasNext()) {
            if (rentediterator.next().getItemID() == item.getItemID()){
                found = 1;break;
            }
        }
        return found;
    }
    public float Solde(String client){
        return mapcus.get(client).getAccountBalance();
    }
    public List<RentedItem> OverdueItems(){
        Date today = new Date();
        List<RentedItem> retard = new ArrayList<RentedItem>();
        Iterator<RentedItem> rentediterator = listrented.iterator();
        while(rentediterator.hasNext()) {
            RentedItem rentedcourant = rentediterator.next();
            if (rentedcourant.getDueDate().compareTo(today)<0){
                retard.add(rentedcourant);
            }
        }
        return retard;
    }
}
