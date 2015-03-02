package com.tutorazadi.interestdestroyerandroid;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class AboutActivity extends Activity {

    TextView aboutLbl, softwareDesignerLbl, softwareDesignerCompanyLbl;
    TextView softwareDesignerWebsiteLbl, graphicsDesignerLbl;
    TextView graphicsDesignerCompanyLbl, fontsLbl, arimoLbl;
    TextView parisishLbl, chartingEngineLbl, aChartEngineLbl;

    Typeface arimo, arimoBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        arimo = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Regular.ttf");
        arimoBold = Typeface.createFromAsset(this.getAssets(), "fonts/Arimo-Bold.ttf");

        aboutLbl = (TextView) findViewById(R.id.aboutLbl);
        aboutLbl.setTypeface(arimoBold);

        softwareDesignerLbl = (TextView) findViewById(R.id.softwareDesignerLbl);
        softwareDesignerLbl.setTypeface(arimo);

        softwareDesignerCompanyLbl = (TextView) findViewById(R.id.softwareDesignerCompanyLbl);
        softwareDesignerCompanyLbl.setTypeface(arimo);

        softwareDesignerWebsiteLbl = (TextView) findViewById(R.id.softwareDesignerWebsiteLbl);
        softwareDesignerWebsiteLbl.setTypeface(arimo);

        graphicsDesignerLbl = (TextView) findViewById(R.id.graphicsDesignerLbl);
        graphicsDesignerLbl.setTypeface(arimo);

        graphicsDesignerCompanyLbl = (TextView) findViewById(R.id.graphicsDesignerCompanyLbl);
        graphicsDesignerCompanyLbl.setTypeface(arimo);

        fontsLbl = (TextView) findViewById(R.id.arimoLbl);
        fontsLbl.setTypeface(arimo);

        arimoLbl = (TextView) findViewById(R.id.arimoLbl);
        arimoLbl.setTypeface(arimo);

        parisishLbl = (TextView) findViewById(R.id.parisishLbl);
        parisishLbl.setTypeface(arimo);

        chartingEngineLbl = (TextView) findViewById(R.id.chartingEngineLbl);
        chartingEngineLbl.setTypeface(arimo);

        aChartEngineLbl = (TextView) findViewById(R.id.aChartEngineLbl);
        aChartEngineLbl.setTypeface(arimo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
}
