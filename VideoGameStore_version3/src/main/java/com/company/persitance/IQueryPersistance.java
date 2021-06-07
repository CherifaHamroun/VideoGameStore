package com.company.persitance;

import com.company.metier.ClientEntity;
import com.company.metier.RentedItemEntity;
import com.company.metier.StockItemEntity;

import java.util.List;

public interface IQueryPersistance {
    public ClientEntity findCustomerByName(String name);
    public StockItemEntity findStockByTitle(String name);
    public RentedItemEntity findRentedItem(int customer,int item);
    public List<ClientEntity> getClients();
    public List<StockItemEntity> getItems();
    public String getTypeParam(int id);
    public String isCheckedOut(int id);
    public List<RentedItemEntity> overdueItems();
    public StockItemEntity findItemById(int id);
    public List<Integer> findFilmByActor(String actor);
}
