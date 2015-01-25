package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
	public double[] minimum_payments;
	public double[] extra_payments;
	public String principal, interest, extraPaymentAmount;
	public double totalMonths, timeSaved, interestSaved;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amortization);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Bundle extras = getIntent().getExtras();
		minimum_payments = extras.getDoubleArray("MINIMUM_PAYMENTS");
		extra_payments = extras.getDoubleArray("EXTRA_PAYMENTS");
		principal = extras.getString("PRINCIPAL");
		interest = extras.getString("INTEREST_RATE");
		timeSaved = extras.getDouble("TIME_SAVED");
		interestSaved = extras.getDouble("INTEREST_SAVED");
		extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

		final int SIZE = minimum_payments.length;

        listView = (ListView) findViewById(R.id.listView1);

        String[] values = new String[SIZE]; 
        for (int i = 0; i < SIZE; i++)
        	values[i] = String.format("Month #:" + (i + 1) + "\n" + "Minimum payment: $%.2f", minimum_payments[i]) + "\n" + String.format("Extra payment: $%.2f", extra_payments[i]);
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