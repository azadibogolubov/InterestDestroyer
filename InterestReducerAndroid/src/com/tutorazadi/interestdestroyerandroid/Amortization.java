package com.tutorazadi.interestdestroyerandroid;

import java.util.Random;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.Build;

public class Amortization extends Activity {

	TableLayout table;
	Spinner spinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_amortization);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//table = (TableLayout)findViewById(R.id.table);
		//spinner = (Spinner)findViewById(R.id.spinner_compare);		
		
		TableRow row[] = new TableRow[360];
		for (int i = 0; i < 360; i++)
		{
			row[i] = new TableRow(this);
			row[i].setPadding(0,20,0,20);
			row[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		
		Random r = new Random();
        TextView[] tv = new TextView[1080];
        int rowCount = 0, monthNum = 1;
        for (int i = 0; i < 1080; i+=3)
        {
        	tv[i] = new TextView(this);
        	tv[i].setText(String.valueOf((monthNum)));
        	tv[i].setTextColor(Color.WHITE);
        	tv[i].setGravity(Gravity.CENTER);    	
        	tv[i+1] = new TextView(this);
        	tv[i+1].setText("$" + String.valueOf(r.nextInt(500)));
        	tv[i+1].setTextColor(Color.WHITE);
        	tv[i+1].setGravity(Gravity.CENTER);    	
        	tv[i+2] = new TextView(this);
        	tv[i+2].setText("$" + String.valueOf(r.nextInt(500)));
        	tv[i+2].setTextColor(Color.WHITE);
        	tv[i+2].setGravity(Gravity.CENTER);    	
        	
        	row[rowCount].addView(tv[i]);
        	row[rowCount].addView(tv[i+1]);
        	row[rowCount].addView(tv[i+2]);
        	table.addView(row[rowCount], new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        	rowCount++;
        	monthNum++;
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.amortization, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
