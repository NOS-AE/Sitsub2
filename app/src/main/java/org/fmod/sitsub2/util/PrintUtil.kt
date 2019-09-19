package org.fmod.sitsub2.util

import android.util.Log
import android.widget.Toast
import org.fmod.sitsub2.MyApp

fun toast(msg: String) = Toast.makeText(MyApp.instance, msg, Toast.LENGTH_SHORT).show()

fun log(msg: String) = Log.d("MyApp", msg)

fun remoteLog(msg: String) = Log.d("MyApp-Remote", msg)

fun localLog(msg: String) = Log.d("MyApp-Local", msg)