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

import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button graphical, amortize, email, extra, customize;
    ScrollView mainLayout;
    LinearLayout homeLoan, carLoan, studentLoan;
    boolean animationShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initializeControls();
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

    public void initializeControls() {
        animationShown = false;
//        mainFab = (FloatingActionButton) findViewById(R.id.mainFab);
        homeLoan = (LinearLayout) findViewById(R.id.homeLoan);
        carLoan = (LinearLayout) findViewById(R.id.carLoan);
        studentLoan = (LinearLayout) findViewById(R.id.studentLoan);
    }

    public void initializeButtons() {
//        mainFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, InfoGatheringActivity.class));
//            }
//        });

        homeLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoGatheringActivity.class));
            }
        });

        carLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoGatheringActivity.class));
            }
        });

        studentLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoGatheringActivity.class));
            }
        });

    }
}