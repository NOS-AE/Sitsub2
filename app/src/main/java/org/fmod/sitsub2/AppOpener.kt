package org.fmod.sitsub2

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import org.fmod.sitsub2.data.remote.CLIENT_ID
import org.fmod.sitsub2.data.remote.OAUTH2_SCOPE
import org.fmod.sitsub2.data.remote.OAUTH2_URL
import org.fmod.sitsub2.util.log
import org.fmod.sitsub2.util.toastWarning
import java.util.*
import kotlin.collections.ArrayList

object AppOpener {

    //redirect用户到github页面输入密码和用户名的url(oauth)
    fun getOAuth2Url(): String {
        val randomState = UUID.randomUUID().toString()
        return OAUTH2_URL +
                "?client_id=${CLIENT_ID}" +
                "&scope=$OAUTH2_SCOPE" +
                "&state=$randomState"
    }

    fun openInBrowser(context: Context, url: String) {
        val uri = Uri.parse(url)
        val intent: Intent = Intent(Intent.ACTION_VIEW, uri).addCategory(Intent.CATEGORY_BROWSABLE)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val finalIntent = createActivityChooserIntent(context, intent, uri)
        if(finalIntent != null){
            context.startActivity(finalIntent)
        } else {
            toastWarning("未安装浏览器客户端")
        }
    }

    private fun createActivityChooserIntent(context: Context, intent: Intent, uri: Uri): Intent? {
        val pm = context.packageManager
        val activities = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        val chooserIntents = ArrayList<Intent>()
        val thisPackageName = context.packageName

        activities.sortWith(ResolveInfo.DisplayNameComparator(pm))

        for (resInfo in activities) {
            val info = resInfo.activityInfo
            if(!info.enabled || !info.exported)
                continue
            if(info.packageName == thisPackageName)
                continue
            if(VIEW_IGNORE_PACKAGE.contains(info.packageName))
                continue

            val targetIntent = Intent(intent)
            targetIntent.`package` = info.packageName
            targetIntent.setDataAndType(uri, intent.type)
            chooserIntents.add(targetIntent)
        }

        if(chooserIntents.isEmpty())
            return null

        val lastIntent = chooserIntents.removeAt(chooserIntents.lastIndex)
        if(chooserIntents.isEmpty())
            return lastIntent

        val chooserIntent = Intent.createChooser(lastIntent, null)
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
            chooserIntents.toArray(arrayOfNulls<Intent>(chooserIntents.size)))
        return chooserIntent
    }

    private val VIEW_IGNORE_PACKAGE = arrayListOf(
        "com.gh4a", "com.fastaccess", "com.taobao.taobao", "com.thirtydegreesray.openhub"
    )
}