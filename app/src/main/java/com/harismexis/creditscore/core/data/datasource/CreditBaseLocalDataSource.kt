package com.harismexis.creditscore.core.data.datasource

import com.harismexis.creditscore.core.domain.CreditReport

interface CreditBaseLocalDataSource {

    suspend fun save(item: CreditReport)

    suspend fun getCreditReport(): CreditReport?
}