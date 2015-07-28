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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoGatheringActivity extends Activity {

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

    public static double[] extra_payments, minimum_payments, min_principal_remaining, extra_principal_remaining;
    public static double interest_paid = original_interest = 0.00f;
    public static double timeSaved;
    public static double[] min_interest_paid, extra_interest_paid, min_principal_paid, extra_principal_paid;
    public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    public static DecimalFormat df = new DecimalFormat("#.##");

    ImageView getInfo;
    TextView welcomeLbl, principalLbl, interestRateLbl, numMonthsLbl, extraPaymentLbl;
    EditText principalTxt, interestTxt, numMonthsTxt, extraPaymentTxt;

    LinearLayout mainLayout;

    public Typeface arimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));

        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");

        welcomeLbl = (TextView) findViewById(R.id.welcomeLbl);
        welcomeLbl.setTypeface(arimo);

        principalLbl = (TextView) findViewById(R.id.principalLbl);
        principalLbl.setTypeface(arimo);

        interestRateLbl = (TextView) findViewById(R.id.interestRateLbl);
        interestRateLbl.setTypeface(arimo);

        numMonthsLbl = (TextView) findViewById(R.id.numMonthsLbl);
        numMonthsLbl.setTypeface(arimo);

        extraPaymentLbl = (TextView) findViewById(R.id.extraPaymentLbl);
        extraPaymentLbl.setTypeface(arimo);

        principalTxt = (EditText) findViewById(R.id.principalTxt);
        principalTxt.setTypeface(arimo);
        principalTxt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        numMonthsTxt = (EditText) findViewById(R.id.numMonthsTxt);
        numMonthsTxt.setTypeface(arimo);
        numMonthsTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        interestTxt = (EditText) findViewById(R.id.interestTxt);
        interestTxt.setTypeface(arimo);
        interestTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        extraPaymentTxt = (EditText) findViewById(R.id.extraPaymentTxt);
        extraPaymentTxt.setTypeface(arimo);
        extraPaymentTxt.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        mainLayout.startAnimation(fade_in);

        getInfo = (ImageView) findViewById(R.id.getInfo);
        getInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                principal = principal_original;
                simple_interest = principal * (rate / 100) * (time / 12);
                interest_paid = 0.00;
                net_interest = 0.00;
                principal_paid = 0.00;
                payment_amount = amortize(principal, rate, time);

                try {
                    if (principalTxt.getText().length() < 4) {
                        Toast.makeText(MainActivity.this, "Minimum amount for principal must be greater than $1000.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (numMonthsTxt.getText().length() < 1) {
                        Toast.makeText(MainActivity.this, "Minimum number of months must be greater than 0.", Toast.LENGTH_LONG).show();
                        return;
                    } else if (interestTxt.getText().length() < 1) {
                        Toast.makeText(MainActivity.this, "Minimum interest rate must be greater than 0%.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    principal_original = principal = Double.parseDouble(principalTxt.getText().toString());
                    time = Double.parseDouble(numMonthsTxt.getText().toString());
                    rate = Double.parseDouble(interestTxt.getText().toString());
                    extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "You have entered invalid data.", Toast.LENGTH_LONG).show();
                }
                calculate(v);
            }
        });*/
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void calculate(View v) {
        extra_payments = new double[(int) time];
        minimum_payments = new double[(int) time];
        min_principal_paid = new double[(int) time];
        extra_principal_paid = new double[(int) time];
        min_interest_paid = new double[(int) time];
        extra_interest_paid = new double[(int) time];
        min_principal_remaining = new double[(int) time];
        extra_principal_remaining = new double[(int) time];

        for (int i = 0; i < extra_payments.length; i++) {
            minimum_payments[i] = 0.00f;
            extra_payments[i] = 0.00f;
        }

        payment_amount = amortize(principal, rate, time);
        for (int i = 0; i < time; i++) {
            compound_interest = principal * (1 + (rate / 1200)) - principal;
            if (compound_interest <= 0)
                break;
            interest_paid += compound_interest;
            min_interest_paid[i] = compound_interest;
            principal_paid = payment_amount - compound_interest;
            min_principal_paid[i] = principal_paid;
            min_principal_remaining[i] = principal;
            principal -= principal_paid;
        }

        original_interest = interest_paid;

        principal_original = principal = Double.parseDouble(principalTxt.getText().toString());
        time = Double.parseDouble(numMonthsTxt.getText().toString());
        rate = Double.parseDouble(interestTxt.getText().toString());
        extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString());

        interest_paid = compound_interest = principal_paid = simple_interest = 0;
        payment_amount = amortize(principal, rate, time);
        System.out.println("Monthly payment amount: " + payment_amount);
        for (int i = 0; i < time; i++) {
            compound_interest = principal * (1 + (rate / 1200)) - principal;
            if (compound_interest <= 0) {
                payoff_months = i;
                break;
            }
            interest_paid += compound_interest;
            extra_interest_paid[i] = interest_paid - extra_payment;
            principal_paid = (payment_amount + extra_payment) - compound_interest;
            extra_principal_paid[i] = principal_paid;
            extra_principal_remaining[i] = principal;
            principal -= principal_paid;
        }
        timeSaved = (time - payoff_months) / 12;
        interestSaved = original_interest - interest_paid;

        Bundle extra = new Bundle();
        extra.putDouble("TIME_SAVED", timeSaved);
        extra.putDouble("INTEREST_SAVED", interestSaved);
        extra.putDouble("TOTAL_MONTHS", time);
        extra.putDouble("EXTRA_PAYMENTS", extra_payment);
        extra.putDouble("MINIMUM_PAYMENTS", payment_amount);
        extra.putString("PRINCIPAL", principalTxt.getText().toString());
        extra.putString("INTEREST_RATE", interestTxt.getText().toString());
        extra.putString("EXTRA_PAYMENT_AMOUNT", extraPaymentTxt.getText().toString());
        extra.putDoubleArray("MIN_PRINCIPAL_PAID", min_principal_paid);
        extra.putDoubleArray("EXTRA_PRINCIPAL_PAID", extra_principal_paid);
        extra.putDoubleArray("MIN_INTEREST_PAID", min_interest_paid);
        extra.putDoubleArray("EXTRA_INTEREST_PAID", extra_interest_paid);
        extra.putDoubleArray("MIN_PRINCIPAL_REMAINING", min_principal_remaining);
        extra.putDoubleArray("EXTRA_PRINCIPAL_REMAINING", extra_principal_remaining);

        Intent goToOptions = new Intent(this, OptionsActivity.class);
        goToOptions.putExtras(extra);
        startActivity(goToOptions);
    }

    public static double amortize(double principal, double rate, double time) {
        rate /= 1200;
        return (principal * rate * Math.pow((1 + rate), time)) / (Math.pow((1 + rate), time) - 1);
    }
}