package com.MaxEle.maximarius.nir_navigation.util.nl_activity_utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class DialogProcessorNL(val context: Context, val activity: Activity, private val packName: String, private val processorNL: ProcessorNL) {

    private lateinit var instracton: Dialog

    private fun loadInstructionDialog() {

        instracton = Dialog(context)
        instracton.setTitle(context.resources.getString(R.string.instructions))
        instracton.setContentView(R.layout.dialog_view_nl)

        val viewMain = instracton.findViewById<LinearLayout>(R.id.viewMain)
        val scrollDialog = instracton.findViewById<ScrollView>(R.id.scrolldia)
        val switchNLins = instracton.findViewById<SwitchCompat>(R.id.switchInstNL)

        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        if (isThemeLight) {
            viewMain.setBackgroundColor(context.getColor(context.resources.getIdentifier("background", "color", packName)))
            scrollDialog.setBackgroundColor(context.getColor(context.resources.getIdentifier("backgroundview", "color", packName)))
            switchNLins.setTextColor(context.getColor(context.resources.getIdentifier("textsimple", "color", packName)))
        } else {
            viewMain.setBackgroundColor(context.getColor(context.resources.getIdentifier("background1", "color", packName)))
            scrollDialog.setBackgroundColor(context.getColor(context.resources.getIdentifier("backgroundview1", "color", packName)))
            switchNLins.setTextColor(context.getColor(context.resources.getIdentifier("textsimple1", "color", packName)))
        }

        val isRussian = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_LANGUAGE_RUS, true)


        //поставить переключатель маркировки НЛ в нужное положение
        switchNLins.isChecked = processorNL.isNLMarked
        when {
            switchNLins.isChecked && isRussian -> switchNLins.setText(R.string.switchInstNLyes)
            switchNLins.isChecked && !isRussian -> switchNLins.setText(R.string.switchInstNLyes)
            !switchNLins.isChecked && isRussian -> switchNLins.setText(R.string.switchInstNLnoeng)
            !switchNLins.isChecked && !isRussian -> switchNLins.setText(R.string.switchInstNLnoeng)
        }

        switchNLins?.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            processorNL.isNLMarked = if (isChecked) {
                if (isRussian) switchNLins.setText(R.string.switchInstNLyes) else switchNLins.setText(
                    R.string.switchInstNLyes
                )
                true
            } else {
                if (isRussian) switchNLins.setText(R.string.switchInstNLno) else switchNLins.setText(
                    R.string.switchInstNLno
                )
                false
            }
            mDataFiles.setBoolean(SharedPreferencesProcessor.DATA_FILE_NLINSTRACTIONS, isChecked)
            processorNL.changeNL()
        }


        for (i in 1..10) {
            val textViewForLoadInstructions =
                instracton.findViewById<TextView>(
                    context.resources.getIdentifier(
                        "textViewDescr$i",
                        "id",
                        packName
                    )
                )
            textViewForLoadInstructions.setText(
                context.resources.getIdentifier(
                    "NL_dialog$i",
                    "string",
                    packName
                )
            )
            textViewForLoadInstructions.visibility = View.VISIBLE


            val imageViewForLoadInstructions = instracton.findViewById<ImageView>(
                context.resources.getIdentifier(
                    "imageViewIm$i",
                    "id",
                    packName
                )
            )

            if (i == 1 || i == 9 || i == 10) {
                if (isRussian) {
                    imageViewForLoadInstructions.setImageResource(
                        context.resources.getIdentifier(
                            "nl_instr$i",
                            "drawable",
                            packName
                        )
                    )
                } else {
                    imageViewForLoadInstructions.setImageResource(
                        context.resources.getIdentifier(
                            "nl_instr${i}eng",
                            "drawable",
                            packName
                        )
                    )
                }

            } else {
                imageViewForLoadInstructions.setImageResource(
                    context.resources.getIdentifier(
                        "nl_instr$i",
                        "drawable",
                        packName
                    )
                )
            }


            imageViewForLoadInstructions.visibility = View.VISIBLE
        }
    }

    fun closeDialog() {
        instracton.dismiss()
    }
    fun showDialog() {
        loadInstructionDialog()
        instracton.show()
    }
}