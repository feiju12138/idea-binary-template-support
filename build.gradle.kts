import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    id("java")
    id("org.jetbrains.intellij.platform")
}

group = "cn.fj.loli"
version = "1.0.0"

val localIdePath = providers.gradleProperty("localIdePath")
val hexSupportPluginPath = providers.gradleProperty("hexSupportPluginPath")
val adjacentHexSupport = layout.projectDirectory.file(
    "../idea-hex-support/build/distributions/idea-hex-support-3.0.0.zip"
)

dependencies {
    intellijPlatform {
        if (localIdePath.isPresent) {
            local(localIdePath.get())
        } else {
            intellijIdea("2025.1")
        }

        when {
            hexSupportPluginPath.isPresent -> localPlugin(hexSupportPluginPath.get())
            adjacentHexSupport.asFile.isFile -> localPlugin(adjacentHexSupport.asFile)
            else -> plugin("cn.fj.loli.hexsupport:3.0.0")
        }
        testFramework(TestFrameworkType.Platform)
    }
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.12.2")
}

intellijPlatform {
    pluginConfiguration {
        id = "cn.fj.loli.binarytemplatesupport"
        name = "Binary Template Support"
        version = project.version.toString()
        description = """
            <p>Language support for 010 Editor Binary Template (.bt) files in IntelliJ-based IDEs.</p>
            <p>Provides syntax highlighting, completion for the official language vocabulary, comment support, brace matching, and optional binary structure analysis when Hex Support is installed.</p>
        """.trimIndent()
        changeNotes = """
            <ul>
                <li>1.0.0: Add .bt file recognition, native editor syntax highlighting, completion for keywords, built-in types, template attributes, constants and official built-in functions, comment and brace support, configurable colors, and an optional Hex Support structure provider.</li>
            </ul>
        """.trimIndent()
        vendor {
            name = "feiju12138"
            url = "https://github.com/feiju12138/idea-binary-template-support"
        }
        ideaVersion {
            sinceBuild = "251"
        }
    }

    pluginVerification {
        ides {
            current()
        }
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
        options.encoding = "UTF-8"
    }

    test {
        useJUnitPlatform()
    }

    named("buildSearchableOptions") {
        enabled = false
    }

    named("prepareJarSearchableOptions") {
        enabled = false
    }

    named("jarSearchableOptions") {
        enabled = false
    }
}
