package com.harismexis.creditscore.parser

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String
}