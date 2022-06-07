package com.MaxEle.maximarius.nir_navigation

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.MaxEle.maximarius.nir_navigation.util.GoToAnotherActivity
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.nl_activity_utils.DialogProcessorNL
import com.MaxEle.maximarius.nir_navigation.util.nl_activity_utils.ProcessorNL
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.*


class NLActivity : AppCompatActivity() {
    var isThemeLight: Boolean = false
    lateinit var mAdView: AdView
    private lateinit var dialogProcessor: DialogProcessorNL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        val isRussian = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, true)
        val userLocale = if (isRussian) "ru" else "en"

        val locale = Locale(userLocale)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        setContentView(R.layout.activity_nl)
        
        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }

        val processorNL = ProcessorNL(this, this, packageName)

        dialogProcessor = DialogProcessorNL(this, this, packageName, processorNL)

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        val buttonClose = findViewById<Button>(R.id.buttonNLclose)
        buttonClose.setOnClickListener {
            GoToAnotherActivity(this, this, MainActivity::class.java).start()
        }
        val buttonVisir = findViewById<Button>(R.id.buttonVIS)
        buttonVisir.setOnClickListener {
            //Обработка перемещения Визирки происходит в CustomImageViewForVisir
            processorNL.defaultVisir()
        }

        val buttonFlip = findViewById<Button>(R.id.buttonNLflip)
        buttonFlip.setOnClickListener {
            processorNL.isFlipped = !processorNL.isFlipped
            processorNL.changeNL()
        }

        val buttonOrient = findViewById<Button>(R.id.buttonOrient)
        buttonOrient.setOnClickListener {
            requestedOrientation = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                else -> ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }

        val buttonInstr = findViewById<Button>(R.id.buttonInstructon)
        buttonInstr.setOnClickListener {
            dialogProcessor.showDialog()
        }

        //-----------------------------------------------------------------------

        /*if (isRussian) {
            ButOrient.setText(R.string.Orientation)
            ButClose.setText(R.string.back)
            ButFlip.setText(R.string.NLflip)
            ButVisir.setText(R.string.Visir)
        } else {
            ButOrient.setText(R.string.Orientationeng)
            ButClose.setText(R.string.backeng)
            ButFlip.setText(R.string.NLflipeng)
            ButVisir.setText(R.string.Visireng)
        }*/


        /*  Button Button1=findViewById(R.id.buttonNLclose);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonNLflip);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);
        Button1=findViewById(R.id.buttonVIS);
        Button1.getBackground().setColorFilter(getResources().getColor(R.color.textsimple1), PorterDuff.Mode.MULTIPLY);*/
    }

    override fun onBackPressed() {
        GoToAnotherActivity(this, this, MainActivity::class.java).start()
    }

    public override fun onDestroy() {
        mAdView.destroy()
        super.onDestroy()
    }

    fun onClickBackDial(view: View?) {
        dialogProcessor.closeDialog()
    }
}