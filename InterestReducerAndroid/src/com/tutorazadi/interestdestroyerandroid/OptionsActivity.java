package com.tutorazadi.interestdestroyerandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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

	Button button1, button2, button3, button4, button5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));
		
		final Bundle extra = getIntent().getExtras();
		
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		
		Animation fly_in_bottom = AnimationUtils.loadAnimation(this, R.anim.fly_in_bottom);
		Animation fly_in_top = AnimationUtils.loadAnimation(this, R.anim.fly_in_top);
		Animation fly_in_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_left);
		Animation fly_in_right = AnimationUtils.loadAnimation(this, R.anim.fly_in_right);
		Animation fly_in_diagonal_top_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_diagonal_top_left);
		
		button1.startAnimation(fly_in_bottom);
		button2.startAnimation(fly_in_top);
		button3.startAnimation(fly_in_left);
		button4.startAnimation(fly_in_right);
		button5.startAnimation(fly_in_diagonal_top_left);
		
		button1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToChartActivity = new Intent(OptionsActivity.this, ExtraPaymentChartActivity.class);
				goToChartActivity.putExtras(extra);
				startActivity(goToChartActivity);
			}
		});
		
		button2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent goToAmortization = new Intent(OptionsActivity.this, AmortizationActivity.class);
				goToAmortization.putExtras(extra);
				startActivity(goToAmortization);
			}
		});
		
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
		
		button4.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Create new activity which is similar to ExtraPaymentActivity but instead shows effect of 1 and 2
				// interest points bought down in comparison to regular amount.
				Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_LONG).show();
			}
		});
		
		button5.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// Add Facebook sharing logic.
				Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_LONG).show();
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
