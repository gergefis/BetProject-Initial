package com.eap.plh24;
public class PlatinumCustomer extends Customer{
    protected final int platinumCustomerMaxStake;

    public PlatinumCustomer(String platinumPlayerName){
        super(platinumPlayerName);
        platinumCustomerMaxStake = 2000;
    }

    @Override
    public int getMaxStake() {
        return platinumCustomerMaxStake;
    }
}

