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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Bind(R.id.homeLoan) LinearLayout homeLoan;
    @Bind(R.id.carLoan) LinearLayout carLoan;
    @Bind(R.id.studentLoan) LinearLayout studentLoan;

    boolean animationShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animationShown = false;

        intent = new Intent(MainActivity.this, InfoGatheringActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @OnClick(R.id.homeLoan)
    public void clickHomeLoan() {
        Intent intent = new Intent(MainActivity.this, InfoGatheringActivity.class);
        intent.putExtra("TYPE", "house");
        startActivity(intent);
    }

    @OnClick(R.id.carLoan)
    public void clickCarLoan() {
        Intent intent = new Intent(MainActivity.this, InfoGatheringActivity.class);
        intent.putExtra("TYPE", "car");
        startActivity(intent);
    }

    @OnClick(R.id.studentLoan)
    public void clickStudentLoan() {
        Intent intent = new Intent(MainActivity.this, InfoGatheringActivity.class);
        intent.putExtra("TYPE", "student");
        startActivity(intent);
    }
}