object Dependencies {
    object General {
        val androidXCore = "androidx.core:core-ktx:1.7.0"
        val androidXLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
    }

    object Compose {
        val activity = "androidx.activity:activity-compose:1.3.1"
        val ui = "androidx.compose.ui:ui:${Versions.compose}"
        val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        val material = "androidx.compose.material:material:${Versions.compose}"
        val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
    }

    object Test {
        val junit4 = "junit:junit:4.13.2"
        val androidJunit = "androidx.test.ext:junit:1.1.3"
    }
}
object Network {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
}