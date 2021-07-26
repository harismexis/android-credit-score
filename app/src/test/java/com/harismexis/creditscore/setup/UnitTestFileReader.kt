package com.harismexis.creditscore.setup

import com.harismexis.creditscore.parser.BaseFileReader

class UnitTestFileReader : BaseFileReader() {

    override fun getFileAsString(filePath: String): String =
        ClassLoader.getSystemResource(filePath).readText()
}

