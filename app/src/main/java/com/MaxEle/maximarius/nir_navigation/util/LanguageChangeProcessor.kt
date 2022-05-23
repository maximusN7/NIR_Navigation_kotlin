package com.MaxEle.maximarius.nir_navigation.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.contentValuesOf
import com.MaxEle.maximarius.nir_navigation.R
import java.util.*

class LanguageChangeProcessor(val activity: Activity, val context: Context, val packName: String) {
    private fun saveLocale(lang: String?) {
        val langPref = "Language"
        val prefs = context.getSharedPreferences("CommonPrefs", AppCompatActivity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(langPref, lang)
        editor.apply()
    }

    fun loadLocale() {
        val langPref = "Language"
        val prefs = context.getSharedPreferences("CommonPrefs", AppCompatActivity.MODE_PRIVATE)
        val language = prefs.getString(langPref, "")
        val button1 = this.activity.findViewById<Button>(R.id.buttonShop)
        val mDataFiles = SharedPreferencesProcessor(context)
        if (language != null) changeLang(language) else changeLang("ru")
        val switchLang = this.activity.findViewById<SwitchCompat>(R.id.switchLang)
        if (button1.text == "Shop") {
            switchLang!!.isChecked = true
            mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, false)
        }
        if (button1.text == "Магазин") {
            switchLang!!.isChecked = false
            mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, true)
        }
    }

    private val mapButtonsToTextKeys = mapOf(
        "buttonShop" to "shop",
        "buttonCalc" to "calcul",
        "buttonConstruct" to "constructor",
        "buttonTest" to "test",
        "buttonTraining" to "training",
        "buttonStat" to "statistics",
        "buttonInstructions" to "instructions",
        "buttonLZPTest" to "testLZP",
        "buttonEntryTest" to "EntryTest",
        "buttonNL" to "NL",
        "buttonExit" to "exit",
        "buttonTasks" to "tasks",
        "buttonWind" to "wind",
        "button_EH" to "EH",
        "buttonTimeCalc" to "TimeCalcMen",
        "button_EH_task" to "EH_task"
    )
    private fun updateTexts() {
        var switcher = this.activity.findViewById<SwitchCompat>(R.id.switchLang)
        switcher.setText(R.string.switchLang)
        switcher = this.activity.findViewById(R.id.switchTheme)
        if (switcher.isChecked) {
            switcher.setText(R.string.switchThemeDark)
        } else {
            switcher.setText(R.string.switchThemeLight)
        }
        for ((buttonId, stringId) in mapButtonsToTextKeys) {
            val buttonForUpdateText =
                this.activity.findViewById<Button>(context.resources.getIdentifier(buttonId, "id", packName))
            buttonForUpdateText.setText(context.resources.getIdentifier(stringId, "string", packName))
        }
    }

    fun changeLang(lang: String) {
        if (lang.equals("", ignoreCase = true)) return
        val myLocale = Locale(lang)
        saveLocale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        updateTexts()
    }
}