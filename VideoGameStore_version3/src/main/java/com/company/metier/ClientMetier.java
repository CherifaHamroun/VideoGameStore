package com.company.metier;

import com.company.persitance.ClientPersistance;

public interface ClientMetier {
    ClientPersistance cp = new ClientPersistance();
    public static void AddCustomer(String nom, Double solde) {
        ClientEntity cl = new ClientEntity();
        cl.setName(nom);
        cl.setAccountBalance(solde);
        cl.setCustomerId(4);
        cp.CreateClient(cl);
    }
}
