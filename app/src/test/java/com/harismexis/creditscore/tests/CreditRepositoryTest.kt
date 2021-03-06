package com.harismexis.creditscore.tests

import com.harismexis.creditscore.framework.extensions.toCreditReport
import com.harismexis.creditscore.setup.CreditRepositoryTestSetup
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CreditRepositoryTest : CreditRepositoryTestSetup() {

    @Before
    fun doBefore() {
        setupClassUnderTest()
    }

    @Test
    fun remoteDataSourceReturnsCreditReport_repositoryReturnsExpected() {
        // given
        val mock = mockProvider.getMockResponse().toCreditReport()
        mockRemoteCreditCall(mock)

        // when
        runBlocking {
            val actual = subject.getRemoteCreditReport()
            assertEquals(mock, actual)
        }

        // then
        verifyRemoteCreditCallDone()
    }

    @Test
    fun remoteDataSourceThrowsGettingCreditReport_repositoryThrowsExpected() {
        // given
        val errMsg = "error"
        mockRemoteCreditCall(IllegalStateException(errMsg))

        // when
        assertThrowsIllegalStateEx(errMsg) {
            runBlocking {
                subject.getRemoteCreditReport()
            }
        }

        // then
        verifyRemoteCreditCallDone()
    }

    @Test
    fun localDataSourceReturnsCreditReport_repositoryReturnsExpected() {
        // given
        val mock = mockProvider.getMockResponse().toCreditReport()
        mockLocalCreditCall(mock)

        // when
        runBlocking {
            val actual = subject.getLocalCreditReport()
            assertEquals(mock, actual)
        }

        // then
        verifyLocalCreditCallDone()
    }

    @Test
    fun localDataSourceThrowsGettingCreditReport_repositoryThrowsExpected() {
        // given
        val errMsg = "error"
        mockLocalCreditCall(IllegalStateException(errMsg))

        // when
        assertThrowsIllegalStateEx(errMsg) {
            runBlocking {
                subject.getLocalCreditReport()
            }
        }

        // then
        verifyLocalCreditCallDone()
    }

    @Test
    fun localDataSourceSavesExpectedItem() {
        // given
        val mock = mockProvider.getMockResponse().toCreditReport()

        // when
        runBlocking {
            subject.save(mock)
        }

        // then
        coVerify { mockLocal.save(mock) }
    }

}