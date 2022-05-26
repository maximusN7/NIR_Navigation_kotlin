package com.MaxEle.maximarius.nir_navigation.util.instruction_activity_utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ViewProcessor(private val activity: Activity, val context: Context, private val packName: String) {

    fun makeINVISIBLEALL() {

        for (textViewId in listOfTextViewIds) {
            val textViewForCleaning =
                this.activity.findViewById<TextView>(context.resources.getIdentifier(textViewId, "id", packName))
            textViewForCleaning.text = ""
            textViewForCleaning.visibility = View.GONE
        }


        for (imageViewId in listOfImageViewIds) {
            val imageViewForCleaning =
                this.activity.findViewById<ImageView>(context.resources.getIdentifier(imageViewId, "id", packName))
            imageViewForCleaning.setImageDrawable(null)
            imageViewForCleaning.visibility = View.GONE
        }
    }

    fun showInstructions(numberOfUsableViews: Int, numberOfTask: Int) {
        makeINVISIBLEALL()
        for (i in 1..numberOfUsableViews) {
            val textViewForShow =
                this.activity.findViewById<TextView>(context.resources.getIdentifier(listOfTextViewIds[i - 1], "id", packName))
            textViewForShow.setText(context.resources.getIdentifier("descrtask${numberOfTask}_$i", "string", packName))
            textViewForShow.visibility = View.VISIBLE
        }
        if (numberOfTask != 0) {
            for (i in 1..numberOfUsableViews) {
                val imageViewForShow =
                    this.activity.findViewById<ImageView>(context.resources.getIdentifier(listOfImageViewIds[i - 1], "id", packName))
                imageViewForShow.setImageResource(context.resources.getIdentifier("imgdiscr${numberOfTask}$i", "drawable", packName))
                imageViewForShow.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        val listOfTextViewIds = listOf("textViewDescr1", "textViewDescr2", "textViewDescr3", "textViewDescr4",
            "textViewDescr5", "textViewDescr6", "textViewDescr7", "textViewDescr8", "textViewDescr9", "textViewDescr10",
            "textViewDescr11", "textViewDescr12", "textViewDescr13", "textViewDescr14")
        val listOfImageViewIds = listOf("imageViewIm1", "imageViewIm2", "imageViewIm3", "imageViewIm4",
            "imageViewIm5", "imageViewIm6", "imageViewIm7", "imageViewIm8", "imageViewIm9", "imageViewIm10",
            "imageViewIm11", "imageViewIm12", "imageViewIm13", "imageViewIm14")
    }
}