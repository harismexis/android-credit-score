package com.harismexis.creditscore.core.datasource

import com.harismexis.creditscore.core.domain.CreditReport

interface CreditBaseRemoteDataSource {

    suspend fun getCreditReport(): CreditReport?
}