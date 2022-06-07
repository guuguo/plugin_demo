package top.guuguo.plugin_base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import top.guuguo.plugin_base.utils.ext.logI
import java.lang.reflect.Method


class PluginInstrumentation(var instrumentation:Instrumentation): Instrumentation() {

    @SuppressLint("DiscouragedPrivateApi")
    fun execStartActivity(
        who: Context?, contextThread: IBinder?, token: IBinder?, target: Activity?,
        intent: Intent?, requestCode: Int, options: Bundle?
    ): ActivityResult? {
        val sb = StringBuilder()
        sb.append("who = [").append(who).append("], ")
            .append("contextThread = [").append(contextThread).append("], ")
            .append("token = [").append(token).append("], ")
            .append("target = [").append(target).append("], ")
            .append("intent = [").append(intent).append("], ")
            .append("requestCode = [").append(requestCode).append("], ")
            .append("options = [").append(options).append("]")
        ( "执行了startActivity, 参数如下: $sb").logI()
        try {
            val pluginClazz = pluginClassLoader.loadClass(intent?.component?.className)
            var newIntent=intent;
            if (pluginClazz.superclass == IPluginActivityInterface::class.java) {
                newIntent=Intent(who,HostActivity::class.java)
                intent?.extras?.let {
                    newIntent.putExtras(it)
                }
                newIntent.putExtra(HostActivity.ARG_PLUGIN_CLASS_NAME,pluginClazz.name)
            }
            val execStartActivity: Method = Instrumentation::class.java.getDeclaredMethod(
                "execStartActivity",
                Context::class.java,
                IBinder::class.java,
                IBinder::class.java,
                Activity::class.java,
                Intent::class.java,
                Int::class.javaPrimitiveType,
                Bundle::class.java
            )

            return execStartActivity.invoke(instrumentation, who, contextThread, token, target, newIntent, requestCode, options) as ActivityResult
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
