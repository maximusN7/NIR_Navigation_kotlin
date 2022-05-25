package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.content.Intent
import android.view.View
import android.widget.*
import com.MaxEle.maximarius.nir_navigation.instruction_activity_utils.ViewProcessor
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.google.android.gms.ads.AdRequest

class InstructActivity : AppCompatActivity() {
    private var isThemeLight = false
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_instruct)

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        val viewansw = findViewById<ScrollView>(R.id.viewbasic)
        if (isThemeLight) {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview))
        } else {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview1))
        }
        val viewProcessor = ViewProcessor(this, this, packageName)

        viewProcessor.makeINVISIBLEALL()

        val spinType: Spinner = findViewById(R.id.taskslist)
        val typetask = resources.getStringArray(R.array.taskstypesinstr)
        spinType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                when (spinType.selectedItem.toString()) {
                    typetask[0] -> viewProcessor.showInstructions(2, 0)
                    typetask[1] -> viewProcessor.showInstructions(1, 1)
                    typetask[2] -> viewProcessor.showInstructions(2, 2)
                    typetask[3] -> viewProcessor.showInstructions(2, 3)
                    typetask[4] -> viewProcessor.showInstructions(4, 4)
                    typetask[5] -> viewProcessor.showInstructions(3, 5)
                    typetask[6] -> viewProcessor.showInstructions(4, 6)
                    typetask[7] -> viewProcessor.showInstructions(2, 7)
                    typetask[8] -> viewProcessor.showInstructions(5, 8)
                    typetask[9] -> viewProcessor.showInstructions(5, 9)
                    typetask[10] -> viewProcessor.showInstructions(6, 10)
                    typetask[11] -> viewProcessor.showInstructions(12, 11)
                    typetask[12] -> viewProcessor.showInstructions(10, 12)
                    typetask[13] -> viewProcessor.showInstructions(7, 13)
                    typetask[14] -> viewProcessor.showInstructions(6, 14)
                    typetask[15] -> viewProcessor.showInstructions(5, 15)
                    typetask[16] -> viewProcessor.showInstructions(7, 16)
                    typetask[17] -> viewProcessor.showInstructions(7, 17)
                    typetask[18] -> viewProcessor.showInstructions(5, 18)
                    typetask[19] -> viewProcessor.showInstructions(3, 19)
                    typetask[20] -> viewProcessor.showInstructions(4, 20)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onBackPressed() {
        goBackToMenu()
    }

    fun onClickBack(view: View?) {
        goBackToMenu()
    }

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

    //---------------------------------------------------------------------

    fun onClickObcAgain(view: View?) {
        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setBoolean(
            SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_FOR_INSTRUCTIONS,
            true
        )
        goBackToMenu()
    }

    fun goBackToMenu() {
        finish()
        val intent = Intent(this@InstructActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }
}