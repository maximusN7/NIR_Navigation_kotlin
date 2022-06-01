package com.MaxEle.maximarius.nir_navigation.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.MaxEle.maximarius.nir_navigation.R

class GoToAnotherActivity(val activity: Activity, private val contextFrom: Context, private val activityTo: Class<out AppCompatActivity>) {

    fun start() {
        activity.finish()
        val intent = Intent(contextFrom, activityTo)
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.act_out, R.anim.act_in)
    }
}