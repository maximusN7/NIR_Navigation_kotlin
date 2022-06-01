package com.MaxEle.maximarius.nir_navigation.util.main_activity_utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.shop_activity_utils.BillingFunctions
import com.android.billingclient.api.PurchasesUpdatedListener

class InstructionsProcessor(val context: Context, val activity: Activity, private val packName: String, private val purchaseUpdateListener: PurchasesUpdatedListener) {
    var obNo = -1

    fun start() {
        val instructionLayout = this.activity.findViewById<LinearLayout>(R.id.ObychLayout)
        obNo++
        when (obNo) {
            0 -> {
                instructionLayout.visibility = View.VISIBLE
                val instructionLayoutText = this.activity.findViewById<RelativeLayout>(R.id.ObychLayoutText)
                instructionLayoutText.background = ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.obych,
                    null
                )
                nextInstruction(obNo)
            }
            1 -> {
                nextInstruction(obNo)
            }
            2 -> {
                nextInstruction(obNo, 180f)
            }
            3 -> {
                val buttonTasksMenu = this.activity.findViewById<Button>(R.id.buttonTasks)
                buttonTasksMenu.performClick()
                nextInstruction(obNo, 180f)
            }
            4 -> {
                val buttonEntryMenu = this.activity.findViewById<Button>(R.id.buttonEntryTest)
                buttonEntryMenu.performClick()
                nextInstruction(obNo, 180f)
            }
            5 -> {
                val buttonEntryMenu = this.activity.findViewById<Button>(R.id.buttonEntryTest)
                buttonEntryMenu.performClick()
                instructionLayout.gravity = Gravity.CENTER or Gravity.TOP
                nextInstruction(obNo, 180f)
            }
            6 -> {
                nextInstruction(obNo, 180f)
            }
            7 -> {
                nextInstruction(obNo, 90f)
            }
            8 -> {
                val mDataFiles = SharedPreferencesProcessor(context)
                mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER_FOR_INSTRUCTIONS, false)

                for (i in 1..7) {
                    val instructionImage = this.activity.findViewById<ImageView>(
                        context.resources.getIdentifier(
                            "imageView${i}",
                            "id",
                            packName
                        )
                    )
                    instructionImage.visibility = View.INVISIBLE
                }
                val isFirstEnterForInitialization =
                    mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, true)
                if (isFirstEnterForInitialization) {
                    mDataFiles.checkDataFiles()
                    val billingClient = BillingFunctions(context, activity, purchaseUpdateListener)
                    billingClient.setupBillingClientForMainActivity()
                    mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_FIRSTENTER, false)
                }
                val buttonSkip = this.activity.findViewById<Button>(R.id.buttonskip)
                buttonSkip.visibility = View.INVISIBLE

                nextInstruction(obNo)
            }
            else -> {
                instructionLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun nextInstruction(obNo: Int, rotationVal: Float = 0f) {
        if (obNo > 0) {
            if (obNo > 1) {
                val instructionImage = this.activity.findViewById<ImageView>(
                    context.resources.getIdentifier(
                        "imageView${obNo - 1}",
                        "id",
                        packName
                    )
                )
                instructionImage.visibility = View.INVISIBLE
            }

            if (obNo < 8) {
                val instructionImage = this.activity.findViewById<ImageView>(
                    context.resources.getIdentifier(
                        "imageView$obNo",
                        "id",
                        packName
                    )
                )
                instructionImage.rotation = rotationVal
                instructionImage.visibility = View.VISIBLE
            }
        }

        val instructionText: TextView = this.activity.findViewById(R.id.ObychText)
        instructionText.text = context.resources.getString(
            context.resources.getIdentifier(
                "@string/Ob$obNo",
                "id",
                packName
            )
        )
    }
}