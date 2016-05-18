package com.tutorazadi.interestdestroyerandroid;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class FabRunnable implements Runnable {
    private FloatingActionButton button;
    private boolean hide;

    public FabRunnable(FloatingActionButton button, boolean hide) {
        this.button = button;
        this.hide = hide;
    }

    public void run() {
        if (hide) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.GONE);
        }
    }
}
