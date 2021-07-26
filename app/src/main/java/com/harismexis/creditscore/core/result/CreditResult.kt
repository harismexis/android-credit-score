package com.harismexis.creditscore.core.result

import com.harismexis.creditscore.core.domain.CreditReport

sealed class CreditResult {
    data class Success(val creditReport: CreditReport): CreditResult()
    data class Error(val error: Exception): CreditResult()
}