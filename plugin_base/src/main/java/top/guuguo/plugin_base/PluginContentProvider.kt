package top.guuguo.plugin_base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Instrumentation
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import com.blankj.utilcode.util.Utils
import java.lang.reflect.Field

class PluginContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        Utils.getApp().registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            @SuppressLint("DiscouragedPrivateApi")
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // 拿到原始的 mInstrumentation字段
                val mInstrumentationField: Field = Activity::class.java.getDeclaredField("mInstrumentation")
                mInstrumentationField.isAccessible = true
                // 创建代理对象
                val originalInstrumentation: Instrumentation = mInstrumentationField.get(activity) as Instrumentation
                mInstrumentationField.set(activity, PluginInstrumentation(originalInstrumentation))
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
            }

            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

        })
        return true
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }
}