/**
 * Created by azadi on 8/29/15.
 */

package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SkinsAdapter extends ArrayAdapter<Skin> {
    ArrayList<Skin> skins;
    ViewHolder holder;

    public SkinsAdapter(Context context, int resource, ArrayList<Skin> skins) {
        super(context, resource, skins);
        this.skins = skins;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity currentActivity = (Activity) getContext();
                Intent returnIntent = new Intent();
                Skin currentItem = skins.get(position);

                switch (position)
                {
                    case 0:
                        if (Funcs.isTablet(getContext()))
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_yellow_button_tablet);
                        else
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_yellow_button_phone);
                        returnIntent.putExtra("BACKGROUND_COLOR", R.drawable.light_blue_gradient);
                        break;
                    case 1:
                        if (Funcs.isTablet(getContext()))
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_blue_button_tablet);
                        else
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_blue_button_phone);
                        returnIntent.putExtra("BACKGROUND_COLOR", R.drawable.orange_gradient);
                        break;
                    case 2:
                        if (Funcs.isTablet(getContext()))
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_green_button_tablet);
                        else
                            returnIntent.putExtra("BUTTON_COLOR", R.drawable.rounded_green_button_phone);
                        returnIntent.putExtra("BACKGROUND_COLOR", R.drawable.green_gradient);
                        break;
                }
                currentActivity.setResult(Activity.RESULT_OK, returnIntent);
                currentActivity.finish();
            }
        });
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
