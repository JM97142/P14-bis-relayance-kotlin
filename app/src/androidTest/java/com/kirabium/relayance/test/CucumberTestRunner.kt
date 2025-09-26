package com.kirabium.relayance.test

import android.os.Bundle
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions
import java.io.File

@CucumberOptions(
    features = ["classpath:features"],
    glue = ["com.kirabium.relayance.cucumber.steps"],
    plugin = ["pretty"]
)
class CucumberTestRunner : CucumberAndroidJUnitRunner() {

    override fun onCreate(bundle: Bundle) {
        // On injecte dynamiquement la config des plugins
        bundle.putString("plugin", pluginConfigurationString)
        File(absoluteFilesPath).mkdirs()
        super.onCreate(bundle)
    }

    private val pluginConfigurationString: String
        get() = listOf(
            "pretty",
            "html:${absoluteFilesPath}/cucumber.html"
        ).joinToString(", ")

    private val absoluteFilesPath: String
        get() {
            val directory = targetContext.getExternalFilesDir(null)
            return File(directory, "reports").absolutePath
        }
}