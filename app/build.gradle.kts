plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.dm_garg_07"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.dm_garg_07"
        minSdk = 26
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

    implementation("androidx.room3:room3-runtime:3.0.3")
    //para anotaciones - @Entity, @Insert...
    annotationProcessor("androidx.room3:room3-compiler:3.0.3")
    //ksp()
}