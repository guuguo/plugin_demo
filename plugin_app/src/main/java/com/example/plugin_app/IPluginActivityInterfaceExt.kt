package com.example.plugin_app

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import top.guuguo.plugin_base.IPluginActivityInterface

fun IPluginActivityInterface.setContent(
    parent: CompositionContext? = null,
    content: @Composable () -> Unit
) {
    mHostActivity.setContent(parent,content)
}