package com.MaxEle.maximarius.nir_navigation.util.testEH_activity_utils

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.UtilsForCalculations
import kotlin.random.Random

class TaskProcessor(
    val context: Context,
    private val activity: Activity,
    private val packName: String,
    private val themeLight: Boolean
) {

    private val buttonOK: Button = this.activity.findViewById(R.id.buttonAnswOK)
    private var rightAnswer = 0

    private fun makeButtonsVisible() {
        buttonOK.visibility = View.VISIBLE
        for (i in 1..3) {
            val buttonForAnswer = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$i",
                    "id",
                    packName
                )
            )
            buttonForAnswer.visibility = View.VISIBLE
            buttonForAnswer.isEnabled = true
        }
    }

    fun makeNewTask() {
        makeButtonsVisible()
        val MKpos = (Math.random() * 360).toInt()
        val MKpol = (Math.random() * 360).toInt()
        var k = (MKpol - MKpos).toFloat()
        k = UtilsForCalculations.makeAngle0To360(k)
        k = -1 * (k - 360)
        val circleLorR = Random.nextBoolean()
        when {
            circleLorR && (k in 0.0..110.0 || k in 290.0..360.0) || !circleLorR && (k in 0.0..70.0 || k in 250.0..360.0) -> {
                rightAnswer = 1
            }
            circleLorR && (k in 180.0..290.0) || !circleLorR && (k in 70.0..180.0) -> {
                rightAnswer = 3
            }
            circleLorR && (k in 110.0..180.0) || !circleLorR && (k in 180.0..250.0) -> {
                rightAnswer = 2
            }
        }
        var s: String = if (circleLorR) {
            context.resources.getString(R.string.leftfortask)
        } else {
            context.resources.getString(R.string.rightfortask)
        }
        s = "${context.resources.getString(R.string.condtfortask1)}$MKpos${context.resources.getString(R.string.typeizm_grad)}\n${context.resources.getString(R.string.condtfortask2)}$MKpol${context.resources.getString(R.string.typeizm_grad)}\n${context.resources.getString(R.string.condtfortask3)}$s\n${context.resources.getString(R.string.condtfortask4)}"

        val conditionTaskTextView = this.activity.findViewById<TextView>(R.id.textViewCondition)
        conditionTaskTextView.text = s
    }

    private fun setDisc() {
        buttonOK.visibility = View.VISIBLE
        for (i in 1..3) {
            val buttonForAnswer = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$i",
                    "id",
                    packName
                )
            )
            buttonForAnswer.isEnabled = false
        }
        rightAnswer = 0
    }

    fun returnButtonColor() {
        val theme = if (themeLight) "" else "1"
        for (i in 1..3) {
            val answerButton = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$i",
                    "id",
                    packName
                )
            )
            answerButton.background.setColorFilter(
                context.resources.getColor(
                    context.resources.getIdentifier(
                        "backgroundbutton$theme",
                        "color",
                        packName
                    )
                ),
                PorterDuff.Mode.MULTIPLY
            )
        }
    }

    fun checkAnswer(numberOfButton: Int) {
        if (numberOfButton == rightAnswer) {
            val answerButton = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$numberOfButton",
                    "id",
                    packName
                )
            )
            answerButton.background.setColorFilter(
                context.resources.getColor(R.color.correctansw),
                PorterDuff.Mode.MULTIPLY
            )
        } else {
            val answerButton = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$numberOfButton",
                    "id",
                    packName
                )
            )
            answerButton.background.setColorFilter(
                context.resources.getColor(R.color.wrongansw),
                PorterDuff.Mode.MULTIPLY
            )
            val rightAnswerButton = this.activity.findViewById<Button>(
                context.resources.getIdentifier(
                    "buttonAnsw$rightAnswer",
                    "id",
                    packName
                )
            )
            rightAnswerButton.background.setColorFilter(
                context.resources.getColor(R.color.correctansw),
                PorterDuff.Mode.MULTIPLY
            )
        }
        setDisc()
    }
}