package com.harismexis.creditscore.framework.extensions

import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.local.table.LocalCreditReport
import com.harismexis.creditscore.framework.data.remote.model.CreditResponse

const val NULL_CREDIT_RESPONSE = "Null response"
const val NULL_MAX_SCORE = "Null maxScoreValue"
const val NULL_SCORE = "Null score"
const val NULL_CREDIT_REPORT_INFO = "Null creditReportInfo"

fun CreditResponse?.toCreditReport(): CreditReport {
    this?.let {
        it.creditReportInfo?.let { info ->
            info.score?.let { score ->
                info.maxScoreValue?.let { maxScore ->
                    return CreditReport(score, maxScore)
                }
                throw IllegalStateException(NULL_MAX_SCORE)
            }
            throw IllegalStateException(NULL_SCORE)
        }
        throw IllegalStateException(NULL_CREDIT_REPORT_INFO)
    }
    throw IllegalStateException(NULL_CREDIT_RESPONSE)
}

fun CreditReport.toLocalItem(): LocalCreditReport {
    return LocalCreditReport(1, this.score, this.maxScoreValue)
}

fun LocalCreditReport.toCreditReport(): CreditReport {
    return CreditReport(this.score, this.maxScoreValue)
}