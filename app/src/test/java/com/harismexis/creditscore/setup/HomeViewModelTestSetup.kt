package com.harismexis.creditscore.setup

import androidx.lifecycle.Observer
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.core.repository.CreditRepository
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.presentation.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK

open class HomeViewModelTestSetup: UnitTestSetup() {

    @MockK
    protected lateinit var mockRepository: CreditRepository
    @MockK
    lateinit var observer: Observer<CreditResult>
    protected  lateinit var subject: HomeViewModel

    protected fun setupClassUnderTest() {
        subject = HomeViewModel(mockRepository)
        subject.credit.observeForever(observer)
    }

    protected fun mockRemoteCall(mockCreditReport: CreditReport) {
        coEvery { mockRepository.getRemoteCreditReport() }.returns(mockCreditReport)
    }

    protected fun mockRemoteCall(e: Exception) {
        coEvery { mockRepository.getRemoteCreditReport() }.throws(e)
    }

    protected fun mockLocalCall(mockCreditReport: CreditReport) {
        coEvery { mockRepository.getLocalCreditReport() }.returns(mockCreditReport)
    }

    protected fun mockLocalCall(e: Exception) {
        coEvery { mockRepository.getLocalCreditReport() }.throws(e)
    }

}