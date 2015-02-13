package com.tutorazadi.interestdestroyerandroid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static double rate;
	public static double principal, principal_original;
	public static double time;
	public static double payment_amount;
	public static double extra_payment;
	public static double simple_interest;
	public static double compound_interest;
	public static double original_interest;
	public static double net_interest;
	public double interestSaved;
	public static double principal_paid;
	public static int payoff_years;
	public static double payoff_months;
	
	public static double[] extra_payments, minimum_payments;
	public static double interest_paid = original_interest = 0.00f;
	public static double timeSaved;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");

	Button getInfo;
	EditText principalTxt, interestTxt, monthsTxt, extraPaymentTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getInfo = (Button)findViewById(R.id.getInfo);
		
		principalTxt = (EditText)findViewById(R.id.principalTxt);
		principalTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		monthsTxt = (EditText)findViewById(R.id.monthsTxt);
		monthsTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		interestTxt = (EditText)findViewById(R.id.interestTxt);
		interestTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		extraPaymentTxt = (EditText)findViewById(R.id.extraPaymentTxt);
		extraPaymentTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		
		getInfo.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) {
        		principal = principal_original;
        		simple_interest = principal * (rate / 100) * (time / 12);
        		interest_paid = 0.00;
        		net_interest = 0.00;
        		principal_paid = 0.00;
        		payment_amount = amortize(principal, rate, time);

        		try
        		{
        			if (principalTxt.getText().length() < 4)
        			{
        				Toast.makeText(MainActivity.this, "Minimum amount for principal must be greater than $1000.", Toast.LENGTH_LONG).show();
        				return;
        			}
        			else if (monthsTxt.getText().length() < 1)
        			{
        				Toast.makeText(MainActivity.this, "Minimum number of months must be greater than 0.", Toast.LENGTH_LONG).show();
        				return;        				
        			}
        			else if (interestTxt.getText().length() < 1)
        			{
        				Toast.makeText(MainActivity.this, "Minimum interest rate must be greater than 0%.", Toast.LENGTH_LONG).show();
        				return;
        			}
        			principal_original = principal = Double.parseDouble(principalTxt.getText().toString());
        			time = Double.parseDouble(monthsTxt.getText().toString()); 
        			rate = Double.parseDouble(interestTxt.getText().toString()); 
        			extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString()); 
        		}
        		catch (NumberFormatException e)
        		{
        			Toast.makeText(MainActivity.this, "You have entered invalid data.", Toast.LENGTH_LONG).show();
        		}
                calculate(v);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void calculate(View v)
	{		
		extra_payments = new double[(int)time];
		minimum_payments = new double[(int)time];
		for (int i = 0; i < extra_payments.length; i++)
		{
			minimum_payments[i] = 0.00f;
			extra_payments[i] = 0.00f;
		}

		payment_amount = amortize(principal, rate, time);
		for (int i = 0; i < time; i++)
        {
                compound_interest = principal * (1 + (rate / 1200)) - principal;
                if (compound_interest <= 0)
                        break;
                interest_paid += compound_interest;
                principal_paid = payment_amount - compound_interest;
                principal -= principal_paid;
        }		

		original_interest = interest_paid;
		
		principal_original = principal = Double.parseDouble(principalTxt.getText().toString());
		time = Double.parseDouble(monthsTxt.getText().toString()); 
		rate = Double.parseDouble(interestTxt.getText().toString()); 
		extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString()); 

		interest_paid = compound_interest = principal_paid = simple_interest = 0;
        payment_amount = amortize(principal, rate, time);
        System.out.println("Monthly payment amount: " + payment_amount);
        for (int i = 0; i < time; i++)
        {
                compound_interest = principal * (1 + (rate / 1200)) - principal;
                if (compound_interest <= 0)
                {
                	payoff_months = i;
                    break;
                }
                interest_paid += compound_interest;
                principal_paid = (payment_amount + extra_payment) - compound_interest;
                principal -= principal_paid;
        }
        timeSaved = (time - payoff_months) / 12;
        interestSaved = original_interest - interest_paid;
		
		Intent goToResultsChart = new Intent(this, ExtraPaymentChartActivity.class);
		Bundle extra = new Bundle();
		extra.putDouble("TIME_SAVED", timeSaved);
		extra.putDouble("INTEREST_SAVED", interestSaved);
		extra.putDouble("TOTAL_MONTHS", time);
		extra.putDoubleArray("EXTRA_PAYMENTS", extra_payments);
		extra.putDoubleArray("MINIMUM_PAYMENTS", minimum_payments);
		extra.putString("PRINCIPAL", principalTxt.getText().toString());
		extra.putString("INTEREST_RATE",interestTxt.getText().toString());
		extra.putString("EXTRA_PAYMENT_AMOUNT", extraPaymentTxt.getText().toString());
		goToResultsChart.putExtras(extra);
		Intent goToOptions = new Intent(this, OptionsActivity.class);
		goToOptions.putExtras(extra);
		startActivity(goToOptions);
//		startActivity(goToResultsChart);
	}
	
	public static double amortize(double principal, double rate, double time)
	{
		rate /= 1200;
        return (principal * rate * Math.pow((1 + rate), time))/(Math.pow((1+rate),time)-1);	}
}