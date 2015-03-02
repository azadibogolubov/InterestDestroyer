package com.tutorazadi.interestdestroyerandroid;

import android.graphics.Typeface;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

public class AmortizationItemAdapter extends ArrayAdapter<AmortizationItem> {
    private ArrayList<AmortizationItem> objects;
    private Typeface arimoItalic;

    public AmortizationItemAdapter(Context context, int textViewResourceId, ArrayList<AmortizationItem> objects) {
        super(context, textViewResourceId, objects);
        arimoItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Arimo-Italic.ttf");
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.amortization_list_item, null);
        }

        AmortizationItem i = objects.get(position);
        TextView monthLbl = (TextView) v.findViewById(R.id.monthLbl);
        TextView minimumPaymentLbl = (TextView) v.findViewById(R.id.minimumPaymentLbl);
        TextView extraPaymentLbl = (TextView) v.findViewById(R.id.extraPaymentLbl);

        monthLbl.setText(i.getMonth());
        monthLbl.setTypeface(arimoItalic);

        minimumPaymentLbl.setText(i.getMinPayment());
        minimumPaymentLbl.setTypeface(arimoItalic);

        extraPaymentLbl.setText(i.getExtraPayment());
        extraPaymentLbl.setTypeface(arimoItalic);

        return v;
    }
}
