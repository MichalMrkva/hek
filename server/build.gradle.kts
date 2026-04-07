plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
    application
}

group = "cz.uuk.hek"
version = "1.0.0"
application {
    mainClass.set("cz.uuk.hek.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.bundles.ktor.server)
    implementation(libs.bundles.exposed)
    implementation(libs.sqlite.jdbc)
    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
