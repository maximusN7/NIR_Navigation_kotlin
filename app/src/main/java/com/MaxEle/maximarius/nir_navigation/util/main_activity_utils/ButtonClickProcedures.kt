package com.MaxEle.maximarius.nir_navigation.util.main_activity_utils

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.MaxEle.maximarius.nir_navigation.R
import com.MaxEle.maximarius.nir_navigation.util.SharedPreferencesProcessor

class ButtonClickProcedures(
    val context: Context,
    val activity: Activity,
    private val packName: String,
    private val appComAct: AppCompatActivity
) {

    private var flagMenuTaskOpen = false
    private var flagMenuEntryHoldingOpen = false
    private val buttonTasksMenu: Button = this.activity.findViewById(R.id.buttonTasks)
    private val buttonEntryMenu: Button = this.activity.findViewById(R.id.buttonEntryTest)
    private val animSubMenuOpen: Animation = AnimationUtils.loadAnimation(context, R.anim.undmenu_anim)
    private val animSubMenuClose: Animation = AnimationUtils.loadAnimation(context, R.anim.undmenu_anim_out)
    private val subMenuEH: LinearLayout = this.activity.findViewById(R.id.undermenu_EH)
    private val subMenuTask: LinearLayout = this.activity.findViewById(R.id.undermenu_task)


    fun updateTheme(emptyForLightOneForDarkTheme: String) {
        val mapButtonsToTextKeys = mapOf(
            "buttonShop" to "shop",
            "buttonCalc" to "calcul",
            "buttonConstruct" to "constructor",
            "buttonTest" to "test",
            "buttonTraining" to "training",
            "buttonStat" to "statistics",
            "buttonInstructions" to "instructions",
            "buttonLZPTest" to "testLZP",
            "buttonEntryTest" to "EntryTest",
            "buttonNL" to "NL",
            "buttonExit" to "exit",
            "buttonTasks" to "tasks",
            "buttonWind" to "wind",
            "button_EH" to "EH",
            "buttonTimeCalc" to "TimeCalcMen",
            "button_EH_task" to "EH_task"
        )

        val window = appComAct.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = context.getColor(
            context.resources.getIdentifier(
                "colorPrimary$emptyForLightOneForDarkTheme",
                "color",
                packName
            )
        )
        val actionBar = appComAct.supportActionBar
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                context.getColor(
                    context.resources.getIdentifier(
                        "colorPrimary$emptyForLightOneForDarkTheme",
                        "color",
                        packName
                    )
                )
            )
        )
        val background = this.activity.findViewById<ConstraintLayout>(R.id.BackAll)
        background.setBackgroundColor(
            context.getColor(
                context.resources.getIdentifier(
                    "background$emptyForLightOneForDarkTheme",
                    "color",
                    packName
                )
            )
        )
        var backgroundSubMenu = this.activity.findViewById<LinearLayout>(R.id.undermenu_EH)
        backgroundSubMenu.setBackgroundColor(
            context.getColor(
                context.resources.getIdentifier(
                    "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                    "color",
                    packName
                )
            )
        )
        backgroundSubMenu = this.activity.findViewById(R.id.undermenu_task)
        backgroundSubMenu.setBackgroundColor(
            context.getColor(
                context.resources.getIdentifier(
                    "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                    "color",
                    packName
                )
            )
        )
        val switchTheme = this.activity.findViewById<SwitchCompat>(R.id.switchTheme)
        switchTheme.setTextColor(
            context.getColor(
                context.resources.getIdentifier(
                    "textsimple$emptyForLightOneForDarkTheme",
                    "color",
                    packName
                )
            )
        )

        val switchLang = this.activity.findViewById<SwitchCompat>(R.id.switchLang)
        switchLang.setTextColor(
            context.getColor(
                context.resources.getIdentifier(
                    "textsimple$emptyForLightOneForDarkTheme",
                    "color",
                    packName
                )
            )
        )


        for ((buttonId) in mapButtonsToTextKeys) {
            val buttonForThemeUpdate =
                this.activity.findViewById<Button>(
                    context.resources.getIdentifier(
                        buttonId,
                        "id",
                        packName
                    )
                )
            buttonForThemeUpdate.background.setColorFilter(
                context.getColor(
                    context.resources.getIdentifier(
                        "backgroundbutton$emptyForLightOneForDarkTheme",
                        "color",
                        packName
                    )
                ),
                PorterDuff.Mode.MULTIPLY
            )
            buttonForThemeUpdate.setTextColor(
                context.getColor(
                    context.resources.getIdentifier(
                        "textsimple$emptyForLightOneForDarkTheme",
                        "color",
                        packName
                    )
                )
            )

            if (buttonId == "buttonTasks" && flagMenuTaskOpen || buttonId == "buttonEntryTest" && flagMenuEntryHoldingOpen) {
                buttonForThemeUpdate.background.setColorFilter(
                    context.getColor(
                        context.resources.getIdentifier(
                            "backgroundbuttonpressed$emptyForLightOneForDarkTheme",
                            "color",
                            packName
                        )
                    ),
                    PorterDuff.Mode.MULTIPLY
                )
            }
        }


    }

    fun buttonTaskMenuClick() {
        if (flagMenuTaskOpen) {
            animForEHMenu(buttonTasksMenu, subMenuTask, animSubMenuClose, 0f, 300, "backgroundbutton")
            flagMenuTaskOpen = false
        } else {
            animForEHMenu(buttonTasksMenu, subMenuTask, animSubMenuOpen, 21f, 700, "backgroundbuttonpressed")
            flagMenuTaskOpen = true

            if (flagMenuEntryHoldingOpen) {
                animForEHMenu(buttonEntryMenu, subMenuEH, animSubMenuClose, 0f, 300, "backgroundbutton")
                flagMenuEntryHoldingOpen = false
            }

        }
    }

    fun buttonEHMenuClick() {
        if (flagMenuEntryHoldingOpen) {
            animForEHMenu(buttonEntryMenu, subMenuEH, animSubMenuClose, 0f, 300, "backgroundbutton")
            flagMenuEntryHoldingOpen = false
        } else {
            animForEHMenu(buttonEntryMenu, subMenuEH, animSubMenuOpen, 21f, 700, "backgroundbuttonpressed")
            flagMenuEntryHoldingOpen = true

            if (flagMenuTaskOpen) {
                animForEHMenu(buttonTasksMenu, subMenuTask, animSubMenuClose, 0f, 300, "backgroundbutton")
                flagMenuTaskOpen = false
            }
        }
    }

    private fun animForEHMenu(button: Button, menuLayout: LinearLayout, animationToStart: Animation, pointer: Float, duration: Long, colorToButton: String) {
        val mDataFiles = SharedPreferencesProcessor(context)
        val isThemeLight = mDataFiles.getBoolean(SharedPreferencesProcessor.DATA_FILE_THEME_LIGHT, true)
        val animation = ObjectAnimator.ofFloat(button, "translationX", pointer)
        animation.duration = duration
        animation.start()
        if (isThemeLight) {
            button.background.setColorFilter(
                context.getColor(
                    context.resources.getIdentifier(
                        colorToButton,
                        "color",
                        packName
                    )),
                PorterDuff.Mode.MULTIPLY
            )
            menuLayout.setBackgroundColor(context.getColor(
                context.resources.getIdentifier(
                    "backgroundbuttonpressed",
                    "color",
                    packName
                )))
        } else {
            button.background.setColorFilter(
                context.getColor(context.resources.getIdentifier(
                    "${colorToButton}1",
                    "color",
                    packName
                )),
                PorterDuff.Mode.MULTIPLY
            )
            menuLayout.setBackgroundColor(context.getColor(
                context.resources.getIdentifier(
                    "backgroundbuttonpressed1",
                    "color",
                    packName
                )))
        }
        menuLayout.startAnimation(animationToStart)
        menuLayout.visibility = if (duration == 300L) View.INVISIBLE else View.VISIBLE
    }
}