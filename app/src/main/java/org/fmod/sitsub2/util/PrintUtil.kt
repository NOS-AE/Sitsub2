package org.fmod.sitsub2.util

import android.util.Log
import android.widget.Toast
import org.fmod.sitsub2.BuildConfig
import org.fmod.sitsub2.MyApp

private val isDebug = BuildConfig.DEBUG
private const val APP_TAG = "SitSub2"
private const val REMOTE_TAG = "SitSub2-Remote"
private const val LOCAL_TAG = "SitSub2-Local"

fun toast(msg: String) = Toast.makeText(MyApp.instance, msg, Toast.LENGTH_SHORT).show()

fun Any.log(msg: String) {
    if(isDebug)
        Log.d("$APP_TAG-${javaClass.simpleName}", msg)
}

fun Any.remoteLog(msg: String) {
    if(isDebug)
        Log.d("$REMOTE_TAG-${javaClass.simpleName}", msg)
}

fun Any.localLog(msg: String) {
    if(isDebug)
        Log.d("$LOCAL_TAG-${javaClass.simpleName}", msg)
}