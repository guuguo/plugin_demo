# plugin_demo

> Archived historical Android pluginization demo (Kotlin + Jetpack Compose).

一个基于 `DexClassLoader` 的 Android 插件化加载示例工程，演示宿主 App 从 `assets` 加载 `plugin.apk` 并拉起插件模块中的 Activity。

## Modules

- `:app` — 宿主 App（Jetpack Compose + Material 3），在 `MainActivity` 启动时调用 `PluginLoader.loadPlugin()` 加载插件，并通过 `ComponentName` 跳转到 `com.example.plugin_app.PluginsActivity`。
- `:plugin_app` — 插件业务模块，包含 `PluginsActivity` 与 `IPluginActivityInterfaceExt`。
- `:plugin_base` — 插件化基础库，包含 `PluginLoader`、`HostActivity`、`PluginInstrumentation`、`PluginContentProvider`、`IPluginActivityInterface` 等组件。

## Tech Stack

- Kotlin `1.6.10`
- Android Gradle Plugin `7.4.0-alpha02`
- Jetpack Compose `1.2.0-alpha06` / Material 3
- `compileSdk 32` / `minSdk 24`
