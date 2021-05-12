package com.company;

public class Jeux extends StockItem {
    private String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Jeux(int itemID, float rentalPrice, String title, String platform) {
        super(itemID, rentalPrice, title);
        this.platform = platform;
    }
    
    public String toString() {
		return "ID: " +this.getItemID()+", Titre: "+this.getTitle() + ", Rental price: "+this.getRentalPrice()+" Plateforme: "+ this.getPlatform();
	}
}
