package com.harismexis.creditscore.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTest {

    companion object {
        const val PACKAGE_NAME = "com.harismexis.creditscore"
    }

    @Test
    fun verifyPackageNameIsTheExpected() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE_NAME, appContext.packageName)
    }
}