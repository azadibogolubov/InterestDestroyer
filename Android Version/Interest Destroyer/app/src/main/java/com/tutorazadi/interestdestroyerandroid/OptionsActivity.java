package com.tutorazadi.interestdestroyerandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class OptionsActivity extends Activity {

	Button amortizeBtn, emailAmortizeBtn, graphicalResultsBtn, graphicalBuyDownBtn, shareOnFacebookBtn, emailResults;
    public static double[] extraPayment, minimumPayment, min_principal_remaining, extra_principal_remaining;
    public double timeSaved, interestSaved;
    public String principal, extraPaymentAmount, interest;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));
		
		final Bundle extra = getIntent().getExtras();
        final int SIZE = extra.getInt("TOTAL_MONTHS");

        minimumPayment = extra.getDoubleArray("MINIMUM_PAYMENTS");
        extraPayment = extra.getDoubleArray("EXTRA_PAYMENTS");
        principal = extra.getString("PRINCIPAL");
        interestSaved = extra.getDouble("INTEREST_SAVED");
        timeSaved = extra.getDouble("TIME_SAVED");
        interest = extra.getString("INTEREST_RATE");
        extraPaymentAmount = extra.getString("EXTRA_PAYMENT_AMOUNT");
        min_principal_remaining = extra.getDoubleArray("MIN_PRINCIPAL_REMAINING");
        extra_principal_remaining = extra.getDoubleArray("EXTRA_PRINCIPAL_REMAINING");


		amortizeBtn = (Button) findViewById(R.id.amortizeBtn);
		emailAmortizeBtn = (Button) findViewById(R.id.emailAmortizeBtn);
		graphicalResultsBtn = (Button) findViewById(R.id.graphicalResultsBtn);
		graphicalBuyDownBtn = (Button) findViewById(R.id.graphicalBuyDownBtn);
		shareOnFacebookBtn = (Button) findViewById(R.id.shareOnFacebookBtn);
		
		Animation fly_in_bottom = AnimationUtils.loadAnimation(this, R.anim.fly_in_bottom);
		Animation fly_in_top = AnimationUtils.loadAnimation(this, R.anim.fly_in_top);
		Animation fly_in_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_left);
		Animation fly_in_right = AnimationUtils.loadAnimation(this, R.anim.fly_in_right);
		Animation fly_in_diagonal_top_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_diagonal_top_left);
		
		amortizeBtn.startAnimation(fly_in_bottom);
		emailAmortizeBtn.startAnimation(fly_in_top);
		graphicalResultsBtn.startAnimation(fly_in_left);
		graphicalBuyDownBtn.startAnimation(fly_in_right);
		shareOnFacebookBtn.startAnimation(fly_in_diagonal_top_left);
		
		amortizeBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToAmortization = new Intent(OptionsActivity.this, AmortizationActivity.class);
				goToAmortization.putExtras(extra);
				startActivity(goToAmortization);
			}
		});
		
		emailAmortizeBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
			}
		});
		
		graphicalResultsBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToChartActivity = new Intent(OptionsActivity.this, ExtraPaymentChartActivity.class);
				goToChartActivity.putExtras(extra);
				startActivity(goToChartActivity);
			}
		});
		
		graphicalBuyDownBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Create new activity which is similar to ExtraPaymentActivity but instead shows effect of 1 and 2
				// interest points bought down in comparison to regular amount.
				Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_SHORT).show();
			}
		});
		
		shareOnFacebookBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Add Facebook sharing logic.
				Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
