package com.tutorazadi.interestdestroyerandroid

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.TextView

class SplashScreenActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
//            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            val intent = Intent(this@SplashScreenActivity, AmortizationActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        var parisish: Typeface = Typeface.createFromAsset(this.assets, "fonts/Parisish.ttf")
        var interestTxt: TextView = findViewById(R.id.interestTxt) as TextView

        interestTxt.typeface = parisish
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}