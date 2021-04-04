plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    compileSdkVersion(project.extra.get("compileSdkVersion") as Int)
    buildToolsVersion(project.extra.get("buildToolsVersion") as String)
    defaultConfig {
        minSdkVersion(project.extra.get("minSdkVersion") as Int)
        targetSdkVersion(project.extra.get("compileSdkVersion") as Int)
        versionCode = 1
        versionName = project.extra.get("PUBLISH_VERSION") as String
    }

    lintOptions {
        isAbortOnError = false
    }
}

// This configuration is used to publish the library to a local repo while a being forked and modified.
// It should really be set up so that the version are all in line, and set to be a SNAPSHOT.
// The version listed here is a temp hack to allow me to keep working.
android.libraryVariants

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(project.name) {


        groupId = project.extra.get("PUBLISH_GROUP_ID") as String
                artifactId = project.extra.get("PUBLISH_ARTIFACT_ID") as String
                version  = (project.extra.get("PUBLISH_VERSION") as String) + "-SNAPSHOT"

            //artifact bundleRelease
        }
    }
}

apply(from = "https://raw.githubusercontent.com/blundell/release-android-library/master/android-release-aar.gradle")

dependencies {
    api(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.exifInterface)
}

