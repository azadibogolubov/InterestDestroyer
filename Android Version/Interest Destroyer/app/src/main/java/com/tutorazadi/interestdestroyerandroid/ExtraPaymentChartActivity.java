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

/**
 * @todo Add my own Canvas based charting engine in here, as well as add Apache license file for fonts.
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/*import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;*/

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ExtraPaymentChartActivity extends Activity {

	/*GraphicalView mChartView = null;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
    public TextView interestSavedLbl, yearsSavedLbl;
	public static double[] extraPayment, minimumPayment, min_principal_remaining, extra_principal_remaining;
	public double timeSaved, interestSaved, totalMonths;
	public String principal, extraPaymentAmount, interest;

    public Typeface arimoItalic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_payment_chart);

		ActionBar bar = getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));
		
		Bundle extras = getIntent().getExtras();

        arimoItalic = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Italic.ttf");

		minimumPayment = extras.getDoubleArray("MINIMUM_PAYMENTS");
		extraPayment = extras.getDoubleArray("EXTRA_PAYMENTS");
        min_principal_remaining = extras.getDoubleArray("MIN_PRINCIPAL_REMAINING");
        extra_principal_remaining = extras.getDoubleArray("EXTRA_PRINCIPAL_REMAINING");

		principal = extras.getString("PRINCIPAL");
		interestSaved = extras.getDouble("INTEREST_SAVED");
		timeSaved = extras.getDouble("TIME_SAVED");
		interest = extras.getString("INTEREST_RATE");
		extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

		int size = (int)extras.getDouble("TOTAL_MONTHS");
		String[] mMonth = new String[size/12];
		int[] x = new int[size];
		int b = 0;
		for (int i = 0; i < size; i+=12)
		{
            try {
                mMonth[b] = String.valueOf(b + 1);
                x[b] = (b + 1);
                b++;
            }
            catch (ArrayIndexOutOfBoundsException e)  { break ;}
		}

		yearsSavedLbl = (TextView) findViewById(R.id.yearsSavedLbl);
        yearsSavedLbl.setTypeface(arimoItalic);
        yearsSavedLbl.setText(yearsSavedLbl.getText() + String.valueOf(df.format(extras.getDouble("TIME_SAVED"))));

        interestSavedLbl = (TextView) findViewById(R.id.interestSavedLbl);
        interestSavedLbl.setTypeface(arimoItalic);
        interestSavedLbl.setText(interestSavedLbl.getText() + String.valueOf(n.format(extras.getDouble("INTEREST_SAVED"))));

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        float val = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, metrics);
        
        XYSeries baseSeries = new XYSeries("Base");
        XYSeries extraPaymentSeries = new XYSeries("Extra Payment");
        
        int c = 0;
        for(int i=0;i<size;i+=12)
        {
            baseSeries.add(c,min_principal_remaining[i] / 100);
            extraPaymentSeries.add(c,extra_principal_remaining[i] / 100);
            c++;
        }
 
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(baseSeries);
        dataset.addSeries(extraPaymentSeries);
 
        XYSeriesRenderer baseRenderer = new XYSeriesRenderer();
        baseRenderer.setGradientEnabled(true);
        baseRenderer.setGradientStart(0, Color.rgb(255, 255, 255));
        baseRenderer.setGradientStop(1000, Color.rgb(0, 0, 255));       
        baseRenderer.setFillPoints(true);
        baseRenderer.setLineWidth(5);
        baseRenderer.setDisplayChartValues(false);

        XYSeriesRenderer extraPaymentRenderer = new XYSeriesRenderer();
        extraPaymentRenderer.setGradientEnabled(true);
        extraPaymentRenderer.setGradientStart(0, Color.rgb(255, 255, 255));
        extraPaymentRenderer.setGradientStop(1000, Color.rgb(0, 255, 0));
        extraPaymentRenderer.setFillPoints(true);
        extraPaymentRenderer.setLineWidth(5);
        extraPaymentRenderer.setColor(Color.GREEN);
        extraPaymentRenderer.setDisplayChartValues(false);
        
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setYLabels(0);
        multiRenderer.setXTitle("Year #");
        multiRenderer.setYTitle("Principal remaining");
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setLegendTextSize(24);
        multiRenderer.setXAxisMin(-5.0f);
        multiRenderer.setXAxisMax(10.0f);
        multiRenderer.setYAxisMin(0.0f);
        multiRenderer.setYAxisMax(1000.0f);
        multiRenderer.setAxisTitleTextSize(val);
        multiRenderer.setLabelsColor(Color.GREEN);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.BLACK);
        multiRenderer.setMarginsColor(Color.BLACK);
        multiRenderer.setPanEnabled(true,  false);
        
        for(int i=0; i< size/12;i++)
            multiRenderer.addXTextLabel(i, mMonth[i]);
        
        multiRenderer.addSeriesRenderer(baseRenderer);
        multiRenderer.addSeriesRenderer(extraPaymentRenderer);
        
        mChartView = ChartFactory.getBarChartView(this, dataset, multiRenderer, BarChart.Type.DEFAULT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		mChartView.setLayoutParams(params);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.chartsRelativeLayout);
		layout.addView(mChartView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.extra_payment_chart, menu);
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
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}*/
}