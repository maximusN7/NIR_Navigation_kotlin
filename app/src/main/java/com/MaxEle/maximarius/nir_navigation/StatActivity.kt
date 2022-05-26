package com.MaxEle.maximarius.nir_navigation

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Spinner
import android.content.SharedPreferences
import android.os.Bundle
import com.MaxEle.maximarius.nir_navigation.StatActivity
import com.MaxEle.maximarius.nir_navigation.R
import com.google.android.gms.ads.AdView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.AdapterView
import android.widget.LinearLayout
import android.content.Intent
import android.view.View
import com.MaxEle.maximarius.nir_navigation.MainActivity
import com.google.android.gms.ads.AdRequest
import java.util.*

class StatActivity : AppCompatActivity() {
    var Theme_Light = false
    var typeoftaskglob = 0
    var spinType: Spinner? = null
    var spinText: String? = null
    var mDataFiles: SharedPreferences? = null
    var AvaLevel = 0
    var corransw = 0
    var wrongansw = 0
    var corranswnow = 0
    var wronganswnow = 0
    var PremAkk = false
    var Password: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataFiles = getSharedPreferences(DATA_FILE, MODE_PRIVATE)
        if (mDataFiles.contains(DATA_FILE_THEME_LIGHT)) {
            Theme_Light = mDataFiles.getBoolean(DATA_FILE_THEME_LIGHT, true)
        } else {
            Theme_Light = true
            val editor = mDataFiles.edit()
            editor.putBoolean(DATA_FILE_THEME_LIGHT, Theme_Light)
            editor.apply()
        }
        if (Theme_Light) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(R.style.AppThemeDark)
        }
        setContentView(R.layout.activity_stat)
        val mAdView = findViewById<AdView>(R.id.banner_ad)
        val AdsDis = mDataFiles.getBoolean(DATA_FILE_ADS_DISABLE, false)
        if (AdsDis) {
            mAdView.visibility = View.INVISIBLE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }
        mDataFiles = getSharedPreferences(DATA_FILE, MODE_PRIVATE)
        Password = Dialog(this@StatActivity)
        Password!!.setTitle(resources.getString(R.string.EntPas))
        Password!!.setContentView(R.layout.dialog_view)
        var viewansw = findViewById<ScrollView>(R.id.view1)
        if (Theme_Light) {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview))
            viewansw = findViewById(R.id.view2)
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview))
        } else {
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview1))
            viewansw = findViewById(R.id.view2)
            viewansw.setBackgroundColor(resources.getColor(R.color.backgroundview1))
        }
        ProverkaDataFiles()
        var TextStatAll: TextView
        var a: String
        TextStatAll = findViewById(R.id.textViewContainAll1)
        a = resources.getString(R.string.scorecorr) + corransw
        TextStatAll.text = a
        TextStatAll = findViewById(R.id.textViewContainAll2)
        a = resources.getString(R.string.scorewrong) + wrongansw
        TextStatAll.text = a
        TextStatAll = findViewById(R.id.textViewContainAll3)
        a = resources.getString(R.string.scorelvl) + AvaLevel
        TextStatAll.text = a
        TextStatAll = findViewById(R.id.textViewContainAll4)
        val Dops: Double
        Dops = if (wrongansw + corransw != 0) {
            (wrongansw * 100 / (wrongansw + corransw)).toDouble()
        } else {
            100.0
        }
        a = resources.getString(R.string.scordopspos) + String.format(
            Locale.getDefault(),
            "%.0f",
            +Dops
        ) + "%"
        TextStatAll.text = a
        TextStatAll = findViewById(R.id.textViewContainNow1)
        a = resources.getString(R.string.scorecorr) + corranswnow
        TextStatAll.text = a
        TextStatAll = findViewById(R.id.textViewContainNow2)
        a = resources.getString(R.string.scorewrong) + wronganswnow
        TextStatAll.text = a
        spinType = findViewById(R.id.taskslist)
        val typetask = resources.getStringArray(R.array.taskstypes)
        spinType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                spinText = spinType.getSelectedItem().toString()
                if (spinText == typetask[0]) {
                    typeoftaskglob = 1
                    VivodStatZad()
                }
                if (spinText == typetask[1]) {
                    typeoftaskglob = 2
                    VivodStatZad()
                }
                if (spinText == typetask[2]) {
                    typeoftaskglob = 3
                    VivodStatZad()
                }
                if (spinText == typetask[3]) {
                    typeoftaskglob = 4
                    VivodStatZad()
                }
                if (spinText == typetask[4]) {
                    typeoftaskglob = 5
                    VivodStatZad()
                }
                if (spinText == typetask[5]) {
                    typeoftaskglob = 6
                    VivodStatZad()
                }
                if (spinText == typetask[6]) {
                    typeoftaskglob = 7
                    VivodStatZad()
                }
                if (spinText == typetask[7]) {
                    typeoftaskglob = 8
                    VivodStatZad()
                }
                if (spinText == typetask[8]) {
                    typeoftaskglob = 9
                    VivodStatZad()
                }
                if (spinText == typetask[9]) {
                    typeoftaskglob = 10
                    VivodStatZad()
                }
                if (spinText == typetask[10]) {
                    typeoftaskglob = 11
                    VivodStatZad()
                }
                if (spinText == typetask[11]) {
                    typeoftaskglob = 12
                    VivodStatZad()
                }
                if (spinText == typetask[12]) {
                    typeoftaskglob = 13
                    VivodStatZad()
                }
                if (spinText == typetask[13]) {
                    typeoftaskglob = 14
                    VivodStatZad()
                }
                if (spinText == typetask[14]) {
                    typeoftaskglob = 15
                    VivodStatZad()
                }
                if (spinText == typetask[15]) {
                    typeoftaskglob = 16
                    VivodStatZad()
                }
                if (spinText == typetask[16]) {
                    typeoftaskglob = 17
                    VivodStatZad()
                }
                if (spinText == typetask[17]) {
                    typeoftaskglob = 18
                    VivodStatZad()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    fun Obnyl() {
        var TextSt: TextView
        TextSt = findViewById(R.id.textViewContainZad7)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad8)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad9)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad10)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad11)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad12)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad13)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad14)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainZad15)
        TextSt.visibility = View.INVISIBLE
        TextSt = findViewById(R.id.textViewContainRec1)
        TextSt.visibility = View.INVISIBLE
        TextSt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        TextSt = findViewById(R.id.textViewContainRec2)
        TextSt.visibility = View.INVISIBLE
        TextSt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        TextSt = findViewById(R.id.textViewContainRec3)
        TextSt.visibility = View.INVISIBLE
        TextSt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        TextSt = findViewById(R.id.textViewContainRec4)
        TextSt.visibility = View.INVISIBLE
        TextSt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
    }

    fun VivodStatZad() {
        val numb: String?
        var TextStatAll: TextView
        val separated: Array<String>
        Obnyl()
        var flagOnly = true
        when (typeoftaskglob) {
            1 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST1, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomen0)
                }
            }
            2 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST2, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomen0)
                }
            }
            3 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST3, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomen0)
                }
            }
            4 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST4, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasBen)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYV)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYS)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenBen1)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYV)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYS)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            5 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST5, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasUekv)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYV)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenUekv)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYV2)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            6 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST6, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasUekv)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYV)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasBen)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenUekv)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYV2)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenBen)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            7 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST7, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomen0)
                }
            }
            8 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST8, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomen0)
                }
            }
            9 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST9, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYV)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasYS)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYV3)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenYS)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            10 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST10, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasHbar)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdH)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprastpr)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenHbar)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendH)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomentpr)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            11 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST11, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdH1)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasHbar)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdH2)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendH1)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenHbar)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendH2)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            12 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST12, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasSnach)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdHsht)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasSrasp)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenSnach)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendHsht)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenSrasp)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            13 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST13, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdVsh)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasVprisp)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendVsh)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenVprisp)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            14 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST14, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasVprisp)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasdVsh)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenVprisp2)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomendVsh)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            15 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST15, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasVprispKYS)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenVprispKYS)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            16 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST16, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasDP)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasLBY)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasBY)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenDP)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenLBY)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenBY)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            17 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST17, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasDP)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasLBY)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasBY)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenDP)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenLBY)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenBY)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
            18 -> {
                numb = mDataFiles!!.getString(DATA_FILE_ST18, "")
                separated = numb!!.split("/").toTypedArray()
                TextStatAll = findViewById(R.id.textViewContainZad5)
                TextStatAll.text = separated[0]
                TextStatAll = findViewById(R.id.textViewContainZad6)
                TextStatAll.text = separated[1]
                TextStatAll = findViewById(R.id.textViewContainZad7)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasDP)
                TextStatAll = findViewById(R.id.textViewContainZad8)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[2]
                TextStatAll = findViewById(R.id.textViewContainZad9)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[3]
                TextStatAll = findViewById(R.id.textViewContainZad10)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasLBY)
                TextStatAll = findViewById(R.id.textViewContainZad11)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[4]
                TextStatAll = findViewById(R.id.textViewContainZad12)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[5]
                TextStatAll = findViewById(R.id.textViewContainZad13)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = resources.getString(R.string.statdoprasBY)
                TextStatAll = findViewById(R.id.textViewContainZad14)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[6]
                TextStatAll = findViewById(R.id.textViewContainZad15)
                TextStatAll.visibility = View.VISIBLE
                TextStatAll.text = separated[7]
                if (separated[2].toInt() < separated[3].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec2)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenDP)
                }
                if (separated[4].toInt() < separated[5].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec3)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenLBY)
                }
                if (separated[6].toInt() < separated[7].toInt()) {
                    flagOnly = false
                    TextStatAll = findViewById(R.id.textViewContainRec4)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    TextStatAll.text = resources.getString(R.string.recomenBY)
                }
                if (separated[0].toInt() < separated[1].toInt()) {
                    TextStatAll = findViewById(R.id.textViewContainRec1)
                    TextStatAll.visibility = View.VISIBLE
                    TextStatAll.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    if (flagOnly) {
                        TextStatAll.text = resources.getString(R.string.recomen1)
                    } else {
                        TextStatAll.text = resources.getString(R.string.recomen2)
                    }
                }
            }
        }
    }

    fun ProverkaDataFiles() {
        mDataFiles = getSharedPreferences(DATA_FILE, MODE_PRIVATE)
        AvaLevel = mDataFiles.getInt(DATA_FILE_LEVEL, 0)
        corransw = mDataFiles.getInt(DATA_FILE_CORRECT, 0)
        wrongansw = mDataFiles.getInt(DATA_FILE_WRONG, 0)
        corranswnow = mDataFiles.getInt(DATA_FILE_CORRECT_NOW, 0)
        wronganswnow = mDataFiles.getInt(DATA_FILE_WRONG_NOW, 0)
        PremAkk = mDataFiles.getBoolean(DATA_FILE_PREMIUM, false)
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

    companion object {
        const val DATA_FILE = "datafile"
        const val DATA_FILE_CORRECT = "correct"
        const val DATA_FILE_WRONG = "wrong"
        const val DATA_FILE_CORRECT_NOW = "correctnow"
        const val DATA_FILE_WRONG_NOW = "wrongnow"
        const val DATA_FILE_LEVEL = "level"
        const val DATA_FILE_PREMIUM = "premakk"
        const val DATA_FILE_THEME_LIGHT = "theme_light"
        const val DATA_FILE_ADS_DISABLE = "ads_disable"
        const val DATA_FILE_ST1 = "st1"
        const val DATA_FILE_ST2 = "st2"
        const val DATA_FILE_ST3 = "st3"
        const val DATA_FILE_ST4 = "st4"
        const val DATA_FILE_ST5 = "st5"
        const val DATA_FILE_ST6 = "st6"
        const val DATA_FILE_ST7 = "st7"
        const val DATA_FILE_ST8 = "st8"
        const val DATA_FILE_ST9 = "st9"
        const val DATA_FILE_ST10 = "st10"
        const val DATA_FILE_ST11 = "st11"
        const val DATA_FILE_ST12 = "st12"
        const val DATA_FILE_ST13 = "st13"
        const val DATA_FILE_ST14 = "st14"
        const val DATA_FILE_ST15 = "st15"
        const val DATA_FILE_ST16 = "st16"
        const val DATA_FILE_ST17 = "st17"
        const val DATA_FILE_ST18 = "st18"
    }
}