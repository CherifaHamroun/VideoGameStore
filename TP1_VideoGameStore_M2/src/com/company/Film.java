package com.company;

public class Film extends StockItem {
    private String actor;

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Film(int itemID, float rentalPrice, String title, String actor) {
        super(itemID, rentalPrice, title);
        this.actor = actor;
    }

    public String getActor(){
        return this.actor;
    }
    
    public String toString() {
		return "ID: " +this.getItemID()+", Titre: "+this.getTitle() + ", Rental price: "+this.getRentalPrice()+" Acteur: "+ this.getActor();
	}
}
