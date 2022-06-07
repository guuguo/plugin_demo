package top.guuguo.plugin_base.utils.ext

import android.net.Uri

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is DownloadsProvider.
 */
val Uri.isDownloadsDocument get() = "com.android.providers.downloads.documents" == this.authority

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is ExternalStorageProvider.
 */
val Uri.isExternalStorageDocument get() = "com.android.externalstorage.documents" == authority

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is MediaProvider.
 */
val Uri.isMediaDocument get() = "com.android.providers.media.documents" == authority

/**
 * @param uri The Uri to check.
 * @return Whether the Uri authority is Google Photos.
 */
val Uri.isGooglePhotosUri get() = "com.google.android.apps.photos.content" == authority
