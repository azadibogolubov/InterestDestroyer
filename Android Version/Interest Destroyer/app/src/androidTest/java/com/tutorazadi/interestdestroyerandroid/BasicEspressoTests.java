package com.tutorazadi.interestdestroyerandroid;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.tutorazadi.interestdestroyerandroid.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)

public class BasicEspressoTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    @SmallTest
    public void checkThatAmortizationLabelIsDisplayed() {
        closeSoftKeyboard();
        onView(withText(R.string.amortization_UI)).check(matches(isDisplayed()));
        onView(withContentDescription(R.string.amortization_UI)).check(matches(isDisplayed()));
    }
}
