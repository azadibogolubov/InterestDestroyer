package com.tutorazadi.interestdestroyerandroid;

/**
 *  Credits: Question Mark image taken from http://images.clipartpanda.com/
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
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
import android.widget.ImageButton;
import android.widget.Toast;
import android.graphics.Typeface;

public class AmortizationActivity extends Activity 
{
	Button emailResults;
	ListView listView;
	ImageButton informationBtn;
	TextView amortizationLbl, principalVsExtraLbl;
	public double[] minimum_payment, extra_payment;
	public String principal, interest, extraPaymentAmount;
	public double totalMonths, timeSaved, interestSaved;
	
	String[] values;

    Typeface arimo;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amortization);

        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");

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
        
        final int SIZE = (int) extras.getDouble("TOTAL_MONTHS");
        values = new String[SIZE];
        for (int i = 0; i < SIZE; i++)
        	values[i] = String.format("Month #:" + (i + 1) + "\n" + "Minimum payment: $%.2f", minimum_payment[i]) + "\n" + String.format("Extra payment: $%.2f", extra_payment[i]);
		final String[] results = values;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter); 

		informationBtn = (ImageButton) findViewById(R.id.informationBtn);
		informationBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(AmortizationActivity.this, "Give information about amortization here...", Toast.LENGTH_SHORT).show();
			}
		});

        amortizationLbl = (TextView) findViewById(R.id.amortizationLbl);
        amortizationLbl.setTypeface(arimo);

        principalVsExtraLbl = (TextView) findViewById(R.id.principalVsExtraLbl);
        principalVsExtraLbl.setTypeface(arimo);
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