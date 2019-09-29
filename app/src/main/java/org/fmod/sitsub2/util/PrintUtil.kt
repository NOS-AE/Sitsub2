package org.fmod.sitsub2.util

import android.util.Log
import android.widget.Toast
import es.dmoral.toasty.Toasty
import org.fmod.sitsub2.BuildConfig
import org.fmod.sitsub2.MyApp

private val isDebug = BuildConfig.DEBUG
private const val APP_TAG = "Sitsub2"
private const val REMOTE_TAG = "Sitsub2-Remote"
private const val LOCAL_TAG = "Sitsub2-Local"

fun toastError(msg: String) = Toasty.error(MyApp.instance, msg).show()

fun toastInfo(msg: String) = Toasty.info(MyApp.instance, msg).show()

fun toastSuccess(msg: String) = Toasty.success(MyApp.instance, msg).show()

fun toastWarning(msg: String) = Toasty.warning(MyApp.instance, msg).show()

fun Any.log(msg: String?) {
    if(isDebug)
        Log.d("$APP_TAG-${javaClass.simpleName}", msg.toString())
}

fun Any.remoteLog(msg: String?) {
    if(isDebug)
        Log.d("$REMOTE_TAG-${javaClass.simpleName}", msg.toString())
}

fun Any.localLog(msg: String?) {
    if(isDebug)
        Log.d("$LOCAL_TAG-${javaClass.simpleName}", msg.toString())
}

fun Any.errorLog(msg: String?) {
    if(isDebug)
        Log.e("$APP_TAG-${javaClass.simpleName}", msg.toString())
}

fun Any.remoteErrorLog(msg: String?) {
    if(isDebug)
        Log.e("$REMOTE_TAG-${javaClass.simpleName}", msg.toString())
}

fun Any.localErrorLog(msg: String?) {
    if(isDebug)
        Log.e("$LOCAL_TAG-${javaClass.simpleName}", msg.toString())
}