package com.MaxEle.maximarius.nir_navigation

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.content.Intent
import android.view.View
import android.widget.Button
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.google.android.gms.ads.AdRequest
import java.lang.StringBuilder
import java.util.*
import kotlin.math.abs

class TimeCalcActivity : AppCompatActivity() {
    private var isThemeLight = false
    private var currentNumb: TextView? = null
    private var allNumb: TextView? = null
    private var currentNumbStr: String? = null
    private var allNumbStr: String? = null
    private var flagravno = false
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_time_calc)

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView?.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView?.loadAd(adRequest)
        }

        currentNumb = findViewById(R.id.CurrTime)
        allNumb = findViewById(R.id.AllTime)

        //-------------------------------------------------------------------------
        // ButtonListeners для данной активности
        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
            val intent = Intent(this@TimeCalcActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
        }

        val mapOfButtonToNumberKeys = mapOf(
            "button0" to '0',
            "button1" to '1',
            "button2" to '2',
            "button3" to '3',
            "button4" to '4',
            "button5" to '5',
            "button6" to '6',
            "button7" to '7',
            "button8" to '8',
            "button9" to '9'
        )
        for ((buttonId, numberChar) in mapOfButtonToNumberKeys) {
            val buttonToListen =
                findViewById<Button>(resources.getIdentifier(buttonId, "id", packageName))
            buttonToListen.setOnClickListener {
                whenNumberPressed(numberChar)
            }
        }

        val buttonClear = findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener { clearAll() }
        val buttonMinus = findViewById<Button>(R.id.buttonminus)
        buttonMinus.setOnClickListener {
            allNumbStr = allNumb?.text.toString()
            currentNumbStr = currentNumb?.text.toString()
            if (flagravno) {
                allNumb?.text = ""
                allNumbStr = ""
                flagravno = false
                allNumbStr = "$allNumbStr$currentNumbStr-"
            } else {
                if (currentNumbStr == resources.getString(R.string.TimeCalc00_00)) {
                    if (allNumbStr != "") if (allNumbStr!![allNumbStr!!.length - 1] == '+' || allNumbStr!![allNumbStr!!.length - 1] == '-') {
                        allNumbStr = allNumbStr!!.substring(0, allNumbStr!!.length - 1)
                    }
                    allNumbStr = "$allNumbStr-"
                } else {
                    allNumbStr = "$allNumbStr$currentNumbStr-"
                }
            }
            allNumb?.text = allNumbStr
            currentNumb?.text = resources.getString(R.string.TimeCalc00_00)
        }
        val buttonPlus = findViewById<Button>(R.id.buttonplus)
        buttonPlus.setOnClickListener {
            allNumbStr = allNumb?.text.toString()
            currentNumbStr = currentNumb?.text.toString()
            if (flagravno) {
                allNumb?.text = ""
                allNumbStr = ""
                flagravno = false
                allNumbStr = "$allNumbStr$currentNumbStr+"
            } else {
                if (currentNumbStr == resources.getString(R.string.TimeCalc00_00)) {
                    if (allNumbStr != "") if (allNumbStr!![allNumbStr!!.length - 1] == '+' || allNumbStr!![allNumbStr!!.length - 1] == '-') {
                        allNumbStr = allNumbStr!!.substring(0, allNumbStr!!.length - 1)
                    }
                    allNumbStr = "$allNumbStr+"
                } else {
                    allNumbStr = "$allNumbStr$currentNumbStr+"
                }
            }
            allNumb?.text = allNumbStr
            currentNumb?.text = resources.getString(R.string.TimeCalc00_00)
        }
        val buttonEquals = findViewById<Button>(R.id.buttonequ)
        buttonEquals.setOnClickListener {
            if (!flagravno) {
                allNumbStr = allNumb?.text.toString()
                currentNumbStr = currentNumb?.text.toString()
                allNumbStr = "$allNumbStr$currentNumbStr="
                var input: String? = allNumbStr
                var value = ""
                var valueSize = 0
                val times: MutableList<Time> = ArrayList()
                var firstNumSize: Int
                if (!input!!.startsWith("+") && !input.startsWith("-")) {
                    if (input.split("+","-","=").toTypedArray()[0].length.also {
                            firstNumSize = it
                        } > 5) {
                        times.add(Time(input.substring(0, firstNumSize)))
                        input = input.substring(firstNumSize)
                    } else {
                        times.add(Time(input.substring(0, 5)))
                        input = input.substring(5)
                    }
                } else {
                    if (input.split("+","-","=").toTypedArray()[1].length.also {
                            firstNumSize = it
                        } > 5) {
                        firstNumSize++
                        times.add(Time(input.substring(0, firstNumSize)))
                        input = input.substring(firstNumSize)
                    }
                }
                for (c in input.toCharArray()) {
                    value += c.toString()
                    valueSize++
                    if (valueSize == 6) {
                        times.add(Time(value))
                        value = ""
                        valueSize = 0
                    }
                }
                allNumbStr += calculateTime(times)
                allNumb?.text = allNumbStr
                flagravno = true
            }
        }

        //-----------------------------------------------------------------------
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@TimeCalcActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.act_out_back, R.anim.act_in_back)
    }

    public override fun onDestroy() {
        mAdView!!.destroy()
        super.onDestroy()
    }

    fun clearAll() {
        allNumb!!.text = ""
        currentNumb!!.setText(R.string.TimeCalc00_00)
        currentNumbStr = ""
        allNumbStr = ""
        flagravno = false
    }

    class Time(time: String) {
        var sign = '+'
        var timeInMins = 0

        init {
            if (time[0] == '-') {
                sign = '-'
                timeInMins =
                    time.split(":").toTypedArray()[0].substring(1).toInt() * 60 + time.split(":")
                        .toTypedArray()[1].toInt()
            } else if (time[0] == '+') {
                timeInMins =
                    time.split(":").toTypedArray()[0].substring(1).toInt() * 60 + time.split(":")
                        .toTypedArray()[1].toInt()
            } else {
                timeInMins = time.split(":").toTypedArray()[0].toInt() * 60 + time.split(":")
                    .toTypedArray()[1].toInt()
            }
        }
    }

    fun calculateTime(times: List<Time>): String {
        var mins = 0
        var time = ""
        var format = "%02d"
        for (t in times) {
            if (t.sign == '+') {
                mins += t.timeInMins
            } else {
                mins -= t.timeInMins
            }
        }
        if (mins < 0) {
            time = "-"
        }
        if (abs(mins / 60).toString().length > 1) {
            format = "%d"
        }
        time = time + String.format(
            Locale.getDefault(),
            format,
            abs(mins / 60)
        ) + ":" + String.format(
            Locale.getDefault(), "%02d", abs(mins % 60)
        )
        currentNumb!!.text = time
        return time
    }

    fun whenNumberPressed(c: Char) {
        if (flagravno) {
            clearAll()
        }
        currentNumbStr = currentNumb!!.text.toString()
        val timeChars = currentNumbStr!!.toCharArray()
        timeChars[0] = timeChars[1]
        timeChars[1] = timeChars[3]
        timeChars[3] = timeChars[4]
        timeChars[4] = c
        currentNumbStr = ""
        val total = StringBuilder()
        for (i in 0..4) {
            total.append(timeChars[i])
        }
        currentNumbStr = total.toString()
        currentNumb!!.text = currentNumbStr
    }

}