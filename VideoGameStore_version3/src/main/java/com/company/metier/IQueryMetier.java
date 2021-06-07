package com.company.metier;

import java.util.List;

public interface IQueryMetier {
    public List<ClientEntity> getClients();
    public List<StockItemEntity> getItems();
    public String getTypeParam(int id);
    public String isCheckedOut(int id);
    public List<RentedItemEntity> overdueItems();
    public StockItemEntity findItemById(int id);
    public StockItemEntity findItemByTitle(String title);
    public List<Integer> findByActor(String actor);
}
