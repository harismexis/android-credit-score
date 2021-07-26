package com.harismexis.creditscore.tests

import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import com.harismexis.creditscore.framework.data.remote.datasource.CreditRemoteDataSource
import com.harismexis.creditscore.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(JUnit4::class)
class CreditRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockApi: CreditApi

    private lateinit var subject: CreditRemoteDataSource

    @Before
    fun doBefore() {
        subject = CreditRemoteDataSource(mockApi)
    }

    @Test
    fun getRemoteItem_gotExpectedItem() {
        runBlocking {
            // given
            val mockResponse = mockProvider.getMockResponse()
            `when`(mockApi.getCreditInfo()).thenReturn(mockResponse)

            // when
            val actual = subject.getCreditReport()

            // then
            verify(mockApi, times(1)).getCreditInfo()
            Assert.assertEquals(mockResponse.creditReportInfo!!.score, actual!!.score)
            Assert.assertEquals(
                mockResponse.creditReportInfo!!.maxScoreValue,
                actual.maxScoreValue
            )
        }
    }
}