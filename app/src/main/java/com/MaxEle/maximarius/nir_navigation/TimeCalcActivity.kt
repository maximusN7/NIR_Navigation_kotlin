package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.content.Intent
import android.view.View
import android.widget.Button
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.timeCalc_activity_utils.CalculationProcessor
import com.google.android.gms.ads.AdRequest

class TimeCalcActivity : AppCompatActivity() {
    private var isThemeLight = false
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_time_calc)

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        val calculationProcessor = CalculationProcessor(this, this)

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
            val intent = Intent(this@TimeCalcActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
        }

        val mapOfButtonToNumberKeys = mapOf(
            "button0" to '0',
            "button1" to '1',
            "button2" to '2',
            "button3" to '3',
            "button4" to '4',
            "button5" to '5',
            "button6" to '6',
            "button7" to '7',
            "button8" to '8',
            "button9" to '9'
        )
        for ((buttonId, numberChar) in mapOfButtonToNumberKeys) {
            val buttonToListen =
                findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonToListen.setOnClickListener {
                calculationProcessor.whenNumberPressed(numberChar)
            }
        }

        val buttonClear = findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener { calculationProcessor.clearAll() }
        val buttonMinus = findViewById<Button>(R.id.buttonminus)
        buttonMinus.setOnClickListener {
            calculationProcessor.minus()
        }
        val buttonPlus = findViewById<Button>(R.id.buttonplus)
        buttonPlus.setOnClickListener {
            calculationProcessor.plus()
        }
        val buttonEquals = findViewById<Button>(R.id.buttonequ)
        buttonEquals.setOnClickListener {
            calculationProcessor.equals()
        }

        //-----------------------------------------------------------------------
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@TimeCalcActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

}