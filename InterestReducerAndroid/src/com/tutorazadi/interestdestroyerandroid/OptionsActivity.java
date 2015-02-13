package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OptionsActivity extends Activity {

	Button button1, button2, button3, button4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		final Bundle extra = getIntent().getExtras();
		
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToChartActivity = new Intent(OptionsActivity.this, ExtraPaymentChartActivity.class);
				goToChartActivity.putExtras(extra);
				startActivity(goToChartActivity);
			}
		});
		
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToAmortization = new Intent(OptionsActivity.this, AmortizationActivity.class);
				goToAmortization.putExtras(extra);
				startActivity(goToAmortization);
			}
		});
		
		button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent emailIntent = new Intent(Intent.ACTION_SEND);
				emailIntent.setData(Uri.parse("mailto:"));
				emailIntent.setType("text/plain");
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Interest Destroyer Amortization Results");
				emailIntent.putExtra(Intent.EXTRA_TEXT, "");
				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});
		
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Create new activity which is similar to ExtraPaymentActivity but instead shows effect of 1 and 2
				// interest points bought down in comparison to regular amount.
			}
		});
		
		button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Add Facebook sharing logic.
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