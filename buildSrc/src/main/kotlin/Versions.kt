import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version.
 */
object Versions {
    const val org_jetbrains_kotlin: String = "1.3.60"

    const val androidx_test: String = "1.2.0"

    const val com_android_tools_build_gradle: String = "3.5.2"

    const val de_fayard_refreshversions_gradle_plugin: String = "0.7.0" // available: "0.8.4"

    const val gradle_bintray_plugin: String = "1.8.4"

    const val lifecycle_extensions: String = "2.1.0"

    const val fragment_testing: String = "1.1.0"

    const val ktlint_gradle: String = "9.1.0" // available: "9.1.1"

    const val lint_gradle: String = "26.5.2"

    const val appcompat: String = "1.1.0"

    const val junit_ktx: String = "1.1.1"

    const val ktlint: String = "0.35.0"

    const val rxjava: String = "2.2.14"

    const val aapt2: String = "3.5.2-5435860"

    /**
     * Current version: "5.6.4"
     * See issue 19: How to update Gradle itself?
     * https://github.com/jmfayard/buildSrcVersions/issues/19
     */
    const val gradleLatestVersion: String = "6.0.1"
}
