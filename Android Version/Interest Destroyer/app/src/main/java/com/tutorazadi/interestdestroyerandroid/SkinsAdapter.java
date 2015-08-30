/**
 * Created by azadi on 8/29/15.
 */

package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SkinsAdapter extends ArrayAdapter<Skin> {
    ArrayList<Skin> skins;
    ViewHolder holder;

    public SkinsAdapter(Context context, int resource, ArrayList<Skin> skins) {
        super(context, resource, skins);
        this.skins = skins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_skin, null);
        }

        holder = new ViewHolder(view);
        holder.skinName.setText(skins.get(position).getName());

        holder.mainLayout.setBackground(getContext().getResources().getDrawable(skins.get(position).getBackgroundColor()));

        holder.button1.setBackground(getContext().getResources().getDrawable(skins.get(position).getButtonColor()));
        holder.button2.setBackground(getContext().getResources().getDrawable(skins.get(position).getButtonColor()));
        holder.button3.setBackground(getContext().getResources().getDrawable(skins.get(position).getButtonColor()));
        holder.button4.setBackground(getContext().getResources().getDrawable(skins.get(position).getButtonColor()));

        return view;
    }

    private static class ViewHolder
    {
        TextView skinName;
        Button button1, button2, button3, button4;
        RelativeLayout mainLayout;

        ViewHolder(View view)
        {
            skinName = (TextView) view.findViewById(R.id.skinName);
            button1 = (Button) view.findViewById(R.id.button1);
            button2 = (Button) view.findViewById(R.id.button2);
            button3 = (Button) view.findViewById(R.id.button3);
            button4 = (Button) view.findViewById(R.id.button4);
            mainLayout = (RelativeLayout) view.findViewById(R.id.mainLayout);
        }
    }
}
