package com.tutorazadi.interestdestroyerandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AmortizationActivity extends Activity 
{
	Button emailResults;
	ListView listView;
	public double[] minimum_payment, extra_payment;
	public String principal, interest, extraPaymentAmount;
	public double totalMonths, timeSaved, interestSaved;
	
	String[] values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amortization);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));

		Bundle extras = getIntent().getExtras();
		minimum_payment = extras.getDoubleArray("MIN_PRINCIPAL_PAID");
		extra_payment = extras.getDoubleArray("EXTRA_PRINCIPAL_PAID");
		principal = extras.getString("PRINCIPAL");
		interest = extras.getString("INTEREST_RATE");
		timeSaved = extras.getDouble("TIME_SAVED");
		interestSaved = extras.getDouble("INTEREST_SAVED");
		extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

        listView = (ListView) findViewById(R.id.amortizationList);
        Toast.makeText(this, "Min: " + minimum_payment, Toast.LENGTH_LONG).show();        	
        
        Toast.makeText(this, "# Months: " + extras.getDouble("TOTAL_MONTHS"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this,  "Minimum payments: " + minimum_payment[0], Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Extra payments: " + extra_payment[0], Toast.LENGTH_SHORT).show();
        
        final int SIZE = (int) extras.getDouble("TOTAL_MONTHS");
        values = new String[SIZE];
        for (int i = 0; i < SIZE; i++)
        	values[i] = String.format("Month #:" + (i + 1) + "\n" + "Minimum payment: $%.2f", minimum_payment[i]) + "\n" + String.format("Extra payment: $%.2f", extra_payment[i]);
		final String[] results = values;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter); 

		emailResults = (Button) findViewById(R.id.emailResults);
		emailResults.setOnClickListener(new OnClickListener()
		{
			String bodyText = "Here are the amortization results for your loan "
					+ "with a principal amount of $" + principal + " assessed "
					+ "at an interest rate of " + interest + "% amortized over " 
					+ SIZE + " months, with an extra payment of $" 
					+ extraPaymentAmount + " per month.\n\n" + String.format("If you pay the extra payments "
					+ "as planned, you will save %.2f years and $%.2f over the term of your mortgage.\n\n", timeSaved, interestSaved);
			
			public void onClick(View v)
			{				
				for (int i = 0; i < SIZE; i++)
				{
					bodyText += results[i] + "\n";
				}
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setData(Uri.parse("mailto:"));
				emailIntent.setType("text/plain");
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Interest Destroyer Amortization Results");
				emailIntent.putExtra(Intent.EXTRA_TEXT, bodyText);
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.amortization, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}