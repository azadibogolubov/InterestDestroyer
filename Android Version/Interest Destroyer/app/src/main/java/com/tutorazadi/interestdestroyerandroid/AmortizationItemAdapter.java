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
    private ViewHolder holder;
    TextView monthLbl;
    TextView minimumPaymentLbl;
    TextView extraPaymentLbl;

    public AmortizationItemAdapter(Context context, int textViewResourceId, ArrayList<AmortizationItem> objects) {
        super(context, textViewResourceId, objects);
        arimoItalic = Typeface.createFromAsset(context.getAssets(), "fonts/Arimo-Italic.ttf");
        this.objects = objects;
        holder = new ViewHolder();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_amortization_list, null);
        }

        initializeControls(v);

        AmortizationItem currentItem = objects.get(position);
        holder.month = currentItem.getMonth();
        holder.minimumPayment = currentItem.getMinPayment();
        holder.extraPayment = currentItem.getExtraPayment();


        monthLbl.setText(currentItem.getMonth());
        monthLbl.setTypeface(arimoItalic);

        minimumPaymentLbl.setText(currentItem.getMinPayment());
        minimumPaymentLbl.setTypeface(arimoItalic);

        extraPaymentLbl.setText(currentItem.getExtraPayment());
        extraPaymentLbl.setTypeface(arimoItalic);

        return v;
    }

    public void initializeControls(View v)
    {
        monthLbl = (TextView) v.findViewById(R.id.monthLbl);
        minimumPaymentLbl = (TextView) v.findViewById(R.id.minimumPaymentLbl);
        extraPaymentLbl = (TextView) v.findViewById(R.id.extraPaymentLbl);

    }
    private class ViewHolder {
        String month;
        String minimumPayment;
        String extraPayment;
    }
}
