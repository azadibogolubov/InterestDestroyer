package com.tutorazadi.interestdestroyerandroid;

/**
 * Created by azadi on 3/1/15.
 */
public class AmortizationItem {
    private String month;
    private String minPayment;
    private String extraPayment;

    public AmortizationItem(String month, String minPayment, String extraPayment)
    {
        this.month = month;
        this.minPayment = minPayment;
        this.extraPayment = extraPayment;
    }

    public void setMonth(String value)
    {
        month = value;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMinPayment(String value)
    {
        minPayment = value;
    }

    public String getMinPayment()
    {
        return minPayment;
    }

    public void setExtraPayment(String value)
    {
        extraPayment = value;
    }

    public String getExtraPayment()
    {
        return extraPayment;
    }
}
