plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    application
}

group = "dev.bacongubbe.wishlist"
version = "1.0.0"
application {
    mainClass.set("dev.bacongubbe.wishlist.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation("io.ktor:ktor-server-core:3.3.2")
    implementation("io.ktor:ktor-serialization-gson:3.3.2")
    implementation("io.ktor:ktor-server-content-negotiation:3.3.2")
    implementation("io.ktor:ktor-serialization-jackson:3.3.2")
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    implementation("app.cash.sqldelight:jdbc-driver:2.2.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.ktor:ktor-server-swagger:3.3.2")
    implementation("io.ktor:ktor-server-openapi:3.3.2")
}
kotlin {
    sourceSets.all {
        languageSettings.optIn("kotlinx.uuid.ExperimentalUuidApi")
    }
}

sqldelight {
    databases {
        create("WishlistDatabase") {
            packageName.set("dev.bacongubbe.wishlist")
            dialect("app.cash.sqldelight:postgresql-dialect:2.2.1")
        }
    }
}