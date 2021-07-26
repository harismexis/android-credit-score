package com.harismexis.creditscore.setup

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harismexis.creditscore.parser.MockCreditResponseProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule

abstract class UnitTestSetup {

    protected val mockProvider = MockCreditResponseProvider(UnitTestFileReader())

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    fun assertThrowsIllegalStateEx(
        expectedError: String,
        call: () -> Unit) {
        val exception = shouldThrow<IllegalStateException> {
            call.invoke()
        }
        exception.message shouldBe expectedError
    }

}

