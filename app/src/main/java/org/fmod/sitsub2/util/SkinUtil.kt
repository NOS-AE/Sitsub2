package org.fmod.sitsub2.util

import android.app.Activity
import org.fmod.sitsub2.R
import org.fmod.sitsub2.data.DataManager

object ThemeUtil {

    private val styles = intArrayOf(
        R.style.ThemeDarkTurquoiseGrey
    )


    fun setTheme(target: Activity) {
        val themeIndex = DataManager.theme
        target.setTheme(styles[themeIndex])
    }
}