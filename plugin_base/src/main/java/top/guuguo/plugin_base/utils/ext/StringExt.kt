package top.guuguo.plugin_base.utils.ext

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import java.io.File


fun CharSequence.tint(@ColorInt color: Int, start: Int = 0, end: Int = this.length): CharSequence {
    val sString = SpannableString(this)
    sString.setSpan(ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return sString
}

fun String?.toast() {
    if (this == null) return
    ToastUtils.showLong(this)
}

fun (() -> Unit).applyOffset() {
    LogUtils.getConfig().stackOffset = 1;
    this()
    LogUtils.getConfig().stackOffset = 0;
}

fun String.logI(tag: LogTags = LogTags.default) = {
    LogUtils.iTag(tag.toString(), this)
}.applyOffset()

fun String.logW(tag: LogTags = LogTags.default) = {
    LogUtils.wTag(tag.toString(), this)
}.applyOffset()

fun Any.logE(tag: LogTags = LogTags.default) = {
    LogUtils.eTag(tag.toString(), this)
}.applyOffset()

fun String.logStart(tag: LogTags = LogTags.default) = {
    LogUtils.iTag(tag.toString(), "┌┅┅┅┅┅┅    开始:$this   ┅┅┅┅┅┅┐")

}.applyOffset()

fun String.logEnd(tag: LogTags = LogTags.default) = {
    LogUtils.iTag(tag.toString(), "└┅┅┅┅┅┅    结束:$this   ┅┅┅┅┅┅┘")
}.applyOffset()

sealed class LogTags(val title: String) {
    object default : LogTags("");
    object other : LogTags("");
    object watchdog : LogTags("【守护保活】");
    class wxbackUp(val error: Boolean = false) : LogTags("【微信备份】") {
        override fun toString() = super.toString() + if (error) "【error】" else ""
    }

    object wxAnalyze : LogTags("【微信解析】") {
        var update = false
        operator fun invoke(update: Boolean = false): wxAnalyze {
            wxAnalyze.update = update
            return this
        }

        override fun toString() = super.toString() + if (update) "【update】" else ""
    }

    object voip : LogTags("【VOIP录音】");
    object location : LogTags("【定位】");
    object callLog : LogTags("【通话记录】");
    object mqtt : LogTags("【mqtt】");
    object wxAuto : LogTags("【微信自动点击】");
    override fun toString(): String {
        return title
    }
}

fun String.isPhoneNumber() = this.matches(Regex("^1[0-9]{3,10}\$"))
fun String.isNumberString() = this.matches(Regex("^[0-9]+\$"))

///如果路径文件夹不存在，就创建
fun String.applyCreateDirIfNotExist(): String {
    kotlin.runCatching {
        val file = File(this)
        if (!file.exists())
            FileUtils.createOrExistsDir(file)
    }
    return this
}