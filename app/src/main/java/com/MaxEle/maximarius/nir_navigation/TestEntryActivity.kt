package com.MaxEle.maximarius.nir_navigation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdView
import android.util.DisplayMetrics
import android.widget.TextView.OnEditorActionListener
import android.view.inputmethod.EditorInfo
import android.view.View.OnTouchListener
import android.view.MotionEvent
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.MaxEle.maximarius.nir_navigation.util.GoToAnotherActivity
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor
import com.MaxEle.maximarius.nir_navigation.util.UtilsForCalculations.Companion.isNumeric
import com.MaxEle.maximarius.nir_navigation.util.testEH_activity_utils.EHVisualizationProcessor
import com.MaxEle.maximarius.nir_navigation.util.textLZP_activity_utils.DialogProcessor
import com.google.android.gms.ads.AdRequest
import java.util.*
import kotlin.math.atan2

@SuppressLint("ClickableViewAccessibility")
class TestEntryActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    private lateinit var dialogProcessor: DialogProcessor

    var isThemeLight = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mDataFiles = SharedPreferencesProcessor(this)

        isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        setTheme(if (isThemeLight) R.style.AppTheme else R.style.AppThemeDark)

        setContentView(R.layout.activity_test_entry)

        val viewAnswer = findViewById<ConstraintLayout>(R.id.viewCond)
        viewAnswer.setBackgroundColor(getColor(if (isThemeLight) R.color.backgroundview else R.color.backgroundview1))

        mAdView = findViewById(R.id.banner_ad)
        if (mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_ADS_DISABLE, false)) {
            mAdView.visibility = View.GONE
        } else {
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
        }

        dialogProcessor = DialogProcessor(this, packageName, 4, 1)

        val image: ImageView = findViewById(R.id.imageViewrot)
        val kursim: ImageView = findViewById(R.id.imageViewkur)
        val plane = findViewById<ImageView>(R.id.imagePlane)
        if (isThemeLight) {
            plane.setImageResource(R.drawable.plane_for_rot)
            kursim.setImageResource(R.drawable.kurs)
        } else {
            plane.setImageResource(R.drawable.plane_for_rot_dark)
            kursim.setImageResource(R.drawable.kurs_for_rot_dark)
        }

        val display = windowManager.defaultDisplay
        val metricsB = DisplayMetrics()
        display.getMetrics(metricsB)
        val dXCenter = metricsB.widthPixels.toFloat() / 2
        val dYCenter = (4 * metricsB.heightPixels / 13).toFloat()

        val visualizationProcessor = EHVisualizationProcessor(this, this)


        val courseEditText: EditText = findViewById(R.id.textViewkurs)
        courseEditText.setOnEditorActionListener(OnEditorActionListener { _: TextView?, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isNumeric(courseEditText.text.toString())) {
                    visualizationProcessor.editTextListener(courseEditText, true)

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        Objects.requireNonNull(
                            currentFocus
                        )?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
                return@OnEditorActionListener true
            }
            false
        })
        val landingEditText: EditText = findViewById(R.id.textViewposad)
        landingEditText.setOnEditorActionListener(OnEditorActionListener { _: TextView?, actionId: Int, _: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (isNumeric(landingEditText.text.toString())) {
                    visualizationProcessor.editTextListener(landingEditText, false)

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(
                        Objects.requireNonNull(
                            currentFocus
                        )?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
                return@OnEditorActionListener true
            }
            false
        })

        var dX = 0f
        var dY = 0f
        var grad = 0f
        var grad2 = 10f
        var newGrad: Float


        image.setOnTouchListener(OnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    grad = image.rotation
                    dX = event.rawX
                    dY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    newGrad = -(Math.toDegrees(
                        atan2(
                            (dXCenter - event.rawX).toDouble(),
                            (dYCenter - event.rawY).toDouble()
                        )
                    ) - Math.toDegrees(
                        atan2((dXCenter - dX).toDouble(), (dYCenter - dY).toDouble())
                    )).toFloat()

                    visualizationProcessor.imageTouch(grad, newGrad, false, grad)
                }
                MotionEvent.ACTION_UP -> {}
                else -> return@OnTouchListener false
            }
            true
        })
        kursim.setOnTouchListener(OnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    grad2 = kursim.rotation
                    grad = image.rotation
                    dX = event.rawX
                    dY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    newGrad = -(Math.toDegrees(
                        atan2(
                            (dXCenter - event.rawX).toDouble(),
                            (dYCenter - event.rawY).toDouble()
                        )
                    ) - Math.toDegrees(
                        atan2((dXCenter - dX).toDouble(), (dYCenter - dY).toDouble())
                    )).toFloat()

                    visualizationProcessor.imageTouch(grad2, newGrad, true, grad)
                }
                MotionEvent.ACTION_UP -> {}
                else -> return@OnTouchListener false
            }
            true
        })

        val buttonL: Button = findViewById(R.id.buttonL)
        buttonL.setOnClickListener {
            visualizationProcessor.buttonLorRClick()
        }

        val buttonR: Button = findViewById(R.id.buttonR)
        buttonR.setOnClickListener {
            visualizationProcessor.buttonLorRClick()
        }
    }

    override fun onBackPressed() {
        GoToAnotherActivity(this, this, MainActivity::class.java).start()
    }

    fun onClickBack(view: View?) {
        GoToAnotherActivity(this, this, MainActivity::class.java).start()
    }

    fun onClickInstr(view: View?) {
        dialogProcessor.showDialog()
    }

    fun onClickBackDial(view: View?) {
        dialogProcessor.closeDialog()
    }
}