package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SkinsActivity extends Activity {

    ListView skinsList;
    SkinsAdapter skinsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skins);
        initializeControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_skins, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeControls()
    {
        ArrayList<Skin> skins = new ArrayList<>();
        skins.add(new Skin("Skin 1: Feeling Blue", R.drawable.light_blue_gradient_outlined, "", R.drawable.rounded_yellow_button_phone));
        skins.add(new Skin("Skin 2: The Sun is Shining", R.drawable.orange_gradient_outlined, "", R.drawable.rounded_blue_button_phone));
        skins.add(new Skin("Skin 3: In the Green", R.drawable.green_gradient_outlined, "", R.drawable.rounded_green_button_phone));

        skinsAdapter = new SkinsAdapter(this, R.layout.item_skin, skins);

        skinsList = (ListView) findViewById(R.id.skinsList);
        skinsList.setAdapter(skinsAdapter);
    }
}
