import com.google.protobuf.gradle.GenerateProtoTask
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.protobuf")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}


android {
    namespace = "com.example.skilllink"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.skilllink"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("BASE_URL")}\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"${localProperties.getProperty("BASE_URL")}\"")
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
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // exo-player dependencies
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.media3.ui)

    // for theme purpose
    implementation(libs.androidx.appcompat)

    // proto datastore with protobuf files
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.proto)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    // Hilt core
    implementation(libs.hilt.android)
    implementation(libs.firebase.firestore.ktx)
    // Hilt compiler (ksp)
    ksp(libs.hilt.android.compiler)
    // Hilt Navigation for Compose
    implementation(libs.androidx.hilt.navigation.compose)

    // for navigation
    implementation(libs.androidx.navigation.compose)
    // serialization
    implementation(libs.kotlinx.serialization.json)
    // compose ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Network calls
    implementation(libs.retrofit)
    // Json to kotlin object mapping
    implementation(libs.converter.gson)
    // Image loading
    implementation(libs.coil.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins{
                create("java") {
                    option("lite")
                }
                create("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val protoTask =
                project.tasks.getByName("generate" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Proto") as GenerateProtoTask

            project.tasks.getByName("ksp" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Kotlin") {
                dependsOn(protoTask)
                (this as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>).setSource(
                    protoTask.outputBaseDir
                )
            }
        }
    }
}