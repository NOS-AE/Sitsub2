package org.fmod.sitsub2.util

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import org.fmod.sitsub2.MyApp
import java.lang.StringBuilder

/**
 * Collection
 */
fun <T>List<T>.toString(separator: String): String {
    val builder = StringBuilder("")
    if(size > 0 && separator.isNotEmpty()) {
        builder.append(get(0).toString())
        for(i in 1 until size) {
            builder.append("${separator}${get(i)}")
        }
    }
    return builder.toString()
}

/**
 * Activity
 */
inline fun <reified T: Activity> Activity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun Activity.setStatusBar(barColor: Int = Color.TRANSPARENT) {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    window.statusBarColor = barColor
}

fun Activity.transparentFullScreen() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    window.statusBarColor = Color.TRANSPARENT
}

/**
 * Dimension
 */
val scale = Resources.getSystem().displayMetrics.density

val statusBarHeight by lazy {
    val id = MyApp.instance.resources.getIdentifier("status_bar_height", "dimen", "android")
    if(id > 0)
        MyApp.instance.resources.getDimensionPixelSize(id)
    else
        0
}

fun px2dp(value: Float): Int{
    return (scale * value + 0.5).toInt()
}

fun dp2px(value: Float): Int{
    return (scale * value + 0.5).toInt()
}