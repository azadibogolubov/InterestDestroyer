/**
 * Created by azadi on 8/30/15.
 */
package com.tutorazadi.interestdestroyerandroid;

import android.content.Context;
import android.content.res.Configuration;

public class Funcs {
    public static boolean isTablet(Context context)
    {
        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }
}
