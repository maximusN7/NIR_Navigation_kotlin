package com.MaxEle.maximarius.nir_navigation.util.testEH_activity_utils

import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.UtilsForCalculations.Companion.makeAngle0To360

class EHVisualizationProcessor(val context: Context, val activity: Activity) {
    private var activeR = true

    init {
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        val buttonR: Button = this.activity.findViewById(R.id.buttonR)
        buttonR.isEnabled = false
        activeR = true
        if (isThemeLight) {
            buttonR.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbuttonpressed),
                PorterDuff.Mode.MULTIPLY
            )
        } else {
            buttonR.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbuttonpressed1),
                PorterDuff.Mode.MULTIPLY
            )
        }
    }
    fun buttonLorRClick() {
        val buttonL: Button = this.activity.findViewById(R.id.buttonL)
        val buttonR: Button = this.activity.findViewById(R.id.buttonR)

        activeR = if (activeR) {
            disableOneAndActivateAnother(buttonR, buttonL)
            false
        } else {
            disableOneAndActivateAnother(buttonL, buttonR)
            true
        }

        val image: ImageView = this.activity.findViewById(R.id.imageViewrot)
        setNewImage(makeAngle0To360(image.rotation))
    }

    private fun disableOneAndActivateAnother(button1: Button, button2: Button) {
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)

        button1.isEnabled = true
        button2.isEnabled = false
        if (isThemeLight) {
            button2.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbuttonpressed),
                PorterDuff.Mode.MULTIPLY
            )
            button1.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbutton),
                PorterDuff.Mode.MULTIPLY
            )
        } else {
            button2.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbuttonpressed1),
                PorterDuff.Mode.MULTIPLY
            )
            button1.background.setColorFilter(
                context.resources.getColor(R.color.backgroundbutton1),
                PorterDuff.Mode.MULTIPLY
            )
        }
    }

    fun editTextListener(editText: EditText, rotateBothImages: Boolean) {
        var numberFromEditText = makeAngle0To360(editText.text.toString().toInt())
        editText.setText(numberFromEditText.toString())
        if (rotateBothImages) numberFromEditText = -1 * (numberFromEditText - 360)

        val image: ImageView = this.activity.findViewById(R.id.imageViewrot)
        val grad = image.rotation
        val courseImage: ImageView = this.activity.findViewById(R.id.imageViewkur)
        val grad2 = courseImage.rotation
        if (rotateBothImages) {
            image.rotation = grad + numberFromEditText - grad2
            courseImage.rotation = numberFromEditText.toFloat()
        }else {
            image.rotation = numberFromEditText + grad2
        }

        setNewImage(grad + numberFromEditText - grad2)
    }

    fun imageTouch(currentRotation: Float, newRotation: Float, rotateBothImages: Boolean, imageRotation: Float) {
        val image: ImageView = this.activity.findViewById(R.id.imageViewrot)
        val courseImage: ImageView = this.activity.findViewById(R.id.imageViewkur)
        val landingEditText: EditText = this.activity.findViewById(R.id.textViewposad)
        val courseEditText: EditText = this.activity.findViewById(R.id.textViewkurs)

        val angleImage: Float
        val angleCourse: Float
        val angleImageThroughCourse: Float

        if (rotateBothImages) {
            angleImage = makeAngle0To360(imageRotation + newRotation)
            angleCourse = makeAngle0To360(currentRotation + newRotation)
            courseImage.rotation = angleCourse
            image.rotation = imageRotation + newRotation
            val l = -1 * (angleCourse.toInt() - 360)
            angleImageThroughCourse = makeAngle0To360(angleImage + l)

            courseEditText.setText(l.toString())
        } else {
            image.rotation = currentRotation + newRotation
            angleImage = makeAngle0To360(image.rotation)
            angleImageThroughCourse = makeAngle0To360(angleImage - courseImage.rotation)
        }

        landingEditText.setText(angleImageThroughCourse.toInt().toString())
        setNewImage(angleImage)
    }

    private fun setNewImage(angle: Float) {
        val image: ImageView = this.activity.findViewById(R.id.imageViewrot)
        when {
            ((angle in 0.0..70.0 || angle in 250.0..360.0) && activeR) -> {
                image.setImageResource(R.drawable.round_r_str)
            }
            ((angle in 0.0..110.0 || angle in 290.0..360.0) && !activeR) -> {
                image.setImageResource(R.drawable.round_l_str)
            }
            ((angle > 70 && angle <= 180) && activeR) -> {
                image.setImageResource(R.drawable.round_r_par)
            }
            ((angle >= 180 && angle < 290) && !activeR) -> {
                image.setImageResource(R.drawable.round_l_par)
            }
            ((angle > 180 && angle < 250) && activeR) -> {
                image.setImageResource(R.drawable.round_r_tea)
            }
            ((angle > 110 && angle < 180) && !activeR) -> {
                image.setImageResource(R.drawable.round_l_tea)
            }
        }
    }
}