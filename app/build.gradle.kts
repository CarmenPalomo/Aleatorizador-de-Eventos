plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.personajecreacion"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.personajecreacion"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-common-ktx:20.4.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Importamos la Firebase BoM (se supone que contiene todas las librerías)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    // TODO: Add the dependencies for Firebase products you want to use
    // Implementamos google analytics
    implementation("com.google.firebase:firebase-analytics")
    // Implementamos la plataforma de autentificación
    implementation("com.google.firebase:firebase-auth")
    // Implementación de Realtime Database
    implementation("com.google.firebase:firebase-database-ktx")
    //implementation("com.google.android.gms:play-services-auth:20.7.0")
    // Si queremos añadir más librerías podemos consultar las que hay en
    // https://firebase.google.com/docs/android/setup#available-libraries

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // SDK Dialogflow
    implementation ("ai.api:sdk:2.0.7@aar")
    implementation ("ai.api:libai:1.6.12")

    // Google Cloud Dialogflow API
    implementation ("com.google.cloud:google-cloud-dialogflow:3.1.0")

    // Implementación de Java gRPC
    implementation ("io.grpc:grpc-okhttp:1.38.0")

}