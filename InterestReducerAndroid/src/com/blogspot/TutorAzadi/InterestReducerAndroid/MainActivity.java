package com.blogspot.TutorAzadi.InterestReducerAndroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private EditText principalTxt, interestRateTxt, loanTermTxt;
	private Button calculateBtn, amortizeBtn;
	private double principalAmount, interestRate, loanTerm, paymentAmount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().setTitle("Obtain Loan Information");
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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

	public void initializeEditTextControls()
	{
		principalTxt = (EditText)findViewById(R.id.principalTxt);
		interestRateTxt = (EditText)findViewById(R.id.interestRateTxt);
		loanTermTxt = (EditText)findViewById(R.id.loanTermTxt);
	}
	
	public void initializeButtons() {
		calculateBtn = (Button) findViewById(R.id.calculateBtn);
		calculateBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				principalAmount = Double.parseDouble(principalTxt.getText().toString());
				interestRate = Double.parseDouble(interestRateTxt.getText().toString());
				loanTerm = Double.parseDouble(loanTermTxt.getText().toString());
				paymentAmount = Driver.amortize(principalAmount, interestRate, loanTerm);
				Toast.makeText(MainActivity.this, "Calculate Payment Request",
						Toast.LENGTH_LONG).show();
			}
		});

		amortizeBtn = (Button) findViewById(R.id.amortizeBtn);
		amortizeBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Amortization Request",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}
