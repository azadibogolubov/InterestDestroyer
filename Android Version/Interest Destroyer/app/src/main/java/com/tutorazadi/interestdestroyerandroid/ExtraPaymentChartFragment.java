package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExtraPaymentChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExtraPaymentChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExtraPaymentChartFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
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
    private Activity context;

    public ExtraPaymentChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExtraPaymentChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExtraPaymentChartFragment newInstance(String param1, String param2) {
        ExtraPaymentChartFragment fragment = new ExtraPaymentChartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RelativeLayout rootLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_extra_payment_chart, container, false);
        context = getActivity();

        // Inflate the layout for this fragment
        Bundle extras = context.getIntent().getExtras();


        arimoItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Arimo-Italic.ttf");

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

        yearsSavedLbl = (TextView) rootLayout.findViewById(R.id.yearsSavedLbl);
        yearsSavedLbl.setTypeface(arimoItalic);
        yearsSavedLbl.setText(yearsSavedLbl.getText() + String.valueOf(df.format(extras.getDouble("TIME_SAVED"))));

        interestSavedLbl = (TextView) rootLayout.findViewById(R.id.interestSavedLbl);
        interestSavedLbl.setTypeface(arimoItalic);
        interestSavedLbl.setText(interestSavedLbl.getText() + String.valueOf(n.format(extras.getDouble("INTEREST_SAVED"))));

        chart = (BarChart) rootLayout.findViewById(R.id.chart);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.setDescription("");

        // if more than 60 entries are displayed in the chart, no values will be drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);

        mTf = Typeface.createFromAsset(context.getAssets(), "fonts/Arimo-Regular.ttf");

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
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
        set1.setColor(getResources().getColor(R.color.minimum_payment_bar));

        BarDataSet set2 = new BarDataSet(yVals2, "Extra Payment");
        set2.setBarSpacePercent(5f);
        set2.setColor(getResources().getColor(R.color.extra_payment_bar));

        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(xVals, dataSets);

        chart.setData(data);
    }
}
