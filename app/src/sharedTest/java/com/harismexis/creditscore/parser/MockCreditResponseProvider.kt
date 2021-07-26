package com.harismexis.creditscore.parser

import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.remote.model.CreditResponse
import com.harismexis.creditscore.framework.extensions.toCreditReport

class MockCreditResponseProvider(private val reader: BaseFileReader) {

    companion object {
        const val FILE_CREDIT_RESPONSE_VALID = "credit_response.json"
        const val FILE_CREDIT_RESPONSE_NO_SCORE = "credit_response_no_score.json"
        const val FILE_CREDIT_RESPONSE_NO_MAX_SCORE = "credit_response_no_max_score.json"
        const val FILE_CREDIT_RESPONSE_NO_CREDIT_REPORT_INFO =
            "credit_response_no_credit_report_info.json"
        const val FILE_EMPTY_RESPONSE = "empty_response.json"
    }

    fun getMockCreditReport(): CreditReport = getMockResponse().toCreditReport()

    fun getMockResponse(): CreditResponse =
        toCreditResponse(reader.getFileAsString(FILE_CREDIT_RESPONSE_VALID))

    fun getMockResponseNoScore(): CreditResponse =
        toCreditResponse(reader.getFileAsString(FILE_CREDIT_RESPONSE_NO_SCORE))

    fun getMockResponseNoMaxScore(): CreditResponse =
        toCreditResponse(reader.getFileAsString(FILE_CREDIT_RESPONSE_NO_MAX_SCORE))

    fun getMockResponseNoCreditReportInfo(): CreditResponse =
        toCreditResponse(reader.getFileAsString(FILE_CREDIT_RESPONSE_NO_CREDIT_REPORT_INFO))

    fun getMockResponseEmptyJson(): CreditResponse =
        toCreditResponse(reader.getFileAsString(FILE_EMPTY_RESPONSE))

    private fun toCreditResponse(creditResponseString: String): CreditResponse {
        return toClassObject(creditResponseString)
    }


}