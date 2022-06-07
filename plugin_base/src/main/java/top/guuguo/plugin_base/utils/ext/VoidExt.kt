package top.guuguo.plugin_base.utils.ext

// suspend inline fun <T> MyVoid<T>.invokeCatch(catching: (Throwable) -> Unit) :T?{
//   return runCatching {
//        this()
//    }.onFailure {
//        catching(it)
//    }.getOrNull()
//}
//typealias MyVoid<T> =suspend  () -> T