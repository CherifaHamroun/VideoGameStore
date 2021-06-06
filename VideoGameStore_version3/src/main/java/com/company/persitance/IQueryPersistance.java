package com.company.persitance;

import com.company.metier.ClientEntity;
import com.company.metier.RentedItemEntity;
import com.company.metier.StockItemEntity;

public interface IQueryPersistance {
    public ClientEntity findCustomerByName(String name);
    public StockItemEntity findStockByTitle(String name);
    public RentedItemEntity findRentedItem(int customer,int item);

}
