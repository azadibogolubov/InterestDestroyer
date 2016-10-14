package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsFragment extends Fragment {

    @Bind(R.id.savingsAmount) TextView savingsAmount;
    @Bind(R.id.savingsTime) TextView savingsTime;

    Double interestSaved, timeSaved;

    private static DecimalFormat df = new DecimalFormat("#.##");

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        Bundle extras = getActivity().getIntent().getExtras();
        ButterKnife.bind(this, view);

        interestSaved = extras.getDouble("INTEREST_SAVED");
        timeSaved = extras.getDouble("TIME_SAVED");

        savingsAmount.setText("$" + df.format(interestSaved).toString() + " in interest,");
        savingsTime.setText("and " + df.format(timeSaved).toString() + " years of payments.");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
