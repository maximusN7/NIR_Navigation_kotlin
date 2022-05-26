package com.MaxEle.maximarius.nir_navigation.util.timeCalc_activity_utils

import android.app.Activity
import android.content.Context
import android.widget.TextView
import com.MaxEle.maximarius.nir_navigation.R
import java.lang.StringBuilder
import java.util.*
import kotlin.math.abs

class CalculationProcessor(private val activity: Activity, val context: Context) {
    private var currentNumb: TextView? = this.activity.findViewById(R.id.CurrTime)
    private var allNumb: TextView? = this.activity.findViewById(R.id.AllTime)
    private var currentNumbStr: String? = null
    private var allNumbStr: String? = null
    private var flagravno = false

    fun minus() {
        allNumbStr = allNumb?.text.toString()
        currentNumbStr = currentNumb?.text.toString()
        if (flagravno) {
            allNumb?.text = ""
            allNumbStr = ""
            flagravno = false
            allNumbStr = "$allNumbStr$currentNumbStr-"
        } else {
            if (currentNumbStr == context.resources.getString(R.string.TimeCalc00_00)) {
                if (allNumbStr != "") if (allNumbStr!![allNumbStr!!.length - 1] == '+' || allNumbStr!![allNumbStr!!.length - 1] == '-') {
                    allNumbStr = allNumbStr!!.substring(0, allNumbStr!!.length - 1)
                }
                allNumbStr = "$allNumbStr-"
            } else {
                allNumbStr = "$allNumbStr$currentNumbStr-"
            }
        }
        allNumb?.text = allNumbStr
        currentNumb?.text = context.resources.getString(R.string.TimeCalc00_00)
    }

    fun plus() {
        allNumbStr = allNumb?.text.toString()
        currentNumbStr = currentNumb?.text.toString()
        if (flagravno) {
            allNumb?.text = ""
            allNumbStr = ""
            flagravno = false
            allNumbStr = "$allNumbStr$currentNumbStr+"
        } else {
            if (currentNumbStr == context.resources.getString(R.string.TimeCalc00_00)) {
                if (allNumbStr != "") if (allNumbStr!![allNumbStr!!.length - 1] == '+' || allNumbStr!![allNumbStr!!.length - 1] == '-') {
                    allNumbStr = allNumbStr!!.substring(0, allNumbStr!!.length - 1)
                }
                allNumbStr = "$allNumbStr+"
            } else {
                allNumbStr = "$allNumbStr$currentNumbStr+"
            }
        }
        allNumb?.text = allNumbStr
        currentNumb?.text = context.resources.getString(R.string.TimeCalc00_00)
    }

    fun equals() {
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

    private fun calculateTime(times: List<Time>): String {
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

    fun clearAll() {
        allNumb!!.text = ""
        currentNumb!!.setText(R.string.TimeCalc00_00)
        currentNumbStr = ""
        allNumbStr = ""
        flagravno = false
    }
}