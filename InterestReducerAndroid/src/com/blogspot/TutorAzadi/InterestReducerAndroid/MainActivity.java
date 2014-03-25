package com.blogspot.TutorAzadi.InterestReducerAndroid;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Spinner durationSpinner;
	private Button simpleBtn, compoundBtn, monthlyBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeSpinner();		
		initializeButtons();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void initializeSpinner()
	{
		List<String> items = new ArrayList<String>();
		items.add("Month(s)");
		items.add("Year(s)");
		durationSpinner = (Spinner) findViewById(R.id.duration_spinner);
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
		durationSpinner.setAdapter(spinnerAdapter);
	}
	
	public void initializeButtons()
	{
		simpleBtn = (Button) findViewById(R.id.simpleBtn);
		simpleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Toast.makeText(MainActivity.this, "Simple Interest Request", Toast.LENGTH_LONG).show();
			}
		});
		
		
		compoundBtn = (Button) findViewById(R.id.compoundBtn);
		compoundBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Toast.makeText(MainActivity.this, "Compound Interest Request", Toast.LENGTH_LONG).show();
			}
		});

		monthlyBtn = (Button) findViewById(R.id.monthlyBtn);
		monthlyBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v)
			{
				Toast.makeText(MainActivity.this, "Monthly Payment Request", Toast.LENGTH_LONG).show();
			}
		});
	}
}
