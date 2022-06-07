package top.guuguo.plugin_base.utils.ext
import java.text.SimpleDateFormat
import java.util.*


fun Long.format(format: String) = Date(this).format(format)
fun Date.format(format: String) = SimpleDateFormat(format).format(this)
