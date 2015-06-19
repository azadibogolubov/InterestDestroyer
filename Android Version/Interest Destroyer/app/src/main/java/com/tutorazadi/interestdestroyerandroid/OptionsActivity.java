/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OptionsActivity extends Activity {

	Button amortizeBtn, emailAmortizeBtn, graphicalResultsBtn, aboutBtn, graphicalBuyDownBtn, shareOnFacebookBtn;
    public static double[] extraPayment, minimumPayment, min_principal_remaining, extra_principal_remaining;
    public double timeSaved, interestSaved;
    public String principal, extraPaymentAmount, interest;
    public Typeface arimo;

    Animation fly_in_bottom, fly_in_top, fly_in_left, fly_in_right, fly_in_diagonal_top_left;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));
		
		final Bundle extra = getIntent().getExtras();
        final int SIZE = extra.getInt("TOTAL_MONTHS");

        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");

        fly_in_bottom = AnimationUtils.loadAnimation(this, R.anim.fly_in_bottom);
        fly_in_top = AnimationUtils.loadAnimation(this, R.anim.fly_in_top);
        fly_in_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_left);
        fly_in_right = AnimationUtils.loadAnimation(this, R.anim.fly_in_right);
        fly_in_diagonal_top_left = AnimationUtils.loadAnimation(this, R.anim.fly_in_diagonal_top_left);

        minimumPayment = extra.getDoubleArray("MINIMUM_PAYMENTS");
        extraPayment = extra.getDoubleArray("EXTRA_PAYMENTS");

        min_principal_remaining = extra.getDoubleArray("MIN_PRINCIPAL_REMAINING");
        extra_principal_remaining = extra.getDoubleArray("EXTRA_PRINCIPAL_REMAINING");

        principal = extra.getString("PRINCIPAL");
        interestSaved = extra.getDouble("INTEREST_SAVED");
        timeSaved = extra.getDouble("TIME_SAVED");
        interest = extra.getString("INTEREST_RATE");
        extraPaymentAmount = extra.getString("EXTRA_PAYMENT_AMOUNT");

		amortizeBtn = (Button) findViewById(R.id.amortizeBtn);
        amortizeBtn.setTypeface(arimo);
        amortizeBtn.startAnimation(fly_in_bottom);
        amortizeBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent goToAmortization = new Intent(OptionsActivity.this, AmortizationActivity.class);
                goToAmortization.putExtras(extra);
                startActivity(goToAmortization);
            }
        });

        emailAmortizeBtn = (Button) findViewById(R.id.emailAmortizeBtn);
		emailAmortizeBtn.setTypeface(arimo);
        emailAmortizeBtn.startAnimation(fly_in_top);
        emailAmortizeBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Bundle extras = getIntent().getExtras();
                double[] minimum_payment = extras.getDoubleArray("MIN_PRINCIPAL_PAID");
                double[] extra_payment = extras.getDoubleArray("EXTRA_PRINCIPAL_PAID");
                principal = extras.getString("PRINCIPAL");
                interest = extras.getString("INTEREST_RATE");
                timeSaved = extras.getDouble("TIME_SAVED");
                interestSaved = extras.getDouble("INTEREST_SAVED");
                extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

                final int SIZE = (int) extras.getDouble("TOTAL_MONTHS");
                String[] values = new String[SIZE];
                ArrayList<AmortizationItem> items = new ArrayList<AmortizationItem>();
                String message = "";

                for (int i = 0; i < SIZE; i++) {
                    values[i] = String.format("Month #:" + (i + 1) + "\n" + "Minimum payment: $%.2f", minimum_payment[i]) + "\n" + String.format("Extra payment: $%.2f", extra_payment[i]);
                    message += values[i] + "\n\n";
                }

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Interest Destroyer Amortization Results");
                intent.putExtra(Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        graphicalResultsBtn = (Button) findViewById(R.id.graphicalResultsBtn);
        graphicalResultsBtn.setTypeface(arimo);
        graphicalResultsBtn.startAnimation(fly_in_left);
        graphicalResultsBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent goToChartActivity = new Intent(OptionsActivity.this, ExtraPaymentChartActivity.class);
                goToChartActivity.putExtras(extra);
                startActivity(goToChartActivity);
            }
        });

        aboutBtn = (Button) findViewById(R.id.aboutBtn);
        aboutBtn.setTypeface(arimo);
        aboutBtn.startAnimation(fly_in_right);
        aboutBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                Intent goToAboutActivity = new Intent(OptionsActivity.this, AboutActivity.class);
                startActivity(goToAboutActivity);
            }
        });

/*		graphicalBuyDownBtn = (Button) findViewById(R.id.graphicalBuyDownBtn);
		graphicalBuyDownBtn.setTypeface(arimo);
        graphicalBuyDownBtn.startAnimation(fly_in_right);
        graphicalBuyDownBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                // Create new activity which is similar to ExtraPaymentActivity but instead shows effect of 1 and 2
                // interest points bought down in comparison to regular amount.
                Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_SHORT).show();
            }
        });

        shareOnFacebookBtn = (Button) findViewById(R.id.shareOnFacebookBtn);
		shareOnFacebookBtn.setTypeface(arimo);
        shareOnFacebookBtn.startAnimation(fly_in_diagonal_top_left);
        shareOnFacebookBtn.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                // Add Facebook sharing logic.
                Toast.makeText(OptionsActivity.this, "This functionality is not yet implemented...", Toast.LENGTH_SHORT).show();
            }
        });*/
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
