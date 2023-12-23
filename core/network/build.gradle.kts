@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    //alias(libs.plugins.kotlinx.serialization)
    kotlin("plugin.serialization") version "1.9.10"

}

android {
    namespace = "ua.dev.webnauts.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources.excludes.addAll(
            listOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                        "META-INF/LICENSE.md",
                        "META-INF/LICENSE-notice.md"
                )
        )
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler.dagger )
//    implementation(libs.kotlinx.serialization.json)


    implementation("io.ktor:ktor-client-core:2.3.2")
    implementation("io.ktor:ktor-client-android:2.3.0")
            implementation("io.ktor:ktor-client-serialization:2.3.2")
            implementation("io.ktor:ktor-client-logging:2.3.2")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.2")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
            implementation("io.ktor:ktor-client-auth:2.3.2")
            implementation("ch.qos.logback:logback-classic:1.4.0")

            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

//    implementation(libs.ktor.client.core)
//    implementation(libs.ktor.client.android)
//    implementation(libs.ktor.client.se)
//    //Ktor
//    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation(libs.ktor.client.logging)
//    implementation(libs.ktor.client.content.negotiation)
//    implementation(libs.ktor.client.auth)
//
//    implementation(libs.logback.classic)
//    implementation(libs.ktor.serialization.kotlinx.json)
//    implementation(libs.ktor.serialization.kotlinx.json)
}