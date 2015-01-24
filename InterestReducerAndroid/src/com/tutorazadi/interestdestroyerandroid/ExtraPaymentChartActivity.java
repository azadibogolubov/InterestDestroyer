package com.tutorazadi.interestdestroyerandroid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExtraPaymentChartActivity extends Activity {

	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	public static double[] extraPayment, minimumPayment;
	double payoff_months, interest;
	TextView yearsSaved, interestSaved;
	String data;
	ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_payment_chart);

		yearsSaved = (TextView) findViewById(R.id.yearsSaved);
		interestSaved = (TextView) findViewById(R.id.interestSaved);
		
		Bundle extras = getIntent().getExtras();
		extraPayment = extras.getDoubleArray("EXTRA_PAYMENTS");
		minimumPayment = extras.getDoubleArray("MINIMUM_PAYMENTS");
		payoff_months = extras.getDouble("PAYOFF_MONTHS");
		interest = extras.getDouble("INTEREST_SAVED");
		
		yearsSaved.setText(String.format("%s %.2f", yearsSaved.getText().toString(), payoff_months));
		interestSaved.setText(String.format("%s $%.2f", interestSaved.getText().toString(), interest));
		
		int year = 2014;
		String values = "";
		for (int i = 0; i < extraPayment.length; i++)
		{
			values += "[\'" + year + "\', " + minimumPayment[i] / 1000 + ", " + extraPayment[i] / 1000 + "]";
			if (i % 11 == 0)
				year++;
			if (i != extraPayment.length-1)
				values += ",";
			if (i == extraPayment.length-1)
				values += "]);";
		}
//		Toast.makeText(this, values, 100).show();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		data = "<html> "
				+ "<head> "
				+ "<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>"
				+ " <script type=\"text/javascript\">"
				+ "google.load(\"visualization\", \"1.1\",{packages:[\"bar\"]}); "
				+ "google.setOnLoadCallback(drawChart); "
				+ "function drawChart(){var data=google.visualization.arrayToDataTable([ ['Year', 'Minimum payment', 'Extra payment'], "
				+ values
				+ "var options={chart:{title: 'Mortgage information', subtitle: '30 year view',}, bars: 'horizontal' }; "
				+ "var chart=new google.charts.Bar(document.getElementById('barchart_material')); "
				+ "chart.draw(data, options);"
				+ "}</script>"
				+ "</head>"
				+ "<body>"
				+ "<div id=\"barchart_material\" style=\"width: 900px; height: 500px;\">"
				+ "</div>"
				+ "</body>"
				+ "</html>";
        final WebView webview = (WebView)this.findViewById(R.id.webView);
        webview.getSettings().setLoadWithOverviewMode(true); 
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        Runnable r = new Runnable() {
        	public void run()
        	{
        	       webview.loadData(data, "text/html", "UTF-8");

        	}
        };
        r.run();
 	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extra_payment_chart, menu);
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
	    if ((keyCode == KeyEvent.KEYCODE_BACK))
	    {
	        finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
}