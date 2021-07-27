package com.harismexis.creditscore.setup

import com.harismexis.creditscore.core.data.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.data.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.data.repository.CreditRepository
import com.harismexis.creditscore.core.domain.CreditReport
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK

open class CreditRepositoryTestSetup : UnitTestSetup() {

    @MockK
    protected lateinit var mockRemote: CreditBaseRemoteDataSource

    @MockK
    protected lateinit var mockLocal: CreditBaseLocalDataSource

    protected lateinit var subject: CreditRepository

    protected fun setupClassUnderTest() {
        subject = CreditRepository(mockRemote, mockLocal)
    }

    protected fun mockRemoteCreditCall(mock: CreditReport?) {
        coEvery { mockRemote.getCreditReport() }.returns(mock)
    }

    protected fun mockRemoteCreditCall(e: Exception) {
        coEvery { mockRemote.getCreditReport() }.throws(e)
    }

    protected fun mockLocalCreditCall(mock: CreditReport?) {
        coEvery { mockLocal.getCreditReport() }.returns(mock)
    }

    protected fun mockLocalCreditCall(e: Exception) {
        coEvery { mockLocal.getCreditReport() }.throws(e)
    }

    protected fun verifyRemoteCreditCallDone() {
        coVerify { mockRemote.getCreditReport() }
    }

    protected fun verifyLocalCreditCallDone() {
        coVerify { mockLocal.getCreditReport() }
    }

}