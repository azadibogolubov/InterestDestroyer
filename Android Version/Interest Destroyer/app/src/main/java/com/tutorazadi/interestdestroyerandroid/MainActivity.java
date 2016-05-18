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

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button graphical, amortize, email, extra, customize;
    ScrollView mainLayout;
    FloatingActionButton mainFab, amortizeFab, graphicalResultsFab, sendResultsViaEmailFab;
    PropertyValuesHolder animAmortize;
    boolean animationShown;
    Runnable beforeShowAmortizeFab, afterHideAmortizeFab, beforeShowSendResultsViaEmailFab, afterHideSendResultsViaEmailFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Change back to activity_main to return this to functional state.
        setContentView(R.layout.activity_main_new);
        initializeControls();
        initializeFabColors();
        initializeButtons();
        intent = new Intent(MainActivity.this, InfoGatheringActivity.class);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Change the theme...
            int buttonColor = data.getIntExtra("BUTTON_COLOR", -1);
            int backgroundColor = data.getIntExtra("BACKGROUND_COLOR", -1);
            Drawable btnColor = getResources().getDrawable(buttonColor);
            Drawable bgColor = getResources().getDrawable(backgroundColor);

            amortize.setBackground(btnColor);
            graphical.setBackground(btnColor);
            email.setBackground(btnColor);
            extra.setBackground(btnColor);
            customize.setBackground(btnColor);

            mainLayout.setBackground(bgColor);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {


        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void amortize(View v) {
        intent.putExtra("ACTION", "amortize");
        startActivity(intent);
    }

    public void showGraphicalResults(View v) {
        intent.putExtra("ACTION", "graphical");
        startActivity(intent);
    }

    public void sendResults(View v) {
        intent.putExtra("ACTION", "email");
        startActivity(intent);
    }

    public void extraPayment(View v) {
        intent.putExtra("ACTION", "extra");
        startActivity(intent);
    }

    public void customize(View v) {
        Intent customizeIntent = new Intent(MainActivity.this, SkinsActivity.class);
        startActivityForResult(customizeIntent, 1);
    }
    public void initializeMainFabListener() {
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animationShown) {
                    amortizeFab.animate().translationYBy(-200f).setDuration(100).withStartAction(new FabRunnable(amortizeFab, true));
                    graphicalResultsFab.animate().translationYBy(-400f).setDuration(100).withStartAction(new FabRunnable(graphicalResultsFab, true));
                    sendResultsViaEmailFab.animate().translationYBy(-600f).setDuration(100).withStartAction(new FabRunnable(sendResultsViaEmailFab, true));
                    animationShown = true;
                } else {
                    amortizeFab.animate().translationYBy(200f).setDuration(100).withEndAction(new FabRunnable(amortizeFab, false));
                    graphicalResultsFab.animate().translationYBy(400f).setDuration(100).withEndAction(new FabRunnable(graphicalResultsFab, false));
                    sendResultsViaEmailFab.animate().translationYBy(600f).setDuration(100).withEndAction(new FabRunnable(sendResultsViaEmailFab, false));
                    animationShown = false;
                }
            }
        });
    }

    public void initializeControls() {
        animationShown = false;
        mainLayout = (ScrollView) findViewById(R.id.mainLayout);

        graphical = (Button) findViewById(R.id.graphical);
        amortize = (Button) findViewById(R.id.amortize);
        email = (Button) findViewById(R.id.email);
        extra = (Button) findViewById(R.id.extra);
        customize = (Button) findViewById(R.id.customize);
        mainFab = (FloatingActionButton) findViewById(R.id.mainFab);
        amortizeFab = (FloatingActionButton) findViewById(R.id.amortizationFab);
        graphicalResultsFab = (FloatingActionButton) findViewById(R.id.graphicalResultsFab);
        sendResultsViaEmailFab = (FloatingActionButton) findViewById(R.id.sendResultsViaEmailFab);
    }

    // TODO: Update these to material design compatible colors
    public void initializeFabColors() {
        amortizeFab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#FF0000")));
        graphicalResultsFab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#00FF00")));
        sendResultsViaEmailFab.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#0000FF")));
    }

    public void initializeButtons() {
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeMainFabListener();
            }
        });

        amortizeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Do something with amortization", Toast.LENGTH_LONG).show();
            }
        });
        graphicalResultsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Do something with graphical results", Toast.LENGTH_LONG).show();
            }
        });

        sendResultsViaEmailFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Do something with send results via EMAIL", Toast.LENGTH_LONG).show();
            }
        });
    }
}