package com.harismexis.creditscore.framework.data.remote.datasource

import com.harismexis.creditscore.core.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import com.harismexis.creditscore.framework.extensions.toCreditReport
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditRemoteDataSource @Inject constructor(
    private val api: CreditApi
) : CreditBaseRemoteDataSource {

    override suspend fun getCreditReport(): CreditReport? = api.getCreditInfo()?.toCreditReport()
}