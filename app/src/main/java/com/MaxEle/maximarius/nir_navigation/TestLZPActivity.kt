package com.MaxEle.maximarius.nir_navigation

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Intent
import android.view.View
import android.widget.*
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils.DialogProcessor
import com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils.TaskProcessor
import com.google.android.gms.ads.AdRequest

class TestLZPActivity : AppCompatActivity() {
    private var isThemeLight = false

    private var taskProcessor: TaskProcessor = TaskProcessor(this, this, packageName)
    private var dialogProcessor: DialogProcessor = DialogProcessor(this, packageName)

    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_test_lzp)

        val viewAnswer = findViewById<ConstraintLayout>(R.id.viewcond)
        viewAnswer.setBackgroundColor(getColor(if (isThemeLight) R.color.backgroundview else R.color.backgroundview1))

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        taskProcessor = TaskProcessor(this, this, packageName)
        dialogProcessor = DialogProcessor(this, packageName)
        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
            val intent = Intent(this@TestLZPActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
        }
        val buttonSettings = findViewById<Button>(R.id.buttonInstr)
        buttonSettings.setOnClickListener {
            dialogProcessor.loadInstructionDialog()
            dialogProcessor.showDialog()
        }
        //-------------------------------------------------------------------------
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@TestLZPActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    fun onClickBackDial(view: View?) {
        dialogProcessor.closeDialog()
    }

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

    fun onClickStart_Next(view: View?) {
        taskProcessor.createNextTask()
    }

    fun onClickLeft(view: View?) {
        taskProcessor.checkAnswer(1, "testLZP_answL")
    }

    fun onClickRight(view: View?) {
        taskProcessor.checkAnswer(2, "testLZP_answR")
    }

    fun onClickOn(view: View?) {
        taskProcessor.checkAnswer(3, "testLZP_answO")
    }
}