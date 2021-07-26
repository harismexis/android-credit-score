package com.harismexis.creditscore.core.datasource

import com.harismexis.creditscore.core.domain.CreditReport

interface CreditBaseLocalDataSource {

    suspend fun save(item: CreditReport)

    suspend fun getCreditReport(): CreditReport?
}