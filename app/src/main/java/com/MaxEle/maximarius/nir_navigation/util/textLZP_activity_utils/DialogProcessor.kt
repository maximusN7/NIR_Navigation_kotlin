package com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat.getColor
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class DialogProcessor(val context: Context, val packName: String) {

    private var instract: Dialog? = null

    fun loadInstructionDialog() {
        instract = Dialog(context)
        instract!!.setTitle(context.resources.getString(R.string.instructions))
        instract!!.setContentView(R.layout.dialog_view)

        val viewmain = instract!!.findViewById<LinearLayout>(R.id.viewMain)
        val scrolldialog = instract!!.findViewById<ScrollView>(R.id.scrolldia)
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        if (isThemeLight) {
            viewmain.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("background", "color", packName)))
            scrolldialog.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("backgroundview", "color", packName)))
        } else {
            viewmain.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("background1", "color", packName)))
            scrolldialog.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("backgroundview1", "color", packName)))
        }
        for (i in 1..3) {
            val textViewForLoadInstructions =
                instract!!.findViewById<TextView>(
                    context.resources.getIdentifier(
                        "textViewDescr$i",
                        "id",
                        packName
                    )
                )
            textViewForLoadInstructions.setText(
                context.resources.getIdentifier(
                    "descrtask19_$i",
                    "string",
                    packName
                )
            )
            val imageViewForLoadInstructions = instract!!.findViewById<ImageView>(
                context.resources.getIdentifier(
                    "imageViewIm$i",
                    "id",
                    packName
                )
            )
            imageViewForLoadInstructions.setImageResource(
                context.resources.getIdentifier(
                    "imgdiscr19$i",
                    "drawable",
                    packName
                )
            )
        }
        val textViewForLoadInstructions = instract!!.findViewById<TextView>(R.id.textViewDescr4)
        textViewForLoadInstructions.visibility = View.GONE
        val imageViewForLoadInstructions = instract!!.findViewById<ImageView>(R.id.imageViewIm4)
        imageViewForLoadInstructions.visibility = View.GONE
    }

    fun closeDialog() {
        instract!!.dismiss()
    }
    fun showDialog() {
        instract!!.show()
    }
}