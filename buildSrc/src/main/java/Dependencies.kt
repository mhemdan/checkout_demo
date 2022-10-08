object Libs {
    const val androidXCore = "androidx.core:core-ktx:1.7.0"
    const val androidXLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val dagger = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val daggerComposeNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Compose {
    const val activity = "androidx.activity:activity-compose:1.3.1"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val webview = "com.google.accompanist:accompanist-webview:0.25.0"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"
}

object TestLibs {
    const val junit4 = "junit:junit:4.13.2"
    const val androidJunit = "androidx.test.ext:junit:1.1.3"
    const val mockk = "io.mockk:mockk:${Versions.mockkVersion}"
    const val assertJ = "org.assertj:assertj-core:3.18.1"
    const val fixture = "com.appmattus.fixture:fixture:1.2.0"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val turbine = "app.cash.turbine:turbine:0.11.0"
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0"
}
