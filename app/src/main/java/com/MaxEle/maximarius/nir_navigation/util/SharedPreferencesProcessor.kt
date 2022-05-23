package com.MaxEle.maximarius.nir_navigation.util

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.MaxEle.maximarius.nir_navigation.MainActivity

class SharedPreferencesProcessor(val context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(DATA_FILE_NAME, Context.MODE_PRIVATE)

    fun sharedPreferenceExist(key: String): Boolean {
        return !preferences.contains(key)
    }

    fun setInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun setBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = true): Boolean {
        return preferences.getBoolean(key, true)
    }

    fun setStr(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStr(key: String): String? {
        return preferences.getString(key, "")
    }

    fun checkDataFiles() {
        this.setInt(DATA_FILE_LEVEL, 1)
        this.setInt(DATA_FILE_LEVEL, 1)
        this.setInt(DATA_FILE_CORRECT, 0)
        this.setInt(DATA_FILE_WRONG, 0)
        this.setInt(DATA_FILE_CORRECT_NOW, 0)
        this.setInt(DATA_FILE_WRONG_NOW, 0)
        this.setInt(DATA_FILE_KOLNYSHN1_1, 5)
        this.setInt(DATA_FILE_KOLNYSHN1_2, 5)
        this.setInt(DATA_FILE_KOLNYSHN1_3, 5)
        this.setInt(DATA_FILE_KOLRESH1_1, 0)
        this.setInt(DATA_FILE_KOLRESH1_2, 0)
        this.setInt(DATA_FILE_KOLRESH1_3, 0)
        this.setStr(DATA_FILE_ST1, "0/0")
        this.setStr(DATA_FILE_ST2, "0/0")
        this.setStr(DATA_FILE_ST3, "0/0")
        this.setStr(DATA_FILE_ST4, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST5, "0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST6, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST7, "0/0")
        this.setStr(DATA_FILE_ST8, "0/0")
        this.setStr(DATA_FILE_ST9, "0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST10, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST11, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST12, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST13, "0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST14, "0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST15, "0/0/0/0")
        this.setStr(DATA_FILE_ST16, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST17, "0/0/0/0/0/0/0/0")
        this.setStr(DATA_FILE_ST18, "0/0/0/0/0/0/0/0")
        this.setBoolean(DATA_FILE_PREMIUM, false)
    }

    companion object {
        const val DATA_FILE_NAME = "datafile"
        const val DATA_FILE_PREMIUM = "premakk"
        const val DATA_FILE_THEME_LIGHT = "theme_light"
        const val DATA_FILE_FIRSTENTER = "firstent"
        const val DATA_FILE_FIRSTENTER3 = "firstent3"
        const val DATA_FILE_CORRECT = "correct"
        const val DATA_FILE_WRONG = "wrong"
        const val DATA_FILE_CORRECT_NOW = "correctnow"
        const val DATA_FILE_WRONG_NOW = "wrongnow"
        const val DATA_FILE_LEVEL = "level"
        const val DATA_FILE_ADS_DISABLE = "ads_disable"
        const val DATA_FILE_LANGUAGE_RUS = "language"
        const val DATA_FILE_KOLRESH1_1 = "kr1_1"
        const val DATA_FILE_KOLRESH1_2 = "kr1_2"
        const val DATA_FILE_KOLRESH1_3 = "kr1_3"
        const val DATA_FILE_KOLNYSHN1_1 = "kn1_1"
        const val DATA_FILE_KOLNYSHN1_2 = "kn1_2"
        const val DATA_FILE_KOLNYSHN1_3 = "kn1_3"
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