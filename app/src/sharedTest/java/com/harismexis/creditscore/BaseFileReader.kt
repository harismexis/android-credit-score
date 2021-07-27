package com.harismexis.creditscore

abstract class BaseFileReader {

    abstract fun getFileAsString(filePath: String): String
}