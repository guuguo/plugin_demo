package top.guuguo.plugin_base

import android.os.Bundle


abstract class IPluginActivityInterface {
    lateinit var mHostActivity: HostActivity
    fun registerHostActivity(hostActivity: HostActivity) {
        mHostActivity = hostActivity;
    }

    fun getIntent() = mHostActivity.intent
    open fun onCreate(savedInstanceState: Bundle?){}
    fun setContentView(layoutResID: Int) {
        mHostActivity.setContentView(layoutResID)
    }
}