package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsFragment extends Fragment {

    @Bind(R.id.savingsAmount) TextView savingsAmount;
    @Bind(R.id.savingsTime) TextView savingsTime;
    @Bind(R.id.chart) PieChart pieChart;

    Double interestSaved, timeSaved;

    PieChart chart;

    private static DecimalFormat df = new DecimalFormat("#.##");

    public ResultsFragment() {}

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

        interestSaved = Item.interestSaved;
        timeSaved = Item.timeSaved;

        savingsAmount.setText("$" + df.format(interestSaved) + " in interest,");
        savingsTime.setText("and " + df.format(timeSaved) + " years of payments.");

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(DonutVariables.PRINCIPAL, 0));
        entries.add(new Entry(DonutVariables.INTEREST, 1));

        PieDataSet dataset = new PieDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();
        labels.add("Principal");
        labels.add("Interest");

        PieData data = new PieData(labels, dataset);
        int[] colorList = { ContextCompat.getColor(getContext(), R.color.minimum_payment_bar), ContextCompat.getColor(getContext(), R.color.extra_payment_bar) };
        dataset.setColors(colorList);

        pieChart.setData(data);
        pieChart.animateY(1000);
        pieChart.setDescription("");
        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image
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
