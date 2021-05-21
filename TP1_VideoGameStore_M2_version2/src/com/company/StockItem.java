package com.company;

import java.util.Objects;

public class StockItem {
    private int itemID;
    private float rentalPrice;
    private String title;

    public StockItem(int itemID, float rentalPrice, String title) {
        this.itemID = itemID;
        this.rentalPrice = rentalPrice;
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockItem)) return false;
        StockItem stockItem = (StockItem) o;
        return getItemID() == stockItem.getItemID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemID());
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getItemID(){
        return this.itemID;
    }
}
