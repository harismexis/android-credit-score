package com.harismexis.creditscore.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.creditscore.parser.MockCreditResponseProvider
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

abstract class UnitTestSetup {

    protected val mockProvider = MockCreditResponseProvider(UnitTestFileReader())

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var closeable: AutoCloseable? = null

    @Before
    open fun openMocks() {
        closeable = MockitoAnnotations.openMocks(this)
    }

    @After
    @Throws(Exception::class)
    open fun releaseMocks() {
        closeable?.close()
    }

    protected fun assertThrowsIllegalStateEx(
        expectedError: String,
        call: () -> Unit) {
        val expectedException = Assert.assertThrows(IllegalStateException::class.java) {
            call.invoke()
        }
        Assert.assertEquals(expectedError, expectedException.message)
    }

}

