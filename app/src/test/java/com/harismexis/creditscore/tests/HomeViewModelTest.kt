package com.harismexis.creditscore.tests

import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.setup.HomeViewModelTestSetup
import io.mockk.coVerify
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest : HomeViewModelTestSetup() {

    @Before
    fun setup() {
        setupClassUnderTest()
    }

    @Test
    fun creditCallReturnsData_liveDataUpdated() {
        // given
        val mockCredit = mockProvider.getMockCreditReport()
        mockRemoteCall(mockCredit)
        mockLocalCall(mockCredit)

        // when
        subject.getCreditReport()

        // then
        verifyCreditCallDone()
        verify { observer.onChanged(CreditResult.Success(mockCredit)) }
    }

    @Test
    fun creditCallThrowsError_liveDataUpdatedIfCache() {
        // given
        val mockCredit = mockProvider.getMockCreditReport()
        val error = IllegalStateException("Error getting remote credit report")
        mockRemoteCall(error)
        mockLocalCall(mockCredit)

        // when
        subject.getCreditReport()

        // then
        verifyCreditCallDone()
        verify { observer.onChanged(CreditResult.Success(mockCredit)) }
    }

    @Test
    fun creditCallThrowsError_liveDataUpdatedIfNoCache() {
        // given
        val error1 = IllegalStateException("Error")
        mockRemoteCall(error1)
        val error = IllegalStateException("Error")
        mockLocalCall(error)

        // when
        subject.getCreditReport()

        // then
        verifyCreditCallDone()
        verify{ observer.onChanged(CreditResult.Error(error)) }
    }

    private fun verifyCreditCallDone() = coVerify {
        mockRepository.getRemoteCreditReport()
    }
}