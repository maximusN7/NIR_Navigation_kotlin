package com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class DialogProcessor(val context: Context, private val packName: String, private val numberOfUsedViews: Int, private val numberInArrayOfIDs: Int) {

    private var instracton: Dialog? = null

    private fun loadInstructionDialog() {
        instracton = Dialog(context)
        instracton!!.setTitle(context.resources.getString(R.string.instructions))
        instracton!!.setContentView(R.layout.dialog_view)

        val arrayOfIDs = arrayOf(arrayOf("descrtask19_", "imgdiscr19"),
                                arrayOf("descrtask20_", "instr_krug"))

        val viewMain = instracton!!.findViewById<LinearLayout>(R.id.viewMain)
        val scrollDialog = instracton!!.findViewById<ScrollView>(R.id.scrolldia)
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        if (isThemeLight) {
            viewMain.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("background", "color", packName)))
            scrollDialog.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("backgroundview", "color", packName)))
        } else {
            viewMain.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("background1", "color", packName)))
            scrollDialog.setBackgroundColor(context.resources.getColor(context.resources.getIdentifier("backgroundview1", "color", packName)))
        }

        hideExcessive()

        for (i in 1..numberOfUsedViews) {
            val textViewForLoadInstructions =
                instracton!!.findViewById<TextView>(
                    context.resources.getIdentifier(
                        "textViewDescr$i",
                        "id",
                        packName
                    )
                )
            textViewForLoadInstructions.setText(
                context.resources.getIdentifier(
                    "${arrayOfIDs[numberInArrayOfIDs][0]}$i",
                    "string",
                    packName
                )
            )
            textViewForLoadInstructions.visibility = View.VISIBLE
            val imageViewForLoadInstructions = instracton!!.findViewById<ImageView>(
                context.resources.getIdentifier(
                    "imageViewIm$i",
                    "id",
                    packName
                )
            )
            imageViewForLoadInstructions.setImageResource(
                context.resources.getIdentifier(
                    "${arrayOfIDs[numberInArrayOfIDs][1]}$i",
                    "drawable",
                    packName
                )
            )
            imageViewForLoadInstructions.visibility = View.VISIBLE
        }
    }

    private fun hideExcessive() {
        val textViewForLoadInstructions = instracton!!.findViewById<TextView>(R.id.textViewDescr4)
        textViewForLoadInstructions.visibility = View.GONE
        val imageViewForLoadInstructions = instracton!!.findViewById<ImageView>(R.id.imageViewIm4)
        imageViewForLoadInstructions.visibility = View.GONE
    }

    fun closeDialog() {
        instracton!!.dismiss()
    }
    fun showDialog() {
        loadInstructionDialog()
        instracton!!.show()
    }
}