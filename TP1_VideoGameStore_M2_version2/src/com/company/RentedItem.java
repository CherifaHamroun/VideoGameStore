package com.company;

import java.util.Date;

public class RentedItem {
    private int customerID;
    private int itemID;
    private Date dueDate;

    public int getCustomerID() {
        return customerID;
    }

    public int getItemID(){
        return this.itemID;
    }
    public Date getDueDate(){
        return this.dueDate;
    }
    public void setItemID( int itemID){
        this.itemID = itemID;
    }
    public void setDueDate( Date dueDate){
        this.dueDate = dueDate;
    }

    public RentedItem(int customerID, int itemID, Date dueDate) {
        this.customerID = customerID;
        this.itemID = itemID;
        this.dueDate = dueDate;
    }

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    
    public String toString() {
		return "ID: "+this.itemID+", Client: "+this.customerID+", Due date: "+this.dueDate;
	}
}
