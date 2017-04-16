package com.tutorazadi.interestdestroyerandroid;

public class IDQuery {
    private long id;
    private int principal;
    private double rate;
    private int numMonths;
    private int extraPayment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNumMonths() {
        return numMonths;
    }

    public void setNumMonths(int numMonths) {
        this.numMonths = numMonths;
    }

    public int getExtraPayment() {
        return extraPayment;
    }

    public void setExtraPayment(int extraPayment) {
        this.extraPayment = extraPayment;
    }
}
