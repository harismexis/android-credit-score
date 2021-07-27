package com.harismexis.creditscore.core.data.datasource

import com.harismexis.creditscore.core.domain.CreditReport

interface CreditBaseRemoteDataSource {

    suspend fun getCreditReport(): CreditReport?
}