package com.MaxEle.maximarius.nir_navigation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class OptionsActivity : AppCompatActivity() {
    var mDataFiles: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@OptionsActivity, StatActivity::class.java)
        startActivity(intent)
    }

    fun onClickBack(view: View?) {
        finish()
        val intent = Intent(this@OptionsActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickOk(view: View?) {
        val text = findViewById<TextView>(R.id.textViewEnterlvl)
        val a = text.text.toString().toInt()
        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_LEVEL, a)
    }

    fun onClickOk2(view: View?) {
        var text = findViewById<TextView>(R.id.textViewEnterResh1)
        val a = text.text.toString().toInt()
        text = findViewById(R.id.textViewEnterResh2)
        val b = text.text.toString().toInt()
        text = findViewById(R.id.textViewEnterResh3)
        val c = text.text.toString().toInt()

        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLRESH1_1, a)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLRESH1_2, b)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLRESH1_3, c)
    }

    fun onClickOk3(view: View?) {
        var text = findViewById<TextView>(R.id.textViewEnterNyshResh1)
        val a = text.text.toString().toInt()
        text = findViewById(R.id.textViewEnterNyshResh2)
        val b = text.text.toString().toInt()
        text = findViewById(R.id.textViewEnterNyshResh3)
        val c = text.text.toString().toInt()

        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLNYSHN1_1, a)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLNYSHN1_2, b)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_KOLNYSHN1_3, c)
    }

    fun onClickOk4(view: View?) {
        val text = findViewById<TextView>(R.id.textViewEnterCurr)
        val a = text.text.toString().toInt()

        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_CORRECT, a)
    }

    fun onClickOk5(view: View?) {
        val text = findViewById<TextView>(R.id.textViewEnterWrong)
        val a = text.text.toString().toInt()

        val mDataFiles = SharedPreferencesProcessor(this)
        mDataFiles.setInt(SharedPreferencesProcessor.DATA_FILE_WRONG, a)
    }
}