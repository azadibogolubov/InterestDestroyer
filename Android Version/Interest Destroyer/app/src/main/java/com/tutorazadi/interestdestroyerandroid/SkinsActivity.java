package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_skins, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initializeControls()
    {
        ArrayList<Skin> skins = new ArrayList<>();
        skins.add(new Skin("blue", "", "", ""));
        skins.add(new Skin("blue", "", "", ""));
        skins.add(new Skin("blue", "", "", ""));

        skinsAdapter = new SkinsAdapter(this, R.layout.item_skin, skins);

        skinsList = (ListView) findViewById(R.id.skinsList);
        skinsList.setAdapter(skinsAdapter);
    }
}
