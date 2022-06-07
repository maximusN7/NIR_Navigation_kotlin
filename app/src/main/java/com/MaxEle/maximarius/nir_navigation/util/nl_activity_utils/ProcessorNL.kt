package com.MaxEle.maximarius.nir_navigation.util.nl_activity_utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.core.widget.NestedScrollView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class ProcessorNL(val context: Context, val activity: Activity, private val packName: String) {

    var isNLMarked = false
    var isFlipped = false

    private var nl1: ImageView
    private var nl2: ImageView
    private var nl3: ImageView

    init {
        val mDataFiles = SharedPreferencesProcessor(context)
        isNLMarked = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_NLINSTRACTIONS, false)

        nl1 = this.activity.findViewById(R.id.imageViewNL1)
        nl2 = this.activity.findViewById(R.id.imageViewNL2)
        nl3 = this.activity.findViewById(R.id.imageViewNL3)

        changeNL()

        val nlCenter = this.activity.findViewById<NestedScrollView>(R.id.scrollCenter)
        nlCenter.post {
            val cent = nl2.bottom / 3 + 10
            nlCenter.smoothScrollTo(0, cent)
        }
    }

    fun changeNL() {
        val j = if (isNLMarked) "ins" else ""
        val k = if (isFlipped) 2 else 1

        for (i in 1..3) {
            val imageViewForUpload = this.activity.findViewById<ImageView>(
                context.resources.getIdentifier(
                    "imageViewNL$i",
                    "id",
                    packName
                )
            )

            imageViewForUpload.setImageResource(
                context.resources.getIdentifier(
                    "nl${k}_$i$j",
                    "drawable",
                    packName
                )
            )
        }

    }

    fun defaultVisir() {
        val visisr = this.activity.findViewById<ImageView>(R.id.imageViewVisir)
        visisr.y = 0f
    }
}