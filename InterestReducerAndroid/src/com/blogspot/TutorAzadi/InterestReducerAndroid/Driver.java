package com.blogspot.TutorAzadi.InterestReducerAndroid;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

class Driver 
{
	public static double rate;
	public static double principal;
	public static double time;
	public static double payment_amount;
	public static double simple_interest;
	public static double compound_interest;
	public static double total_interest = 0.00f;
	public static double principal_paid;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	
	public static double amortize(double principal, double rate, double time)
	{
		rate /= 1200;
		return principal * ((rate * Math.pow((1 + rate), time))/(Math.pow((1+rate),time)-1));
	}
}