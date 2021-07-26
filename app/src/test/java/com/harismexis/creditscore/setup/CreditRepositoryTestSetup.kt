package com.harismexis.creditscore.setup

import com.harismexis.creditscore.core.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.core.repository.CreditRepository
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

open class CreditRepositoryTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockRemote: CreditBaseRemoteDataSource

    @Mock
    protected lateinit var mockLocal: CreditBaseLocalDataSource

    protected lateinit var subject: CreditRepository

    protected fun setupClassUnderTest() {
        subject = CreditRepository(mockRemote, mockLocal)
    }

    protected fun mockRemoteCreditCall(mock: CreditReport?) {
        runBlocking {
            `when`(mockRemote.getCreditReport()).thenReturn(mock)
        }
    }

    protected fun mockRemoteCreditCall(e: Exception) {
        runBlocking {
            `when`(mockRemote.getCreditReport()).thenThrow(e)
        }
    }

    protected fun mockLocalCreditCall(mock: CreditReport?) {
        runBlocking {
            `when`(mockLocal.getCreditReport()).thenReturn(mock)
        }
    }

    protected fun mockLocalCreditCall(e: Exception) {
        runBlocking {
            `when`(mockLocal.getCreditReport()).thenThrow(e)
        }
    }

    protected fun verifyRemoteCreditCallDone() {
        runBlocking {
            verify(mockRemote, Mockito.times(1)).getCreditReport()
        }
    }

    protected fun verifyLocalCreditCallDone() {
        runBlocking {
            verify(mockLocal, Mockito.times(1)).getCreditReport()
        }
    }

}