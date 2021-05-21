package com.company;

import java.util.Objects;

public class Client {
    private int customerID;
    private float accountBalance;
    private String name;

    public Client(int customerID, float accountBalance, String name) {
        this.customerID = customerID;
        this.accountBalance = accountBalance;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getCustomerID() == client.getCustomerID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerID());
    }

    public float getAccountBalance() {
        return this.accountBalance;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setAccountBalance(float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
    	return "ID: " + this.customerID+", Nom: "+this.name+", Balance: "+this.accountBalance;
    }
}
