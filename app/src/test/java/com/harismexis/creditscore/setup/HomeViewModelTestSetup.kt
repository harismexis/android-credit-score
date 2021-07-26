package com.harismexis.creditscore.setup

import androidx.lifecycle.Observer
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.core.repository.CreditRepository
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito.`when`

open class HomeViewModelTestSetup: UnitTestSetup() {

    @Mock
    protected lateinit var mockRepository: CreditRepository
    @Mock
    lateinit var observer: Observer<CreditResult>
    protected  lateinit var subject: HomeViewModel

    protected fun setupClassUnderTest() {
        subject = HomeViewModel(mockRepository)
        subject.credit.observeForever(observer)
    }

    protected fun mockRemoteCall(mockCreditReport: CreditReport) {
        runBlocking {
        `when`(mockRepository.getRemoteCreditReport()).thenReturn(mockCreditReport)
        }
    }

    protected fun mockRemoteCall(e: Exception) {
        runBlocking {
            `when`(mockRepository.getRemoteCreditReport()).thenThrow(e)
        }
    }

    protected fun mockLocalCall(mockCreditReport: CreditReport) {
        runBlocking {
            `when`(mockRepository.getLocalCreditReport()).thenReturn(mockCreditReport)
        }
    }

    protected fun mockLocalCall(e: Exception) {
        runBlocking {
            `when`(mockRepository.getLocalCreditReport()).thenThrow(e)
        }
    }

}