package com.MaxEle.maximarius.nir_navigation.util

class UtilsForCalculations {

    companion object {
        fun makeAngle0To360(x: Short): Short {
            var mX = x
            when {
                mX >= 360 -> while (mX >= 360) mX = (mX - 360).toShort()
                mX < 0 -> while (mX < 0) mX = (mX + 360).toShort()
            }
            return mX
        }
    }

}