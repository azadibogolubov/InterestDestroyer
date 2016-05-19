package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class ExtraPaymentChartActivity extends Activity {

    private Typeface mTf;
    BarChart chart;

    private static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    private static DecimalFormat df = new DecimalFormat("#.##");
    private TextView interestSavedLbl, yearsSavedLbl;
    private static double[]  min_principal_remaining, extra_principal_remaining;
    private static double extraPayment, minimumPayment;
    private double timeSaved, interestSaved, totalMonths;
    private String principal, extraPaymentAmount, interest;
    private Typeface arimoItalic;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_extra_payment_chart);

        Bundle extras = getIntent().getExtras();

        arimoItalic = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Italic.ttf");

        minimumPayment = extras.getDouble("MINIMUM_PAYMENTS");
        extraPayment = extras.getDouble("EXTRA_PAYMENTS");
        min_principal_remaining = extras.getDoubleArray("MIN_PRINCIPAL_REMAINING");
        extra_principal_remaining = extras.getDoubleArray("EXTRA_PRINCIPAL_REMAINING");

        principal = extras.getString("PRINCIPAL");
        interestSaved = extras.getDouble("INTEREST_SAVED");
        timeSaved = extras.getDouble("TIME_SAVED");
        interest = extras.getString("INTEREST_RATE");
        extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

        size = (int)extras.getDouble("TOTAL_MONTHS");
        String[] mMonth = new String[size/12];
        int[] x = new int[size];
        int b = 0;
        for (int i = 0; i < size; i += 12)
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

        chart = (BarChart) findViewById(R.id.chart);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        mTf = Typeface.createFromAsset(getAssets(), "fonts/Arimo-Regular.ttf");

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(8, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(8, false);
        rightAxis.setSpaceTop(15f);

        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        setData(size, 50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_extra_payment_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < count; i+=12) {
            xVals.add(String.valueOf(counter));
            counter++;
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<>();
        ArrayList<BarEntry> yVals2 = new ArrayList<>();
        counter = 0;

        for (int i = 0; i < count; i+=12) {
            yVals1.add(new BarEntry((float) min_principal_remaining[i], counter));
            yVals2.add(new BarEntry((float) extra_principal_remaining[i], counter));
            counter++;
        }

        BarDataSet set1 = new BarDataSet(yVals1, "Extra Year Number");
        set1.setBarSpacePercent(5f);
        set1.setColor(getResources().getColor(R.color.green_button));

        BarDataSet set2 = new BarDataSet(yVals2, "Minimum Year Number");
        set2.setBarSpacePercent(5f);
        set2.setColor(getResources().getColor(R.color.blue_button));

        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(xVals, dataSets);

        chart.setData(data);
    }
}
