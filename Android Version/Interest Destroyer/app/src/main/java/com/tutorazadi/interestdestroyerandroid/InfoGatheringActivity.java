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

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
    public static double payoff_months;

    public static double[] extra_payments, minimum_payments, min_principal_remaining, extra_principal_remaining;
    public static double interest_paid = original_interest = 0.00f;
    public static double timeSaved;
    public static double[] min_interest_paid, extra_interest_paid, min_principal_paid, extra_principal_paid;

    ImageView getInfo;
    EditText principalTxt, interestTxt, numMonthsTxt, extraPaymentTxt;

    CoordinatorLayout mainLayout;

    public Typeface arimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gathering);

        initializeControls();
        setClickListeners();
    }

    public void initializeControls()
    {
        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");

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

        mainLayout = (CoordinatorLayout) findViewById(R.id.mainLayout);

        getInfo = (ImageView) findViewById(R.id.getInfo);
    }

    public void setClickListeners() {
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
                        Snackbar.make(mainLayout, "Minimum amount for principal must be greater than $1000", Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                    else if (interestTxt.getText().length() < 1) {
                        Snackbar.make(mainLayout, "Minimum interest rate must be greater than 0%.", Snackbar.LENGTH_SHORT).show();
                        return;
                    } else if (numMonthsTxt.getText().length() < 1) {
                        Snackbar.make(mainLayout, "Minimum number of months must be greater than 0.", Snackbar.LENGTH_SHORT).show();
                        return;
                    } else if (extraPaymentTxt.getText().length() < 1) {
                        Snackbar.make(mainLayout, "Please enter at least $0 for an extra payment amount.", Snackbar.LENGTH_SHORT).show();
                    }
                    principal_original = principal = Double.parseDouble(principalTxt.getText().toString());
                    time = Double.parseDouble(numMonthsTxt.getText().toString());
                    rate = Double.parseDouble(interestTxt.getText().toString());
                    extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString());
                    calculate(v);
                    Snackbar.make(mainLayout, "TODO: Implement logic to go to ViewPager activity to show amortization and results.", Snackbar.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Snackbar.make(mainLayout, "You have entered invalid data.", Snackbar.LENGTH_SHORT).show();
                }
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
    }

    public static double amortize(double principal, double rate, double time) {
        rate /= 1200;
        return (principal * rate * Math.pow((1 + rate), time)) / (Math.pow((1 + rate), time) - 1);
    }
}