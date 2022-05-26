package com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils

import android.app.Activity
import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.UtilsForCalculations

class TaskProcessor(
    private val activity: Activity,
    val context: Context,
    private val packName: String
) {
    private var answ: Byte = 0
    private var nom: Byte = 1
    private var corr: Byte = 0
    private var wron: Byte = 0
    private var isNewSession = true

    private var mChronometer: Chronometer? = null
    private var running = false
    private var elapsedMillis: Long = 0
    private var obshVR: Short = 0
    private var isInProgressFlag = false

    private fun fillThreeConditionsTextView(str1: String, str2: String, str3: String) {
        val strList = listOf(str1, str2, str3)
        for (i in 1..3) {
            val conditionTextView =
                this.activity.findViewById<TextView>(
                    context.resources.getIdentifier(
                        "textViewEdIzm$i",
                        "id",
                        packName
                    )
                )
            conditionTextView.text = strList[i - 1]
        }

    }

    fun checkAnswer(rightAnswer: Byte, stringId: String) {
        if (isInProgressFlag) {
            val nextBut: Button = this.activity.findViewById(R.id.button2)
            val rezView: TextView = this.activity.findViewById(R.id.textViewRez)
            val message: String
            elapsedMillis = SystemClock.elapsedRealtime() - mChronometer!!.base
            val a: Short = (elapsedMillis / 1000).toShort()
            obshVR = (obshVR + a).toShort()
            if (running) {
                mChronometer!!.base = SystemClock.elapsedRealtime()
                elapsedMillis = 0
                mChronometer!!.stop()
                running = false
            }
            nextBut.text = context.resources.getString(R.string.testLZPnext)
            nextBut.visibility = View.VISIBLE
            if (answ == rightAnswer) {
                message =
                    context.resources.getString(R.string.testLZP_corr) + context.resources.getString(context.resources.getIdentifier(stringId, "string", packName)) + a + context.resources.getString(
                        R.string.testLZP_sec
                    )
                rezView.text = message
                corr++
            } else {
                message =
                    context.resources.getString(R.string.testLZP_wron) + context.resources.getString(context.resources.getIdentifier(stringId, "string", packName)) + a + context.resources.getString(
                        R.string.testLZP_sec
                    )
                rezView.text = message
                wron++
            }
            nom++
            isInProgressFlag = false
        }
    }

    private fun endOfTraining() {
        val nextBut: Button = this.activity.findViewById(R.id.button2)
        val rezView: TextView = this.activity.findViewById(R.id.textViewRez)
        isInProgressFlag = false
        fillThreeConditionsTextView("", "", "")
        mChronometer!!.base = SystemClock.elapsedRealtime()
        nextBut.text = context.resources.getString(R.string.testLZPstart)
        val b: Byte = (corr * 5).toByte()
        val massage =
            context.resources.getString(R.string.testLZP_totalcorr) + b + context.resources.getString(R.string.testLZP_perccorr) + obshVR + context.resources.getString(
                R.string.testLZP_sec
            )
        rezView.text = massage
        nom = 1
        obshVR = 0
    }

    fun createNextTask() {
        val nextBut: Button = this.activity.findViewById(R.id.button2)
        val rezView: TextView = this.activity.findViewById(R.id.textViewRez)
        if (isNewSession) {
            mChronometer = this.activity.findViewById(R.id.chronometer)
            isNewSession = false
        }
        isInProgressFlag = true
        if (nom.toInt() == 21) {
            endOfTraining()
            isNewSession = true
        } else {
            val stringNumberOfTask: String = context.resources.getString(R.string.testLZP_zad) + nom + context.resources.getString(R.string.testLZP_of20)
            rezView.text = stringNumberOfTask
            var ZMPY: Short
            val KYR: Short
            val pom1: Short
            val MK = (Math.random() * 360).toInt().toShort()
            val r1 = (1 + (Math.random() * 3).toInt()).toByte()
            val razn = (-30 + (Math.random() * 61).toInt()).toShort()
            KYR = when (r1.toInt()) {
                1 -> (330 + (Math.random() * 30).toInt()).toShort()
                2 -> (Math.random() * 31).toInt().toShort()
                3 -> (170 + (Math.random() * 21).toInt()).toShort()
                else -> 0
            }
            pom1 = UtilsForCalculations.makeAngle0To360((MK + KYR).toShort())
            ZMPY = (pom1 + razn).toShort()
            when (r1.toInt()) {
                1, 2 -> {answ = when {
                    pom1 < ZMPY -> 2
                    pom1 > ZMPY -> 1
                    pom1 == ZMPY -> 3
                    else -> 0
                }}
                3 -> {answ = when {
                    pom1 < ZMPY -> 1
                    pom1 > ZMPY -> 2
                    pom1 == ZMPY -> 3
                    else -> 0
                }}
            }
            ZMPY = UtilsForCalculations.makeAngle0To360(ZMPY)
            fillThreeConditionsTextView(ZMPY.toString(), MK.toString(), KYR.toString())

            nextBut.visibility = View.INVISIBLE
            if (!running) {
                mChronometer?.base = SystemClock.elapsedRealtime()
                mChronometer?.start()
                running = true
            }
        }
    }
}