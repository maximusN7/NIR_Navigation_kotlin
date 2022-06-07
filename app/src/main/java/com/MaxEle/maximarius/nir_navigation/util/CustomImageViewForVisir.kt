package com.MaxEle.maximarius.nir_navigation.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import android.view.MotionEvent

class CustomImageViewForVisir : AppCompatImageView {
    private var dX = 0f
    var dY = 0f

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dX = this.x - event.rawX
                dY = this.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> animate()
                .y(event.rawY + dY)
                .setDuration(0)
                .start()
            MotionEvent.ACTION_UP -> performClick()
            else -> return false
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}