package com.eap.plh24;

public class GoldCustomer extends Customer{

   protected final int goldCustomerMaxStake;

    //Δημιουργία κατασκευαστή υποκλάσης
    public GoldCustomer(String goldPlayerName) {
        super(goldPlayerName);
        goldCustomerMaxStake = 1000;
    }
@Override
    public int getMaxStake() {
        return goldCustomerMaxStake;
    }
}
