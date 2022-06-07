package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils.BillingFunctions
import com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils.ShopDialogProcessor
import com.android.billingclient.api.*
import com.google.android.gms.ads.AdRequest

class ShopActivity : AppCompatActivity(), PurchasesUpdatedListener {

    private var isThemeLight = true
    private var firstenter = false
    private var mAdView: AdView? = null

    private lateinit  var dialogProcessor: ShopDialogProcessor
    private lateinit var billingFunctions: BillingFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_shop)

        val viewCondition = findViewById<ConstraintLayout>(R.id.viewCond)
        viewCondition.setBackgroundColor(getColor(if (isThemeLight) R.color.backgroundview else R.color.backgroundview1))

        billingFunctions = BillingFunctions(this, this, this)
        dialogProcessor = ShopDialogProcessor(this, this, this,billingFunctions)

        dialogProcessor.loadShopListDialog()

        firstenter = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_SHOP, true)

        if (firstenter) {
            generateKeyPass()
        }
        val about = findViewById<TextView>(R.id.textViewAbout)
        val key = mDataFiles.getStr(SharedPreferencesProcessor.DATA_FILE_CODE, "null")
        val mess = resources.getString(R.string.About1) + key + resources.getString(R.string.About2)
        about.text = mess

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
            val intent = Intent(this@ShopActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
        }
        val buttonOk = findViewById<Button>(R.id.button)
        buttonOk.setOnClickListener {
            whenOkPressed()
        }
    }

    fun onClickBackDial(view: View?) {
        dialogProcessor.closeDialog()
    }

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@ShopActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    private fun generateKeyPass() {
        val keys = arrayOf(
            arrayOf("u5", "3t", "8h", "h3"),
            arrayOf("q7", "yf", "5l", "0p"),
            arrayOf("34", "8m", "9s", "p0"),
            arrayOf("7z", "5g", "qe", "2q"),
            arrayOf("c7", "8j", "3f", "80")
        )
        val pass = arrayOf(
            arrayOf("hq", "gt", "zd", "ta"),
            arrayOf("sm", "qh", "ii", "6y"),
            arrayOf("ev", "y4", "kf", "qw"),
            arrayOf("bg", "4h", "9h", "0p"),
            arrayOf("mf", "hr", "6n", "nd")
        )

        val mDataFiles = SharedPreferencesProcessor(this)

        val a = (Math.random() * 5).toInt().toByte()
        val b = (Math.random() * 5).toInt().toByte()
        val c = (Math.random() * 5).toInt().toByte()
        val d = (Math.random() * 5).toInt().toByte()
        var s =
            keys[a.toInt()][0] + keys[b.toInt()][1] + keys[c.toInt()][2] + keys[d.toInt()][3]
        mDataFiles.setStr(SharedPreferencesProcessor.DATA_FILE_CODE, s)
        s = pass[a.toInt()][0] + pass[b.toInt()][1] + pass[c.toInt()][2] + pass[d.toInt()][3]
        mDataFiles.setStr(SharedPreferencesProcessor.DATA_FILE_PASSWORD, s)
        mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_SHOP, false)
    }

    private fun whenOkPressed() {
        val mDataFiles = SharedPreferencesProcessor(this)
        val text = findViewById<TextView>(R.id.textViewEnterPassword)
        val s = text.text.toString()
        val p = mDataFiles.getStr(SharedPreferencesProcessor.DATA_FILE_PASSWORD, "null")

        when (s) {
            "maximusN7" -> {
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
                finish()
                val intent = Intent(this@ShopActivity, OptionsActivity::class.java)
                startActivity(intent)
            }
            "" -> {
                val mestost = resources.getString(R.string.toastPremempt)
                Toast.makeText(
                    applicationContext, mestost,
                    Toast.LENGTH_SHORT
                ).show()
            }
            p -> {
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_PREMIUM, true)
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, true)
                val mess1 = resources.getString(R.string.toastPrem)
                Toast.makeText(
                    applicationContext, mess1,
                    Toast.LENGTH_SHORT
                ).show()
            }
            "m5g4qp" -> {
                mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, 7)
                val mess1 = resources.getString(R.string.yourmaxlvl)
                Toast.makeText(
                    applicationContext, mess1,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val mestost = resources.getString(R.string.toastPremdenn)
                Toast.makeText(
                    applicationContext, mestost,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //--------------------------------------------------------------------------------
    //Работа платежей Google

    override fun onPurchasesUpdated(billingResult: BillingResult, list: List<Purchase>?) {
        billingFunctions.onPurchasesUpdated(billingResult, list)
    }
}