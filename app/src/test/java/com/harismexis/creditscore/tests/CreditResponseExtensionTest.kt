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
        val mock = mockProvider.getMockResponse()
        val expectedScore = mock.creditReportInfo!!.score
        val expectedMaxScore = mock.creditReportInfo!!.maxScoreValue

        // when
        val actual = mock.toCreditReport()

        // then
        Assert.assertNotNull(actual.score)
        Assert.assertEquals(expectedScore, actual.score)
        Assert.assertEquals(expectedMaxScore, actual.maxScoreValue)
    }

    @Test
    fun responseHasNoScore_convertToModelThrowsExpected() {
        // given
        val mock = mockProvider.getMockResponseNoScore()

        // then
        assertThrowsIllegalStateEx(NULL_SCORE) { mock.toCreditReport() }
    }

    @Test
    fun responseHasNoMaxScore_convertToModelThrowsExpected() {
        // given
        val mock = mockProvider.getMockResponseNoMaxScore()

        // then
        assertThrowsIllegalStateEx(NULL_MAX_SCORE) { mock.toCreditReport() }
    }

    @Test
    fun responseHasNoCreditReportInfo_convertToModelThrowsExpected() {
        // given
        val mock = mockProvider.getMockResponseNoCreditReportInfo()

        // then
        assertThrowsIllegalStateEx(NULL_CREDIT_REPORT_INFO) { mock.toCreditReport() }
    }

    @Test
    fun responseIsEmptyJson_convertToModelThrowsExpected() {
        // given
        val mock = mockProvider.getMockResponseEmptyJson()

        // then
        assertThrowsIllegalStateEx(NULL_CREDIT_REPORT_INFO) { mock.toCreditReport() }
    }
}