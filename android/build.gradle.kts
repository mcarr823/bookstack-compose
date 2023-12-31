plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

}

group = "dev.mcarr"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    val room_version = "2.5.2"
    implementation(project(":common"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("com.arkivanov.decompose:decompose:2.0.0")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:2.0.0")

}

android {
    namespace = "dev.mcarr.android"
    compileSdkVersion(34)
    defaultConfig {
        applicationId = "dev.mcarr.android"
        minSdkVersion(24)
        targetSdkVersion(34)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

}