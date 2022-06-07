package top.guuguo.plugin_base

import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.Utils
import dalvik.system.DexClassLoader
import top.guuguo.plugin_base.utils.ext.logI
import java.io.File

object PluginLoader {
    fun loadPlugin() {
        val inputStream = Utils.getApp().assets.open("plugin.apk")
        val filesDir = Utils.getApp().externalCacheDir
        val apkFile = File(filesDir?.absolutePath, "plugin.apk")
        apkFile.writeBytes(inputStream.readBytes())

        val dexFile = File(filesDir, "dex")
        FileUtils.createOrExistsDir(dexFile)
        "输出dex路径${dexFile}".logI()
        pluginClassLoader = DexClassLoader(apkFile.absolutePath, dexFile.absolutePath, null, this.javaClass.classLoader)
    }
}

lateinit var pluginClassLoader: DexClassLoader;