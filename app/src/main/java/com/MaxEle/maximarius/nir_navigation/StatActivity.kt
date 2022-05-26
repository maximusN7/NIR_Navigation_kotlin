package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.content.Intent
import android.view.View
import android.widget.*
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.stat_activity_utils.ViewProcessor
import com.google.android.gms.ads.AdRequest
import java.util.*

class StatActivity : AppCompatActivity() {
    private var isThemeLight = false
    var spinText: String? = null

    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_stat)

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        var viewansw = findViewById<ScrollView>(R.id.scrollViewStatForTask)
        if (isThemeLight) {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview))
            viewansw = findViewById(R.id.view2)
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview))
        } else {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview1))
            viewansw = findViewById(R.id.view2)
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview1))
        }

        val viewProcessor = ViewProcessor(this, this, packageName)

        viewProcessor.setInfoOnStatOfAll()

        val spinType: Spinner = findViewById(R.id.taskslist)
        val typetask = resources.getStringArray(R.array.taskstypes)
        spinType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                when (spinType.selectedItem.toString()) {
                    typetask[0] -> viewProcessor.showStat(1, 2)
                    typetask[1] -> viewProcessor.showStat(2, 2)
                    typetask[2] -> viewProcessor.showStat(3, 2)
                    typetask[3] -> viewProcessor.showStat(4, 8)
                    typetask[4] -> viewProcessor.showStat(5, 6)
                    typetask[5] -> viewProcessor.showStat(6, 8)
                    typetask[6] -> viewProcessor.showStat(7, 2)
                    typetask[7] -> viewProcessor.showStat(8, 2)
                    typetask[8] -> viewProcessor.showStat(9, 6)
                    typetask[9] -> viewProcessor.showStat(10, 8)
                    typetask[10] -> viewProcessor.showStat(11, 8)
                    typetask[11] -> viewProcessor.showStat(12, 8)
                    typetask[12] -> viewProcessor.showStat(13, 6)
                    typetask[13] -> viewProcessor.showStat(14, 6)
                    typetask[14] -> viewProcessor.showStat(15, 4)
                    typetask[15] -> viewProcessor.showStat(16, 8)
                    typetask[16] -> viewProcessor.showStat(17, 8)
                    typetask[17] -> viewProcessor.showStat(18, 8)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@StatActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    fun onClickBack(view: View?) {
        finish()
        val intent = Intent(this@StatActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }
}