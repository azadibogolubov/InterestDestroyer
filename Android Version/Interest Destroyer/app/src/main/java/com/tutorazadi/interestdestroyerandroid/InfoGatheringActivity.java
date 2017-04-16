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

    public static double rate, principal_original, payment_amount, simple_interest;
    public static double compound_interest, original_interest = 0.00f, net_interest, principal_paid, payoff_months;
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
        Item.principal = principal_original;
        simple_interest = Item.principal * (rate / 100) * (Item.time / 12);
        Item.interest_paid = 0.00;
        net_interest = 0.00;
        principal_paid = 0.00;
        payment_amount = amortize(Item.principal, rate, Item.time);

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
            principal_original = Item.principal = Double.parseDouble(principalTxt.getText().toString());
            Item.time = Double.parseDouble(numMonthsTxt.getText().toString());
            rate = Double.parseDouble(interestTxt.getText().toString());
            Item.extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString());
            calculate();

            Item.total_months = Integer.parseInt(numMonthsTxt.getText().toString());
            DonutVariables.PRINCIPAL = (float) principal_original;
            DonutVariables.INTEREST = (float) Item.interest_paid;
            startActivity(new Intent(InfoGatheringActivity.this, ResultsActivity.class));
        } catch (NumberFormatException e) {
            Snackbar.make(mainLayout, "You have entered invalid data.", Snackbar.LENGTH_SHORT).show();
        }

    }

    public void calculate() {
        Item.extra_payments = new double[(int) Item.time];
        Item.minimum_payments = new double[(int) Item.time];
        min_principal_paid = new double[(int) Item.time];
        extra_principal_paid = new double[(int) Item.time];
        min_interest_paid = new double[(int) Item.time];
        extra_interest_paid = new double[(int) Item.time];
        Item.min_principal_remaining = new double[(int) Item.time];
        Item.extra_principal_remaining = new double[(int) Item.time];

        for (int i = 0; i < Item.extra_payments.length; i++) {
            Item.minimum_payments[i] = 0.00f;
            Item.extra_payments[i] = 0.00f;
        }

        payment_amount = amortize(Item.principal, rate, Item.time);
        for (int i = 0; i < Item.time; i++) {
            compound_interest = Item.principal * (1 + (rate / 1200)) - Item.principal;
            if (compound_interest <= 0)
                break;
            Item.interest_paid += compound_interest;
            min_interest_paid[i] = compound_interest;
            principal_paid = payment_amount - compound_interest;
            min_principal_paid[i] = principal_paid;
            Item.min_principal_remaining[i] = Item.principal;
            Item.principal -= principal_paid;
        }

        original_interest = Item.interest_paid;

        principal_original = Item.principal = Double.parseDouble(principalTxt.getText().toString());
        Item.time = Double.parseDouble(numMonthsTxt.getText().toString());
        rate = Double.parseDouble(interestTxt.getText().toString());
        Item.extra_payment = Double.parseDouble(extraPaymentTxt.getText().toString());

        Item.interest_paid = compound_interest = principal_paid = simple_interest = 0;
        payment_amount = amortize(Item.principal, rate, Item.time);

        for (int i = 0; i < Item.time; i++) {
            compound_interest = Item.principal * (1 + (rate / 1200)) - Item.principal;
            if (compound_interest <= 0) {
                payoff_months = i;
                break;
            }
            Item.interest_paid += compound_interest;
            extra_interest_paid[i] = Item.interest_paid - Item.extra_payment;
            principal_paid = (payment_amount + Item.extra_payment) - compound_interest;
            extra_principal_paid[i] = principal_paid;
            Item.extra_principal_remaining[i] = Item.principal;
            Item.principal -= principal_paid;
        }
        Item.timeSaved = (Item.time - payoff_months) / 12;
        Item.interestSaved = original_interest - Item.interest_paid;
    }

    public static double amortize(double principal, double rate, double time) {
        rate /= 1200;
        return (principal * rate * Math.pow((1 + rate), time)) / (Math.pow((1 + rate), Item.time) - 1);
    }
}