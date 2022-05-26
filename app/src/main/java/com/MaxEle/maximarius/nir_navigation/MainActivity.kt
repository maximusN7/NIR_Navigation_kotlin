package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.view.animation.Animation
import com.google.android.gms.ads.AdView
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import android.content.Intent
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
import com.MaxEle.maximarius.nir_navigation.util.LanguageChangeProcessor
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest

class MainActivity : AppCompatActivity(), PurchasesUpdatedListener {
    private var obNo = -1
    private var isFirstEnterForInitialization = false
    private var switchLang: SwitchCompat? = null
    private var switchTheme: SwitchCompat? = null
    private var flagMenuTaskOpen = false
    private var animSubMenuOpen: Animation? = null
    private var animSubMenuClose: Animation? = null
    private var flagMenuEntryHoldingOpen = false
    private var buttonTasksMenu: Button? = null
    private var buttonEntryMenu: Button? = null
    private var isThemeLight = false
    private var purchaseAdsOff = false
    var billingClient: BillingClient? = null
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_main)

        switchLang = findViewById(R.id.switchLang)
        switchTheme = findViewById(R.id.switchTheme)

        if (isThemeLight) {
            switchLang?.setTextColor(getColor(R.color.textsimple))
            switchTheme?.setTextColor(getColor(R.color.textsimple))
        } else {
            switchLang?.setTextColor(getColor(R.color.textsimple1))
            switchTheme?.setTextColor(getColor(R.color.textsimple1))
        }

        isFirstEnterForInitialization =
            mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, true)
        purchaseAdsOff =
            mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)

        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_FOR_INSTRUCTIONS, true)) {
            obNo = -1
            instructionsProcessor()
        }

        //поставить переключатель темы в нужное положение
        switchTheme?.isChecked = !isThemeLight
        val langChangeProc = LanguageChangeProcessor(this, this,  packageName)
        langChangeProc.loadLocale()
        mAdView = findViewById(R.id.banner_ad)

        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        switchLang?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
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

        switchTheme?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                switchTheme!!.setText(R.string.switchThemeDark)
                isThemeLight = false
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, false)
                updateTheme("1")
            } else {
                switchTheme!!.setText(R.string.switchThemeLight)
                isThemeLight = true
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
                updateTheme("")
            }
        }


        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности

        buttonTasksMenu = findViewById(R.id.buttonTasks)
        buttonTasksMenu?.setOnClickListener { view: View? ->
            animSubMenuOpen = AnimationUtils.loadAnimation(this, R.anim.undmenu_anim)
            animSubMenuClose = AnimationUtils.loadAnimation(this, R.anim.undmenu_anim_out)
            val subMenuTasks = findViewById<LinearLayout>(R.id.undermenu_task)
            if (flagMenuTaskOpen) {
                val animationOpen = ObjectAnimator.ofFloat(view, "translationX", 0f)
                animationOpen.duration = 300
                animationOpen.start()
                if (isThemeLight) {
                    buttonTasksMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbutton),
                        PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    buttonTasksMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbutton1),
                        PorterDuff.Mode.MULTIPLY
                    )
                }
                subMenuTasks.startAnimation(animSubMenuClose)
                subMenuTasks.visibility = View.INVISIBLE
                flagMenuTaskOpen = false
            } else {
                val animat = ObjectAnimator.ofFloat(view, "translationX", 21f)
                animat.duration = 700
                animat.start()
                if (isThemeLight) {
                    buttonTasksMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbuttonpressed),
                        PorterDuff.Mode.MULTIPLY
                    )
                    subMenuTasks.setBackgroundColor(getColor(R.color.backgroundbuttonpressed))
                } else {
                    buttonTasksMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbuttonpressed1),
                        PorterDuff.Mode.MULTIPLY
                    )
                    subMenuTasks.setBackgroundColor(getColor(R.color.backgroundbuttonpressed1))
                }
                val subMenuEH = findViewById<LinearLayout>(R.id.undermenu_EH)
                if (flagMenuEntryHoldingOpen) {
                    val animat2 = ObjectAnimator.ofFloat(buttonEntryMenu!!, "translationX", 0f)
                    animat2.duration = 300
                    animat2.start()
                    if (isThemeLight) {
                        buttonEntryMenu!!.background.setColorFilter(
                            getColor(R.color.backgroundbutton),
                            PorterDuff.Mode.MULTIPLY
                        )
                    } else {
                        buttonEntryMenu!!.background.setColorFilter(
                            getColor(R.color.backgroundbutton1),
                            PorterDuff.Mode.MULTIPLY
                        )
                    }
                    subMenuEH.startAnimation(animSubMenuClose)
                    subMenuEH.visibility = View.INVISIBLE
                    flagMenuEntryHoldingOpen = false
                }
                subMenuTasks.startAnimation(animSubMenuOpen)
                subMenuTasks.visibility = View.VISIBLE
                flagMenuTaskOpen = true
            }
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
                finish()
                val intent = Intent(this@MainActivity, classToGo)
                startActivity(intent)
                overridePendingTransition(R.anim.act_out, R.anim.act_in)
            }
        }

        val buttonExit = findViewById<Button>(R.id.buttonExit)
        buttonExit.setOnClickListener { finish() }

        buttonEntryMenu = findViewById(R.id.buttonEntryTest)
        buttonEntryMenu?.setOnClickListener { view: View? ->
            animSubMenuOpen = AnimationUtils.loadAnimation(this, R.anim.undmenu_anim)
            animSubMenuClose = AnimationUtils.loadAnimation(this, R.anim.undmenu_anim_out)
            val subMenuEH = findViewById<LinearLayout>(R.id.undermenu_EH)
            if (flagMenuEntryHoldingOpen) {
                val animat = ObjectAnimator.ofFloat(view, "translationX", 0f)
                animat.duration = 300
                animat.start()
                if (isThemeLight) {
                    buttonEntryMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbutton),
                        PorterDuff.Mode.MULTIPLY
                    )
                } else {
                    buttonEntryMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbutton1),
                        PorterDuff.Mode.MULTIPLY
                    )
                }
                subMenuEH.startAnimation(animSubMenuClose)
                subMenuEH.visibility = View.INVISIBLE
                flagMenuEntryHoldingOpen = false
            } else {
                val animat = ObjectAnimator.ofFloat(view, "translationX", 21f)
                animat.duration = 700
                animat.start()
                if (isThemeLight) {
                    buttonEntryMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbuttonpressed),
                        PorterDuff.Mode.MULTIPLY
                    )
                    subMenuEH.setBackgroundColor(getColor(R.color.backgroundbuttonpressed))
                } else {
                    buttonEntryMenu?.background?.setColorFilter(
                        getColor(R.color.backgroundbuttonpressed1),
                        PorterDuff.Mode.MULTIPLY
                    )
                    subMenuEH.setBackgroundColor(getColor(R.color.backgroundbuttonpressed1))
                }
                val undmen_task = findViewById<LinearLayout>(R.id.undermenu_task)
                if (flagMenuTaskOpen) {
                    val animat2 = ObjectAnimator.ofFloat(buttonTasksMenu!!, "translationX", 0f)
                    animat2.duration = 300
                    animat2.start()
                    if (isThemeLight) {
                        buttonTasksMenu?.background?.setColorFilter(
                            getColor(R.color.backgroundbutton),
                            PorterDuff.Mode.MULTIPLY
                        )
                    } else {
                        buttonTasksMenu?.background?.setColorFilter(
                            getColor(R.color.backgroundbutton1),
                            PorterDuff.Mode.MULTIPLY
                        )
                    }
                    undmen_task.startAnimation(animSubMenuClose)
                    undmen_task.visibility = View.INVISIBLE
                    flagMenuTaskOpen = false
                }
                subMenuEH.startAnimation(animSubMenuOpen)
                subMenuEH.visibility = View.VISIBLE
                flagMenuEntryHoldingOpen = true
            }
        }

        val instructionLayout = findViewById<LinearLayout>(R.id.ObychLayout)
        val buttonSkip = findViewById<Button>(R.id.buttonskip)
        buttonSkip.setOnClickListener {
            obNo = 7
            instructionLayout?.performClick()
        }
        instructionLayout?.setOnClickListener {
            instructionsProcessor()
        }
        //-----------------------------------------------------------------------
    }

    private fun nextInstruction(obNo: Int, rotationVal: Float = 0f) {
        if (obNo > 0) {
            if (obNo > 1) {
                val instructionImage = findViewById<ImageView>(
                    resources.getIdentifier(
                        "imageView${obNo - 1}",
                        "id",
                        packageName
                    )
                )
                instructionImage.visibility = View.INVISIBLE
            }

            if (obNo < 8) {
                val instructionImage = findViewById<ImageView>(
                    resources.getIdentifier(
                        "imageView$obNo",
                        "id",
                        packageName
                    )
                )
                instructionImage?.rotation = rotationVal
                instructionImage?.visibility = View.VISIBLE
            }
        }

        val instructionText: TextView = findViewById(R.id.ObychText)
        instructionText.text = resources.getString(
            resources.getIdentifier(
                "@string/Ob$obNo",
                "id",
                packageName
            )
        )
    }
    private fun instructionsProcessor() {
        val instructionLayout = findViewById<LinearLayout>(R.id.ObychLayout)
        obNo++
        when (obNo) {
            0 -> {
                instructionLayout.visibility = View.VISIBLE
                val instructionLayoutText = findViewById<RelativeLayout>(R.id.ObychLayoutText)
                instructionLayoutText?.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.obych,
                    null
                )
                nextInstruction(obNo)
            }
            1 -> {
                nextInstruction(obNo)
            }
            2 -> {
                nextInstruction(obNo, 180f)
            }
            3 -> {
                buttonTasksMenu?.performClick()
                nextInstruction(obNo, 180f)
            }
            4 -> {
                buttonEntryMenu?.performClick()
                nextInstruction(obNo, 180f)
            }
            5 -> {
                buttonEntryMenu?.performClick()
                instructionLayout?.gravity = Gravity.CENTER or Gravity.TOP
                nextInstruction(obNo, 180f)
            }
            6 -> {
                nextInstruction(obNo, 180f)
            }
            7 -> {
                nextInstruction(obNo, 90f)
            }
            8 -> {
                val mDataFiles = SharedPreferencesProcessor(this)
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_FOR_INSTRUCTIONS, false)

                for (i in 1..7) {
                    val instructionImage = findViewById<ImageView>(
                        resources.getIdentifier(
                            "imageView${i}",
                            "id",
                            packageName
                        )
                    )
                    instructionImage.visibility = View.INVISIBLE
                }

                if (isFirstEnterForInitialization) {
                    mDataFiles.checkDataFiles()
                    setupBillingClient()
                    mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, false)
                }
                val buttonSkip = findViewById<Button>(R.id.buttonskip)
                buttonSkip.visibility = View.INVISIBLE

                nextInstruction(obNo)
            }
            else -> {
                instructionLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun updateTheme(emptyForLightOneForDarkTheme: String) {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = getColor(
            resources.getIdentifier(
                "colorPrimary$emptyForLightOneForDarkTheme",
                "color",
                packageName
            )
        )
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                getColor(
                    resources.getIdentifier(
                        "colorPrimary$emptyForLightOneForDarkTheme",
                        "color",
                        packageName
                    )
                )
            )
        )
        val background = findViewById<ConstraintLayout>(R.id.BackAll)
        background.setBackgroundColor(
            getColor(
                resources.getIdentifier(
                    "background$emptyForLightOneForDarkTheme",
                    "color",
                    packageName
                )
            )
        )
        var backgroundSubMenu = findViewById<LinearLayout>(R.id.undermenu_EH)
        backgroundSubMenu.setBackgroundColor(
            getColor(
                resources.getIdentifier(
                    "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                    "color",
                    packageName
                )
            )
        )
        backgroundSubMenu = findViewById(R.id.undermenu_task)
        backgroundSubMenu.setBackgroundColor(
            getColor(
                resources.getIdentifier(
                    "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                    "color",
                    packageName
                )
            )
        )
        switchTheme!!.setTextColor(
            getColor(
                resources.getIdentifier(
                    "textsimple$emptyForLightOneForDarkTheme",
                    "color",
                    packageName
                )
            )
        )
        switchLang!!.setTextColor(
            getColor(
                resources.getIdentifier(
                    "textsimple$emptyForLightOneForDarkTheme",
                    "color",
                    packageName
                )
            )
        )


        for ((buttonId) in mapButtonsToTextKeys) {
            val buttonForThemeUpdate =
                findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonForThemeUpdate.background.setColorFilter(
                getColor(
                    resources.getIdentifier(
                        "backgroundbutton$emptyForLightOneForDarkTheme",
                        "color",
                        packageName
                    )
                ),
                PorterDuff.Mode.MULTIPLY
            )
            buttonForThemeUpdate.setTextColor(
                getColor(
                    resources.getIdentifier(
                        "textsimple$emptyForLightOneForDarkTheme",
                        "color",
                        packageName
                    )
                )
            )

            if (buttonId == "buttonTasks" && flagMenuTaskOpen || buttonId == "buttonEntryTest" && flagMenuEntryHoldingOpen) {
                buttonForThemeUpdate.background.setColorFilter(
                    getColor(
                        resources.getIdentifier(
                            "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                            "color",
                            packageName
                        )
                    ),
                    PorterDuff.Mode.MULTIPLY
                )
            }
        }
    }

    private val mapButtonsToTextKeys = mapOf(
        "buttonShop" to "shop",
        "buttonCalc" to "calcul",
        "buttonConstruct" to "constructor",
        "buttonTest" to "test",
        "buttonTraining" to "training",
        "buttonStat" to "statistics",
        "buttonInstructions" to "instructions",
        "buttonLZPTest" to "testLZP",
        "buttonEntryTest" to "EntryTest",
        "buttonNL" to "NL",
        "buttonExit" to "exit",
        "buttonTasks" to "tasks",
        "buttonWind" to "wind",
        "button_EH" to "EH",
        "buttonTimeCalc" to "TimeCalcMen",
        "button_EH_task" to "EH_task"
    )

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        finish()
    }

    //------------------------------------------------------------------------------------------------------
    //Проверка купленных предметов в первом запуске
    private fun setupBillingClient() {
        billingClient = BillingClientSetup.getInstance(this, this)
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    //Query
                    val purchases = billingClient?.queryPurchases(BillingClient.SkuType.INAPP)
                        ?.purchasesList
                    purchases?.let { handleItemAlreadyPurchase(it) }
                }
            }

            override fun onBillingServiceDisconnected() {}
        })
    }

    private fun handleItemAlreadyPurchase(purchases: List<Purchase>) {
        for (purchase in purchases) {
            val mDataFiles = SharedPreferencesProcessor(this)
            if (purchase.sku == "donation") //Consume item
            {
                purchaseAdsOff = true
                mAdView!!.visibility = View.INVISIBLE
                mDataFiles.setBoolean(
                    SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE,
                    purchaseAdsOff
                )
            }
            if (purchase.sku == "prem_pass") {
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
            }
            if (purchase.sku == "perm_max_lvl") {
                mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, 7)
            }
            if (purchase.sku == "na_pecheni") {
                purchaseAdsOff = true
                mAdView!!.visibility = View.INVISIBLE
                mDataFiles.setBoolean(
                    SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE,
                    purchaseAdsOff
                )
            }
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, list: List<Purchase>?) {}
}