package com.tutorazadi.interestdestroyerandroid;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ExtraPaymentChartActivity extends Activity {

	GraphicalView mChartView = null;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	public static double[] extraPayment, minimumPayment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_payment_chart);

		Bundle extras = getIntent().getExtras();
		int size = (int)extras.getDouble("TOTAL_MONTHS");

		extraPayment = extras.getDoubleArray("EXTRA_PAYMENTS");
		minimumPayment = extras.getDoubleArray("MINIMUM_PAYMENTS");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		String data = "<html> <head> <script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script> <script type=\"text/javascript\">google.load(\"visualization\", \"1.1\",{packages:[\"bar\"]}); google.setOnLoadCallback(drawChart); function drawChart(){var data=google.visualization.arrayToDataTable([ ['Year', 'Sales', 'Expenses', 'Profit'], ['2014', 1000, 400, 200], ['2015', 1170, 460, 250], ['2016', 660, 1120, 300], ['2017', 1030, 540, 350]]); var options={chart:{title: 'Company Performance', subtitle: 'Sales, Expenses, and Profit: 2014-2017',}, bars: 'horizontal' }; var chart=new google.charts.Bar(document.getElementById('barchart_material')); chart.draw(data, options);}</script></head><body><div id=\"barchart_material\" style=\"width: 900px; height: 500px;\"></div></body></html>";
        WebView webview = (WebView)this.findViewById(R.id.webView);
        webview.getSettings().setLoadWithOverviewMode(true); 
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadData(data, "text/html", "UTF-8");
        //webview.loadUrl("http://www.google.com");

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