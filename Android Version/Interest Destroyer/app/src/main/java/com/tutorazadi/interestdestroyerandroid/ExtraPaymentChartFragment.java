package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class ExtraPaymentChartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Typeface mTf;
    BarChart chart;

    private static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    private static DecimalFormat df = new DecimalFormat("#.##");
    private static double[] minimumPayment, extraPayment, min_principal_remaining, extra_principal_remaining;
    private static double timeSaved, interestSaved, totalMonths;
    private double principal, extraPaymentAmount, interest;
    private Typeface arimoItalic;
    private int size;

    @Bind(R.id.interestSavedLbl) TextView interestSavedLbl;
    @Bind(R.id.yearsSavedLbl) TextView yearsSavedLbl;

    public ExtraPaymentChartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExtraPaymentChartFragment newInstance(String param1, String param2) {
        ExtraPaymentChartFragment fragment = new ExtraPaymentChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_extra_payment_chart, container, false);
        ButterKnife.bind(this, rootLayout);

        // Inflate the layout for this fragment
        Bundle extras = getActivity().getIntent().getExtras();


        arimoItalic = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Arimo-Italic.ttf");

        minimumPayment = Item.minimum_payments;
        extraPayment = Item.extra_payments;
        min_principal_remaining = Item.min_principal_remaining;
        extra_principal_remaining = Item.extra_principal_remaining;

        principal = Item.principal;
        interestSaved = Item.interestSaved;
        timeSaved = Item.timeSaved;
        interest = Item.interest_paid;
        extraPaymentAmount = Item.extra_payment;

        size = Item.total_months;
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

        //yearsSavedLbl = (TextView) rootLayout.findViewById(R.id.yearsSavedLbl);
        yearsSavedLbl.setTypeface(arimoItalic);
        yearsSavedLbl.setText(yearsSavedLbl.getText() + String.valueOf(df.format(Item.timeSaved)));

        //interestSavedLbl = (TextView) rootLayout.findViewById(R.id.interestSavedLbl);
        interestSavedLbl.setTypeface(arimoItalic);
        interestSavedLbl.setText(interestSavedLbl.getText() + String.valueOf(n.format(Item.interestSaved)));

        chart = (BarChart) rootLayout.findViewById(R.id.chart);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        mTf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Arimo-Regular.ttf");

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

        return rootLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

        BarDataSet set1 = new BarDataSet(yVals1, "Minimum Payment");
        set1.setBarSpacePercent(5f);
        set1.setColor(ContextCompat.getColor(getActivity(), R.color.minimum_payment_bar));

        BarDataSet set2 = new BarDataSet(yVals2, "Extra Payment");
        set2.setBarSpacePercent(5f);
        set2.setColor(ContextCompat.getColor(getActivity(), R.color.extra_payment_bar));

        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(xVals, dataSets);

        chart.setData(data);
    }
}
