package com.harismexis.creditscore.tests

import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import com.harismexis.creditscore.framework.data.remote.datasource.CreditRemoteDataSource
import com.harismexis.creditscore.setup.UnitTestSetup
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CreditRemoteDataSourceTest : UnitTestSetup() {

    @MockK
    private lateinit var mockApi: CreditApi

    private lateinit var subject: CreditRemoteDataSource

    @Before
    fun doBefore() {
        subject = CreditRemoteDataSource(mockApi)
    }

    @Test
    fun getRemoteItem_gotExpectedItem() {
        // given
        val mockResponse = mockProvider.getMockResponse()
        coEvery { mockApi.getCreditInfo() }.returns(mockResponse)

        // when
        var actual: CreditReport?
        runBlocking {
            actual = subject.getCreditReport()
        }
        // then
        coVerify { mockApi.getCreditInfo() }
        Assert.assertEquals(mockResponse.creditReportInfo!!.score, actual!!.score)
        Assert.assertEquals(
            mockResponse.creditReportInfo!!.maxScoreValue,
            actual!!.maxScoreValue
        )
    }
}