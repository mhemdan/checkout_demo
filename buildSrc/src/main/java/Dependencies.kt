object Dependencies {
    object General {
        val androidXCore by lazy { "androidx.core:core-ktx:1.7.0" }
        val androidXLifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1" }
    }

    object Compose {
        val activity by lazy { "androidx.activity:activity-compose:1.3.1" }
        val ui by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
        val preview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
        val material by lazy { "androidx.compose.material:material:${Versions.compose}" }
        val testJunit by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
        val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
        val uiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.compose}" }
    }

    object Test {
        val junit4 by lazy { "junit:junit:4.13.2" }
        val androidJunit by lazy { "androidx.test.ext:junit:1.1.3" }
    }

    object Network {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:2.9.0" }
        val moshi by lazy { "com.squareup.moshi:moshi:1.14.0" }
    }
}