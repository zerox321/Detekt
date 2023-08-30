plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.example.detektcompiler"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.detektcompiler"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")


}
// Kotlin DSL
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
//detekt {
//    // Define the detekt configuration(s) you want to use.
//    // Defaults to the default detekt configuration.
//    config.setFrom("path/to/config.yml")
//
//    // Applies the config files on top of detekt's default config file. `false` by default.
//    buildUponDefaultConfig = false
//
//    // Turns on all the rules. `false` by default.
//    allRules = false
//
//    // Specifying a baseline file. All findings stored in this file in subsequent runs of detekt.
//    baseline = file("path/to/baseline.xml")
//
//    // Disables all default detekt rulesets and will only run detekt with custom rules
//    // defined in plugins passed in with `detektPlugins` configuration. `false` by default.
//    disableDefaultRuleSets = false
//
//    // Adds debug output during task execution. `false` by default.
//    debug = false
//
//    // Kill switch to turn off the Compiler Plugin execution entirely.
//    enableCompilerPlugin = true
//}