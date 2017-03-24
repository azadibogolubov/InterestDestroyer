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
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoGatheringActivity extends Activity {

    public static double rate, principal, principal_original, time, payment_amount, extra_payment, simple_interest;
    public static double compound_interest, original_interest, net_interest, principal_paid, payoff_months, timeSaved;
    public static double interest_paid = original_interest = 0.00f;

    public double interestSaved;

    public static double[] extra_payments, minimum_payments, min_principal_remaining, extra_principal_remaining;
    public static double[] min_interest_paid, extra_interest_paid, min_principal_paid, extra_principal_paid;

    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.principalTxt) EditText principalTxt;
    @Bind(R.id.interestTxt) EditText interestTxt;
    @Bind(R.id.numMonthsTxt) EditText numMonthsTxt;
    @Bind(R.id.extraPaymentTxt) EditText extraPaymentTxt;
    @Bind(R.id.mainLayout) CoordinatorLayout mainLayout;
    @Bind(R.id.icon) ImageView icon;

    public Typeface arimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_gathering);
//        setContentView(R.layout.results);
        ButterKnife.bind(this);
        initializeControls();
    }

    public void initializeControls() {
        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");

        List<EditText> fields = Arrays.asList(principalTxt, numMonthsTxt, interestTxt, extraPaymentTxt);

        for (EditText field: fields) {
            field.setTypeface(arimo);
            field.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }

    @OnClick(R.id.fab)
    void submit() {
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
            } else if (interestTxt.getText().length() < 1) {
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
            calculate();
            goToViewPager();
            Snackbar.make(mainLayout, "TODO: Implement logic to go to ViewPager activity to show amortization and results.", Snackbar.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Snackbar.make(mainLayout, "You have entered invalid data.", Snackbar.LENGTH_SHORT).show();
        }

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

    public void calculate() {
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

    public void goToViewPager() {
        Intent viewPagerIntent = new Intent(InfoGatheringActivity.this, ResultsActivity.class);
        viewPagerIntent.putExtra("MINIMUM_PAYMENTS", minimum_payments);
        viewPagerIntent.putExtra("EXTRA_PAYMENTS", extra_payments);
        viewPagerIntent.putExtra("MIN_PRINCIPAL_REMAINING", min_principal_remaining);
        viewPagerIntent.putExtra("EXTRA_PRINCIPAL_REMAINING", extra_principal_remaining);
        viewPagerIntent.putExtra("PRINCIPAL", principal);
        viewPagerIntent.putExtra("INTEREST_SAVED", interestSaved);
        viewPagerIntent.putExtra("TIME_SAVED", timeSaved);
        viewPagerIntent.putExtra("INTEREST_PAID", interest_paid);
        viewPagerIntent.putExtra("EXTRA_PAYMENT_AMOUNT", extra_payment);
        viewPagerIntent.putExtra("TOTAL_MONTHS", time);
        DonutVariables.PRINCIPAL = (float) principal_original;
        DonutVariables.INTEREST = (float) interest_paid;
        startActivity(viewPagerIntent);
    }
}