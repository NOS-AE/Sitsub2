package org.fmod.sitsub2.util

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import org.fmod.sitsub2.MyApp
import java.lang.StringBuilder
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.statusBarColor = barColor
}

fun Activity.transparentFullScreen() {
    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.statusBarColor = Color.TRANSPARENT
}

/**
 * Dimension
 */
val density = Resources.getSystem().displayMetrics.density
val scaledDensity = Resources.getSystem().displayMetrics.scaledDensity
val statusBarHeight by lazy {
    val id = MyApp.instance.resources.getIdentifier("status_bar_height", "dimen", "android")
    if(id > 0)
        MyApp.instance.resources.getDimensionPixelSize(id)
    else
        0
}


fun dp2px(value: Float): Int {
    return (density * value + 0.5).toInt()
}

fun sp2px(value: Float): Int {
    return (scaledDensity * value + 0.5).toInt()
}

/**
 * Others
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}