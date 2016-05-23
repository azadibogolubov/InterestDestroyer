package com.tutorazadi.interestdestroyerandroid;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class AmortizationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AmortizationFragment() {
        // Required empty public constructor
    }

    Button emailResults;
    ListView listView;
    ImageButton informationBtn;
    TextView amortizationLbl, principalVsExtraLbl;
    public double[] minimum_payment, extra_payment;
    public String principal, interest, extraPaymentAmount;
    public double totalMonths, timeSaved, interestSaved;

    String[] values;

    Typeface arimo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        arimo = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Arimo-Regular.ttf");

//        ActionBar bar = getActivity().getActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#066891")));

        Bundle extras = getActivity().getIntent().getExtras();
        minimum_payment = extras.getDoubleArray("MIN_PRINCIPAL_PAID");
        extra_payment = extras.getDoubleArray("EXTRA_PRINCIPAL_PAID");
        principal = extras.getString("PRINCIPAL");
        interest = extras.getString("INTEREST_RATE");
        timeSaved = extras.getDouble("TIME_SAVED");
        interestSaved = extras.getDouble("INTEREST_SAVED");
        extraPaymentAmount = extras.getString("EXTRA_PAYMENT_AMOUNT");

        listView = (ListView) getActivity().findViewById(R.id.amortizationList);

        final int SIZE = (int) extras.getDouble("TOTAL_MONTHS");
        values = new String[SIZE];
        String[] month = new String[SIZE];
        String[] minPayment = new String[SIZE];
        String[] extraPayment = new String[SIZE];
        ArrayList<AmortizationItem> items = new ArrayList<AmortizationItem>();

/*        for (int i = 0; i < SIZE; i++)
        {
            values[i] = String.format("Month #:" + (i + 1) + "\n" + "Minimum payment: $%.2f", minimum_payment[i]) + "\n" + String.format("Extra payment: $%.2f", extra_payment[i]);
            String[] temp = values[i].split("\n");
            month[i] = temp[0];
            minPayment[i] = temp[1];
            extraPayment[i] = temp[2];
            items.add(new AmortizationItem(month[i], minPayment[i], extraPayment[i]));
        }

        AmortizationItemAdapter adapter = new AmortizationItemAdapter(getActivity(), R.layout.item_amortization_list, items);
        listView.setAdapter(adapter);

        amortizationLbl = (TextView) getActivity().findViewById(R.id.amortizationLbl);
        amortizationLbl.setTypeface(arimo);

        principalVsExtraLbl = (TextView) getActivity().findViewById(R.id.principalVsExtraLbl);
        principalVsExtraLbl.setTypeface(arimo);*/
        return inflater.inflate(R.layout.fragment_amortization, container, false);
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
}
