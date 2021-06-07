package com.company.metier;

import com.company.persitance.IQueryPersistance;
import com.company.persitance.QueryPersistance;

import java.util.List;

public class QueryMetier implements IQueryMetier{
    IQueryPersistance qp = new QueryPersistance();
    @Override
    public List<ClientEntity> getClients() {
        return qp.getClients();
    }
    public List<StockItemEntity> getItems() {
        return qp.getItems();
    }
    public String  getTypeParam(int id) {
        return qp.getTypeParam(id);
    }

    @Override
    public String isCheckedOut(int id) {
        return qp.isCheckedOut(id);
    }

    @Override
    public List<RentedItemEntity> overdueItems() {
        return qp.overdueItems();
    }

    @Override
    public StockItemEntity findItemById(int id) {
        return qp.findItemById(id);
    }

    @Override
    public StockItemEntity findItemByTitle(String title) {
        return qp.findStockByTitle(title);
    }

    @Override
    public List<Integer> findByActor(String actor) {
        return qp.findFilmByActor(actor);
    }
}
