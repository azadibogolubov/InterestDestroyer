package com.tutorazadi.interestdestroyerandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;

import com.tutorazadi.interestdestroyerandroid.helpers.PrintHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AmortizationActivity extends Activity {

    @Bind(R.id.email) Button email;
    @Bind(R.id.print) Button print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amortization);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email)
    public void emailClick() {
        String email = "fakeemail@gmail.com";
        String body = "Test";

        // Set up email logic here.
        Intent sendViaEmail = new Intent(Intent.ACTION_SEND);
        sendViaEmail.setType("message/rfc822");
        sendViaEmail.setData(Uri.parse("mailto:"+ email));
        sendViaEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.amortization_subject));
        sendViaEmail.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(sendViaEmail, "Email Amortization Results:"));
    }

    @OnClick(R.id.print)
    public void printClick() {
        PrintHelper.callPrintService(this, "Test");
    }
}
