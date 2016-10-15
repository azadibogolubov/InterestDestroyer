package com.tutorazadi.interestdestroyerandroid.helpers;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

public class PrintHelper {
    private static WebView mWebView;
    private static ArrayList<PrintJob> mPrintJobs;

    public static void callPrintService(final Context context, String data) {
        mPrintJobs = new ArrayList<>();

        WebView webView = new WebView(context);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { return false; }

            @Override
            public void onPageFinished(WebView view, String url) {
                createPrintJob(context, view);
            }
        });

        data = "<html><body>" + data + "</body></html>";
        webView.loadDataWithBaseURL(null, data, "text/HTML", "UTF-8", null);

        mWebView = webView;
    }

    private static void createPrintJob(Context context, WebView webView) {
        PrintManager manager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        String jobName = "Amortization Results";
        PrintJob printJob = manager.print(jobName, printAdapter, new PrintAttributes.Builder().build());

        mPrintJobs.add(printJob);
    }
}
