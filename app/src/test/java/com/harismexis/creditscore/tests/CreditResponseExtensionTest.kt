package com.harismexis.creditscore.tests

import com.harismexis.creditscore.framework.extensions.NULL_CREDIT_REPORT_INFO
import com.harismexis.creditscore.framework.extensions.NULL_MAX_SCORE
import com.harismexis.creditscore.framework.extensions.NULL_SCORE
import com.harismexis.creditscore.framework.extensions.toCreditReport
import com.harismexis.creditscore.setup.UnitTestSetup
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CreditResponseExtensionTest : UnitTestSetup() {

    @Test
    fun responseHasAllData_modelHasExpectedData() {
        // given
        val mockResponse = mockProvider.getMockResponse()
        val expectedScore = mockResponse.creditReportInfo!!.score
        val expectedMaxScore = mockResponse.creditReportInfo!!.maxScoreValue

        // when
        val model = mockResponse.toCreditReport()

        // then
        Assert.assertNotNull(model.score)
        Assert.assertEquals(expectedScore, model.score)
        Assert.assertEquals(expectedMaxScore, model.maxScoreValue)
    }

    @Test
    fun responseHasNoScore_conversionToUiModelThrowsException() {
        // given
        val response = mockProvider.getMockResponseNoScore()

        // then
        assertThrowsIllegalStateEx(NULL_SCORE) { response.toCreditReport() }
    }

    @Test
    fun responseHasNoMaxScore_conversionToUiModelThrowsException() {
        // given
        val response = mockProvider.getMockResponseNoMaxScore()

        // then
        assertThrowsIllegalStateEx(NULL_MAX_SCORE) { response.toCreditReport() }
    }

    @Test
    fun responseHasNoCreditReportInfo_conversionToUiModelThrowsException() {
        // given
        val response = mockProvider.getMockResponseNoCreditReportInfo()

        // then
        assertThrowsIllegalStateEx(NULL_CREDIT_REPORT_INFO) { response.toCreditReport() }
    }

    @Test
    fun responseIsEmptyJson_conversionToUiModelThrowsException() {
        // given
        val response = mockProvider.getMockResponseEmptyJson()

        // then
        assertThrowsIllegalStateEx(NULL_CREDIT_REPORT_INFO) { response.toCreditReport() }
    }
}