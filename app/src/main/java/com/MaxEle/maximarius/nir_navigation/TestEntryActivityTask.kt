package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.content.Intent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.testEH_activity_utils.TaskProcessor
import com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils.DialogProcessor
import com.google.android.gms.ads.AdRequest

class TestEntryActivityTask : AppCompatActivity() {
    private var isThemeLight = false

    private var mAdView: AdView? = null
    private var dialogProcessor: DialogProcessor = DialogProcessor(this, packageName, 4, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_test_entry_task)

        val viewAnswer = findViewById<ConstraintLayout>(R.id.viewCond)
        viewAnswer.setBackgroundColor(getColor(if (isThemeLight) R.color.backgroundview else R.color.backgroundview1))

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }
        dialogProcessor = DialogProcessor(this, packageName, 4, 1)

        val taskProcessor = TaskProcessor(this, this, packageName, isThemeLight)

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        val buttonSettings = findViewById<Button>(R.id.buttonInstrT)
        buttonSettings.setOnClickListener {
            dialogProcessor.showDialog()
        }
        for (i in 1..3) {
            val buttonForAnswer = findViewById<Button>(
                resources.getIdentifier(
                    "buttonAnsw$i",
                    "id",
                    packageName
                )
            )
            buttonForAnswer.setOnClickListener {
                taskProcessor.checkAnswer(i)
            }
        }
        val buttonTask = findViewById<Button>(R.id.buttonTasksT)
        buttonTask.setOnClickListener {
            buttonTask.visibility = View.INVISIBLE
            taskProcessor.makeNewTask()
        }
        val buttonOk = findViewById<Button>(R.id.buttonAnswOK)
        buttonOk.setOnClickListener {
            taskProcessor.returnButtonColor()
            taskProcessor.makeNewTask()
            buttonOk.visibility = View.INVISIBLE
        }

        //-------------------------------------------------------------------------
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@TestEntryActivityTask, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    fun onClickBack(view: View?) {
        finish()
        val intent = Intent(this@TestEntryActivityTask, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    fun onClickBackDial(view: View?) {
        dialogProcessor.closeDialog()
    }
}