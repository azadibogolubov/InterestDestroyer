package com.example.googlechartstest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		String data = "<html> <head> <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script> <script type=\"text/javascript\">google.load(\"visualization\", \"1.1\",{packages:[\"bar\"]}); google.setOnLoadCallback(drawChart); function drawChart(){var data=google.visualization.arrayToDataTable([ ['Year', 'Sales', 'Expenses', 'Profit'], ['2014', 1000, 400, 200], ['2015', 1170, 460, 250], ['2016', 660, 1120, 300], ['2017', 1030, 540, 350]]); var options={chart:{title: 'Company Performance', subtitle: 'Sales, Expenses, and Profit: 2014-2017',}, bars: 'horizontal' }; var chart=new google.charts.Bar(document.getElementById('barchart_material')); chart.draw(data, options);}</script></head><body><div id=\"barchart_material\" style=\"width: 900px; height: 500px;\"></div></body></html>";
        	WebView webview = (WebView)this.findViewById(R.id.webview);
        	webview.getSettings().setLoadWithOverviewMode(true); 
        	webview.getSettings().setUseWideViewPort(true);
        	webview.getSettings().setJavaScriptEnabled(true);
        webview.loadData(data, "text/html", "UTF-8");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
