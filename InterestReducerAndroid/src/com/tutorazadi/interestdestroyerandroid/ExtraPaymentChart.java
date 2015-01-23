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
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ExtraPaymentChart extends Activity {

	GraphicalView mChartView = null;
	public static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
	public static DecimalFormat df = new DecimalFormat("#.##");
	public static double[] extraPayment, minimumPayment;
//	public static Button amortizeBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extra_payment_chart);

		/*if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		Bundle extras = getIntent().getExtras();
		int size = (int)extras.getDouble("TOTAL_MONTHS");
		String[] mMonth = new String[size/12];
		int[] x = new int[size];
		int b = 0;
		for (int i = 0; i < size; i+=12)
		{
			mMonth[b] = String.valueOf(b+1);
			x[b] = (b+1);
			b++;
		}
		extraPayment = extras.getDoubleArray("EXTRA_PAYMENTS");
		minimumPayment = extras.getDoubleArray("MINIMUM_PAYMENTS");
		/*amortizeBtn = (Button)findViewById(R.id.amortizeBtn);
		amortizeBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				paidOnly(v);
			}
		});*/

		TextView monthsSavedLbl;
        TextView interestSavedLbl;
        
        XYSeries baseSeries = new XYSeries("Base");
        XYSeries extraPaymentSeries = new XYSeries("Extra Payment");
        
        int c = 0;
        for(int i=0;i<size;i+=12)
        {
            baseSeries.add(c,minimumPayment[i] / 100);
            extraPaymentSeries.add(c,extraPayment[i] / 100);
            c++;
        }
 
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(baseSeries);
        dataset.addSeries(extraPaymentSeries);
 
        XYSeriesRenderer baseRenderer = new XYSeriesRenderer();
        baseRenderer.setGradientEnabled(true);
        baseRenderer.setGradientStart(0, Color.rgb(255, 255, 255));
        baseRenderer.setGradientStop(1000, Color.rgb(0, 0, 255));       
        baseRenderer.setFillPoints(true);
        baseRenderer.setLineWidth(5);
        baseRenderer.setDisplayChartValues(false);
        
        XYSeriesRenderer extraPaymentRenderer = new XYSeriesRenderer();
        extraPaymentRenderer.setGradientEnabled(true);
        extraPaymentRenderer.setGradientStart(0, Color.rgb(255, 255, 255));
        extraPaymentRenderer.setGradientStop(1000, Color.rgb(0, 255, 0));
        extraPaymentRenderer.setFillPoints(true);
        extraPaymentRenderer.setLineWidth(5);
        extraPaymentRenderer.setColor(Color.GREEN);
        extraPaymentRenderer.setDisplayChartValues(false);
        
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        multiRenderer.setYLabels(0);
        multiRenderer.setChartTitle("Base vs Extra Payments Comparison Chart");
        multiRenderer.setXTitle("Year #");
        multiRenderer.setYTitle("Principal remaining");
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setLegendTextSize(24);
        multiRenderer.setXAxisMin(-5.0f);
        multiRenderer.setXAxisMax(10.0f);
        multiRenderer.setYAxisMin(0.0f);
        multiRenderer.setYAxisMax(1000.0f);
        
        multiRenderer.setLabelsColor(Color.GREEN);
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setBackgroundColor(Color.BLACK);
        multiRenderer.setMarginsColor(Color.BLACK);
        multiRenderer.setLabelsTextSize(24);
        multiRenderer.setPanEnabled(true,  false);
        
        for(int i=0; i< size/12;i++)
            multiRenderer.addXTextLabel(i, mMonth[i]);
        
        multiRenderer.addSeriesRenderer(baseRenderer);
        multiRenderer.addSeriesRenderer(extraPaymentRenderer);
        
        
        monthsSavedLbl = (TextView)findViewById(R.id.monthsSaved);
        interestSavedLbl = (TextView)findViewById(R.id.interestSaved);

        monthsSavedLbl.setText(monthsSavedLbl.getText() + String.valueOf(df.format(extras.getDouble("TIME_SAVED"))));
        interestSavedLbl.setText(interestSavedLbl.getText() + String.valueOf(n.format(extras.getDouble("INTEREST_SAVED"))));
        mChartView = ChartFactory.getBarChartView(this, dataset, multiRenderer, BarChart.Type.DEFAULT);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//		params.addRule(RelativeLayout.BELOW, findViewById(R.id.amortizeBtn).getId());
		mChartView.setLayoutParams(params);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.chartsRelativeLayout);
		layout.addView(mChartView);
	}

	public void paidOnly(View v)
	{
		//Toast.makeText(this, "This feature is available in the paid version of the app only...", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, Amortization.class);
		startActivity(intent);
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
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_extra_payment_chart, container, false);
			return rootView;
		}
	}*/

}
