package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.view.animation.Animation
import com.google.android.gms.ads.AdView
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import android.animation.ObjectAnimator
import android.graphics.PorterDuff
import android.view.Gravity
import android.view.WindowManager
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.MaxEle.maximarius.nir_navigation.util.BillingClientSetup
import com.MaxEle.maximarius.nir_navigation.util.GoToAnotherActivity
import com.MaxEle.maximarius.nir_navigation.util.LanguageChangeProcessor
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.main_activity_utils.ButtonClickProcedures
import com.MaxEle.maximarius.nir_navigation.util.main_activity_utils.InstructionsProcessor
import com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils.BillingFunctions
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest

class MainActivity : AppCompatActivity(), PurchasesUpdatedListener {
    private lateinit var switchLang: SwitchCompat
    private lateinit var switchTheme: SwitchCompat
    private lateinit var instructionsProcessor: InstructionsProcessor
    lateinit var mAdView: AdView

    private var isFirstEnterForInitialization = false
    private var isThemeLight = false
    private var purchaseAdsOff = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_main)

        switchLang = findViewById(R.id.switchLang)
        switchTheme = findViewById(R.id.switchTheme)

        if (isThemeLight) {
            switchLang.setTextColor(getColor(R.color.textsimple))
            switchTheme.setTextColor(getColor(R.color.textsimple))
        } else {
            switchLang.setTextColor(getColor(R.color.textsimple1))
            switchTheme.setTextColor(getColor(R.color.textsimple1))
        }

        isFirstEnterForInitialization =
            mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, true)
        purchaseAdsOff =
            mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)

        val buttonClickProcedures = ButtonClickProcedures(this, this, packageName, this)

        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_FOR_INSTRUCTIONS, true)) {
            instructionsProcessor = InstructionsProcessor(this, this, packageName, this)
            instructionsProcessor.start()
        }

        //поставить переключатель темы в нужное положение
        switchTheme.isChecked = !isThemeLight
        val langChangeProc = LanguageChangeProcessor(this, this,  packageName)
        langChangeProc.loadLocale()

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }

        switchLang.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            val lang: String
            if (isChecked) {
                lang = "en"
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, false)
            } else {
                lang = "ru"
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, true)
            }
            langChangeProc.changeLang(lang)
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                switchTheme.setText(R.string.switchThemeDark)
                isThemeLight = false
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, false)
                buttonClickProcedures.updateTheme("1")
            } else {
                switchTheme.setText(R.string.switchThemeLight)
                isThemeLight = true
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
                buttonClickProcedures.updateTheme("")
            }
        }


        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности

        val buttonTasksMenu = findViewById<Button>(R.id.buttonTasks)
        val buttonEntryMenu = findViewById<Button>(R.id.buttonEntryTest)

        buttonTasksMenu.setOnClickListener {
            buttonClickProcedures.buttonTaskMenuClick()
        }

        buttonEntryMenu.setOnClickListener {
            buttonClickProcedures.buttonEHMenuClick()
        }

        val mapOfButtonToClassesKeys = mapOf(
            "buttonCalc" to CalculActivity::class.java,
            "buttonConstruct" to ConstructActivity::class.java,
            "buttonTest" to TestActivity::class.java,
            "buttonTraining" to TrainingActivity::class.java,
            "buttonInstructions" to InstructActivity::class.java,
            "button_EH" to TestEntryActivity::class.java,
            "button_EH_task" to TestEntryActivityTask::class.java,
            "buttonLZPTest" to TestLZPActivity::class.java,
            "buttonWind" to WindActivity::class.java,
            "buttonTimeCalc" to TimeCalcActivity::class.java,
            "buttonStat" to StatActivity::class.java,
            "buttonShop" to ShopActivity::class.java,
            "buttonNL" to NLActivity::class.java
        )
        for ((buttonId, classToGo) in mapOfButtonToClassesKeys) {
            val buttonToListen =
                findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonToListen.setOnClickListener {
                GoToAnotherActivity(this, this, classToGo).start()
            }
        }

        val instructionLayout = findViewById<LinearLayout>(R.id.ObychLayout)
        val buttonSkip = findViewById<Button>(R.id.buttonskip)
        buttonSkip.setOnClickListener {
            instructionsProcessor.obNo = 7
            instructionLayout.performClick()
        }
        instructionLayout.setOnClickListener {
            instructionsProcessor.start()
        }
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        buttonExit.setOnClickListener { finish() }
        //-----------------------------------------------------------------------
    }

    public override fun onDestroy() {
        mAdView.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        finish()
    }

    //------------------------------------------------------------------------------------------------------
    //Проверка купленных предметов в первом запуске

    override fun onPurchasesUpdated(billingResult: BillingResult, list: List<Purchase>?) {}
}