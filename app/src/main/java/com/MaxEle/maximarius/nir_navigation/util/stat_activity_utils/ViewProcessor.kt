package com.MaxEle.maximarius.nir_navigation.util.stat_activity_utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import java.util.*

class ViewProcessor(private val activity: Activity, val context: Context, private val packName: String) {

    private fun clearScreen() {
        for (i in 1..8) {
            val textViewSt =
                this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainZad$i", "id", packName))
            textViewSt.visibility = View.GONE
        }

        for (i in 1..4) {
            val textViewSt =
                this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainRec$i", "id", packName))
            textViewSt.visibility = View.GONE
            textViewSt.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0)
        }

        for (i in 1..3) {
            val textViewSt =
                this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainDescription$i", "id", packName))
            textViewSt.visibility = View.GONE
        }
    }

    private val listOfDataNames = listOf(
        SharedPreferencesProcessor.DATA_FILE_ST1, SharedPreferencesProcessor.DATA_FILE_ST2, SharedPreferencesProcessor.DATA_FILE_ST3,
        SharedPreferencesProcessor.DATA_FILE_ST4, SharedPreferencesProcessor.DATA_FILE_ST5, SharedPreferencesProcessor.DATA_FILE_ST6,
        SharedPreferencesProcessor.DATA_FILE_ST7, SharedPreferencesProcessor.DATA_FILE_ST8, SharedPreferencesProcessor.DATA_FILE_ST9,
        SharedPreferencesProcessor.DATA_FILE_ST10, SharedPreferencesProcessor.DATA_FILE_ST11, SharedPreferencesProcessor.DATA_FILE_ST12,
        SharedPreferencesProcessor.DATA_FILE_ST13, SharedPreferencesProcessor.DATA_FILE_ST14, SharedPreferencesProcessor.DATA_FILE_ST15,
        SharedPreferencesProcessor.DATA_FILE_ST16, SharedPreferencesProcessor.DATA_FILE_ST17, SharedPreferencesProcessor.DATA_FILE_ST18)

    fun showStat(numberOfTask: Int, numberOfUsedViews: Int) {
        clearScreen()
        var flagOnly: Boolean? = null
        val mDataFiles = SharedPreferencesProcessor(context)
        val numb = mDataFiles.getStr(listOfDataNames[numberOfTask - 1], "")
        val separated = numb!!.split("/").toTypedArray()

        for (i in 0 until numberOfUsedViews) {
            val textStatNumbers =
                this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainZad${i + 1}", "id", packName))
            textStatNumbers.text = separated[i]
            textStatNumbers.visibility = View.VISIBLE
        }

        var j = 2
        var k = 1
        for (i in 2 until numberOfUsedViews step 2) {
            if (separated[i].toInt() < separated[i + 1].toInt()) {
                flagOnly = false
                val textStatRecommendation =
                    this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainRec${j}", "id", packName))
                textStatRecommendation.visibility = View.VISIBLE
                textStatRecommendation.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                textStatRecommendation.setText(context.resources.getIdentifier(listOfStringIdForTasks[numberOfTask - 1][1][j - 2], "string", packName))
                j++
            }
            val textStatDescription = this.activity.findViewById<TextView>(context.resources.getIdentifier("textViewContainDescription${k}", "id", packName))
            textStatDescription.visibility = View.VISIBLE
            textStatDescription.text = context.getString(context.resources.getIdentifier(listOfStringIdForTasks[numberOfTask - 1][0][k - 1], "string", packName))
            k++
        }

        if (separated[0].toInt() < separated[1].toInt()) {
            val textStatRecommendation = this.activity.findViewById<TextView>(R.id.textViewContainRec1)
            textStatRecommendation.visibility = View.VISIBLE
            textStatRecommendation.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            if (numberOfUsedViews > 2 && flagOnly != false) flagOnly = true
            textStatRecommendation.text = when (flagOnly) {
                true -> context.resources.getString(R.string.recomen1)
                false -> context.resources.getString(R.string.recomen2)
                null -> context.resources.getString(R.string.recomen0)
            }
        }
    }

    private val listOfStringIdForTasks = listOf(
        emptyList(),
        emptyList(),
        emptyList(),
        listOf(listOf("statdoprasBen", "statdoprasYV", "statdoprasYS"), listOf("recomenBen1", "recomenYV", "recomenYS")),
        listOf(listOf("statdoprasUekv", "statdoprasYV"), listOf("recomenUekv", "recomenYV2")),
        listOf(listOf("statdoprasUekv", "statdoprasYV", "statdoprasBen"), listOf("recomenUekv", "recomenYV2", "recomenBen")),
        emptyList(),
        emptyList(),
        listOf(listOf("statdoprasYV", "statdoprasYS"), listOf("recomenYV3", "recomenYS")),
        listOf(listOf("statdoprasHbar", "statdoprasdH", "statdoprastpr"), listOf("recomenHbar", "recomendH", "recomentpr")),
        listOf(listOf("statdoprasdH1", "statdoprasHbar", "statdoprasdH2"), listOf("recomendH1", "recomenHbar", "recomendH2")),
        listOf(listOf("statdoprasSnach", "statdoprasdHsht", "statdoprasSrasp"), listOf("recomenSnach", "recomendHsht", "recomenSrasp")),
        listOf(listOf("statdoprasdVsh", "statdoprasVprisp"), listOf("recomendVsh", "recomenVprisp")),
        listOf(listOf("statdoprasVprisp", "statdoprasdVsh"), listOf("recomenVprisp2", "recomendVsh")),
        listOf(listOf("statdoprasVprispKYS"), listOf("recomenVprispKYS")),
        listOf(listOf("statdoprasDP", "statdoprasLBY", "statdoprasBY"), listOf("recomenDP", "recomenLBY", "recomenBY")),
        listOf(listOf("statdoprasDP", "statdoprasLBY", "statdoprasBY"), listOf("recomenDP", "recomenLBY", "recomenBY")),
        listOf(listOf("statdoprasDP", "statdoprasLBY", "statdoprasBY"), listOf("recomenDP", "recomenLBY", "recomenBY")))


    fun setInfoOnStatOfAll() {
        val mDataFiles = SharedPreferencesProcessor(context)
        val avaLevel = mDataFiles.getInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, 0)
        val corransw = mDataFiles.getInt(SharedPreferencesProcessor.DATA_FILE_CORRECT, 0)
        val wrongansw = mDataFiles.getInt(SharedPreferencesProcessor.DATA_FILE_WRONG, 0)
        val corranswnow = mDataFiles.getInt(SharedPreferencesProcessor.DATA_FILE_CORRECT_NOW, 0)
        val wronganswnow = mDataFiles.getInt(SharedPreferencesProcessor.DATA_FILE_WRONG_NOW, 0)

        var textViewStatAll: TextView = this.activity.findViewById(R.id.textViewContainAll1)
        var a: String = context.resources.getString(R.string.scorecorr) + corransw
        textViewStatAll.text = a
        textViewStatAll = this.activity.findViewById(R.id.textViewContainAll2)
        a = context.resources.getString(R.string.scorewrong) + wrongansw
        textViewStatAll.text = a
        textViewStatAll = this.activity.findViewById(R.id.textViewContainAll3)
        a = context.resources.getString(R.string.scorelvl) + avaLevel
        textViewStatAll.text = a
        textViewStatAll = this.activity.findViewById(R.id.textViewContainAll4)
        val dops: Double = if (wrongansw + corransw != 0) {
            (wrongansw * 100 / (wrongansw + corransw)).toDouble()
        } else {
            100.0
        }
        a = context.resources.getString(R.string.scordopspos) + String.format(
            Locale.getDefault(),
            "%.0f",
            +dops
        ) + "%"
        textViewStatAll.text = a
        textViewStatAll = this.activity.findViewById(R.id.textViewContainNow1)
        a = context.resources.getString(R.string.scorecorr) + corranswnow
        textViewStatAll.text = a
        textViewStatAll = this.activity.findViewById(R.id.textViewContainNow2)
        a = context.resources.getString(R.string.scorewrong) + wronganswnow
        textViewStatAll.text = a
    }
}