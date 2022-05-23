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
import android.content.res.Configuration
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.MaxEle.maximarius.nir_navigation.util.BillingClientSetup
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest
import java.util.*

class MainActivity : AppCompatActivity(), PurchasesUpdatedListener {
    private var instructionLayout: LinearLayout? = null
    private var instructionLayoutText: RelativeLayout? = null
    private var instructionText: TextView? = null
    private var instructionImage: ImageView? = null
    private var obNo = 0
    private var isFirstEnterForInitialization = false
    private var isFirstEnterForInstructions = true
    private var switchLang: SwitchCompat? = null
    private var switchTheme: SwitchCompat? = null
    private var flagMenuTaskOpen = false
    private var animSubMenuOpen: Animation? = null
    private var animSubMenuClose: Animation? = null
    private var flagMenuEntryHoldingOpen = false
    private var buttonTasksMenu: Button? = null
    private var buttonEntryMenu: Button? = null
    private var isThemeLight = false
    private var usersLevel = 0
    private var purchasePremiumAccount = false
    private var purchaseAdsOff = false
    var billingClient: BillingClient? = null
    private var myLocale: Locale? = null
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        if (isThemeLight) setTheme(R.style.AppTheme) else setTheme(R.style.AppThemeDark)

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

        isFirstEnterForInitialization = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, true)
        purchaseAdsOff = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)
        isFirstEnterForInstructions = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER3, true)

        instructionLayout = findViewById(R.id.ObychLayout)
        if (isFirstEnterForInstructions) {
            instructionImage = findViewById(R.id.imageView1)
            instructionLayout?.visibility = View.VISIBLE
            instructionLayoutText = findViewById(R.id.ObychLayoutText)
            instructionLayoutText?.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.obych,
                null
            )
            instructionText = findViewById(R.id.ObychText)
            instructionText?.text = resources.getString(R.string.Ob0)
            obNo = 0
        }

        //поставить переключатель темы в нужное положение
        switchTheme?.isChecked = !isThemeLight
        loadLocale()
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
            changeLang(lang)
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
        val buttonNL = findViewById<Button>(R.id.buttonNL)
        buttonNL.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, NLActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
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
        val buttonTaskCalc = findViewById<Button>(R.id.buttonCalc)
        buttonTaskCalc.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, CalculActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonTaskConstruct = findViewById<Button>(R.id.buttonConstruct)
        buttonTaskConstruct.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, ConstructActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonTaskTest = findViewById<Button>(R.id.buttonTest)
        buttonTaskTest.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TestActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonTaskTraining = findViewById<Button>(R.id.buttonTraining)
        buttonTaskTraining.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TrainingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonTaskInstructions = findViewById<Button>(R.id.buttonInstructions)
        buttonTaskInstructions.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, InstructActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
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
        val buttonEntryVis = findViewById<Button>(R.id.button_EH)
        buttonEntryVis.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TestEntryActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonEntryTest = findViewById<Button>(R.id.button_EH_task)
        buttonEntryTest.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TestEntryActivityTask::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonLZPTest = findViewById<Button>(R.id.buttonLZPTest)
        buttonLZPTest.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TestLZPActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonWindCalc = findViewById<Button>(R.id.buttonWind)
        buttonWindCalc.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, WindActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonTimeCalc = findViewById<Button>(R.id.buttonTimeCalc)
        buttonTimeCalc.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, TimeCalcActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonStat = findViewById<Button>(R.id.buttonStat)
        buttonStat.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, StatActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonExit = findViewById<Button>(R.id.buttonExit)
        buttonExit.setOnClickListener { finish() }
        val buttonShop = findViewById<Button>(R.id.buttonShop)
        buttonShop.setOnClickListener {
            finish()
            val intent = Intent(this@MainActivity, ShopActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out, R.anim.act_in)
        }
        val buttonSkip = findViewById<Button>(R.id.buttonskip)
        buttonSkip.setOnClickListener {
            obNo = 7
            instructionLayout?.performClick()
        }
        instructionLayout?.setOnClickListener {
            obNo++
            if (obNo <= 8) {
                if (obNo == 1) {
                    instructionImage!!.visibility = View.VISIBLE
                }
                if (obNo == 2) {
                    instructionImage!!.visibility = View.INVISIBLE
                    instructionImage = findViewById(R.id.imageView2)
                    instructionImage?.rotation = 180f
                    instructionImage?.visibility = View.VISIBLE
                }
                if (obNo == 3) {
                    instructionImage!!.visibility = View.INVISIBLE
                    buttonTasksMenu?.performClick()
                    instructionImage = findViewById(R.id.imageView3)
                    instructionImage?.rotation = 180f
                    instructionImage?.visibility = View.VISIBLE
                }
                if (obNo == 4) {
                    instructionImage!!.visibility = View.INVISIBLE
                    instructionImage = findViewById(R.id.imageView4)
                    buttonEntryMenu?.performClick()
                    instructionImage?.rotation = 180f
                    instructionImage?.visibility = View.VISIBLE
                }
                if (obNo == 5) {
                    instructionImage!!.visibility = View.INVISIBLE
                    instructionImage = findViewById(R.id.imageView5)
                    buttonEntryMenu?.performClick()
                    instructionImage?.rotation = 180f
                    instructionImage?.visibility = View.VISIBLE
                    instructionLayout?.gravity = Gravity.CENTER or Gravity.TOP
                }
                if (obNo == 6) {
                    instructionImage!!.visibility = View.INVISIBLE
                    instructionImage = findViewById(R.id.imageView6)
                    instructionImage?.rotation = 180f
                    instructionImage?.visibility = View.VISIBLE
                }
                if (obNo == 7) {
                    instructionImage!!.visibility = View.INVISIBLE
                    instructionImage = findViewById(R.id.imageView7)
                    instructionImage?.rotation = 90f
                    instructionImage?.visibility = View.VISIBLE
                }
                instructionText!!.text = resources.getString(
                    resources.getIdentifier(
                        "@string/Ob$obNo",
                        "id",
                        packageName
                    )
                )
                if (obNo == 8) {
                    instructionImage!!.visibility = View.INVISIBLE
                    isFirstEnterForInstructions = false
                    mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER3, false)

                    if (isFirstEnterForInitialization) {
                        mDataFiles.checkDataFiles()
                        setupBillingClient()
                        mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, false)
                    }
                    buttonSkip.visibility = View.INVISIBLE
                }
            } else {
                instructionLayout?.visibility = View.INVISIBLE
            }
        }
        //-----------------------------------------------------------------------
    }

    private fun updateTheme(emptyForLightOneForDarkTheme: String) {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = getColor(resources.getIdentifier("colorPrimary$emptyForLightOneForDarkTheme", "color", packageName))
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(getColor(resources.getIdentifier("colorPrimary$emptyForLightOneForDarkTheme", "color", packageName))))
        val background = findViewById<RelativeLayout>(R.id.BackAll)
        background.setBackgroundColor(getColor(resources.getIdentifier("background$emptyForLightOneForDarkTheme", "color", packageName)))
        var backgroundSubMenu = findViewById<LinearLayout>(R.id.undermenu_EH)
        backgroundSubMenu.setBackgroundColor(getColor(resources.getIdentifier("backgroundbuttonpressed$emptyForLightOneForDarkTheme", "color", packageName)))
        backgroundSubMenu = findViewById(R.id.undermenu_task)
        backgroundSubMenu.setBackgroundColor(getColor(resources.getIdentifier("backgroundbuttonpressed$emptyForLightOneForDarkTheme", "color", packageName)))
        switchTheme!!.setTextColor(getColor(resources.getIdentifier("textsimple$emptyForLightOneForDarkTheme", "color", packageName)))
        switchLang!!.setTextColor(getColor(resources.getIdentifier("textsimple$emptyForLightOneForDarkTheme", "color", packageName)))


        for ((buttonId) in mapButtonsToTextKeys) {
            val buttonForThemeUpdate = findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonForThemeUpdate.background.setColorFilter(
                getColor(resources.getIdentifier("backgroundbutton$emptyForLightOneForDarkTheme", "color", packageName)),
                PorterDuff.Mode.MULTIPLY
            )
            buttonForThemeUpdate.setTextColor(getColor(resources.getIdentifier("textsimple$emptyForLightOneForDarkTheme", "color", packageName)))

            if (buttonId == "buttonTasks" && flagMenuTaskOpen || buttonId == "buttonEntryTest" && flagMenuEntryHoldingOpen) {
                buttonForThemeUpdate.background.setColorFilter(
                    getColor(resources.getIdentifier("backgroundbuttonpressed$emptyForLightOneForDarkTheme", "color", packageName)),
                    PorterDuff.Mode.MULTIPLY
                )
            }
        }
    }

    private fun changeLang(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        saveLocale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        updateTexts()
    }

    private fun saveLocale(lang: String?) {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(langPref, lang)
        editor.apply()
    }

    private fun loadLocale() {
        val langPref = "Language"
        val prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE)
        val language = prefs.getString(langPref, "")
        val button1 = findViewById<Button>(R.id.buttonShop)
        val mDataFiles = SharedPreferencesProcessor(this)
        if (language != null) changeLang(language) else changeLang("ru")
        if (button1.text == "Shop") {
            switchLang!!.isChecked = true
            mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, false)
        }
        if (button1.text == "Магазин") {
            switchLang!!.isChecked = false
            mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, true)
        }
    }

    private val mapButtonsToTextKeys = mapOf("buttonShop" to "shop", "buttonCalc" to "calcul",
        "buttonConstruct" to "constructor", "buttonTest" to "test", "buttonTraining" to "training",
        "buttonStat" to "statistics", "buttonInstructions" to "instructions", "buttonLZPTest" to "testLZP",
        "buttonEntryTest" to "EntryTest", "buttonNL" to "NL", "buttonExit" to "exit",
        "buttonTasks" to "tasks", "buttonWind" to "wind", "button_EH" to "EH",
        "buttonTimeCalc" to "TimeCalcMen", "button_EH_task" to "EH_task")
    private fun updateTexts() {
        var switcher = findViewById<SwitchCompat>(R.id.switchLang)
        switcher.setText(R.string.switchLang)
        switcher = findViewById(R.id.switchTheme)
        if (switcher.isChecked) {
            switcher.setText(R.string.switchThemeDark)
        } else {
            switcher.setText(R.string.switchThemeLight)
        }
        for ((buttonId, stringId) in mapButtonsToTextKeys) {
            val buttonForUpdateText = findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonForUpdateText.setText(resources.getIdentifier(stringId, "string", packageName))
        }
    }

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
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, purchaseAdsOff)
            }
            if (purchase.sku == "prem_pass") {
                purchasePremiumAccount = true
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
            }
            if (purchase.sku == "perm_max_lvl") {
                usersLevel = 7
                mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, usersLevel)
            }
            if (purchase.sku == "na_pecheni") {
                purchaseAdsOff = true
                mAdView!!.visibility = View.INVISIBLE
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, purchaseAdsOff)
            }
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, list: List<Purchase>?) {}
}