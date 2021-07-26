package com.harismexis.creditscore.setup.util

import androidx.test.platform.app.InstrumentationRegistry
import com.harismexis.creditscore.parser.BaseFileReader

class InstrumentedFileReader: BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        InstrumentationRegistry.getInstrumentation().context.classLoader
            .getResource(filePath).readText()

}

