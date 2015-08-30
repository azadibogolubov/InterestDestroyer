/**
 * Created by azadi on 8/29/15.
 */

package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SkinsAdapter extends ArrayAdapter<Skin> {
    ArrayList<Skin> skins;
    ViewHolder holder;

    public SkinsAdapter(Context context, int resource, ArrayList<Skin> skins) {
        super(context, resource, skins);
        this.skins = skins;
        holder = new ViewHolder();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_skin, null);
        }

        holder.name = skins.get(position).getName();
        holder.backgroundColor = skins.get(position).getBackgroundColor();
        holder.titleColor = skins.get(position).getTitleColor();
        holder.buttonColor = skins.get(position).getButtonColor();

        return v;
    }

    private class ViewHolder
    {
        String name;
        String backgroundColor;
        String titleColor;
        String buttonColor;
    }
}
