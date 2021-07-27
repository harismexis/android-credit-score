package com.harismexis.creditscore.core.data.repository

import com.harismexis.creditscore.core.data.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.data.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.domain.CreditReport
import javax.inject.Singleton

@Singleton
class CreditRepository(
    private val remote: CreditBaseRemoteDataSource,
    private val local: CreditBaseLocalDataSource,
) {

    suspend fun getRemoteCreditReport(): CreditReport? = remote.getCreditReport()

    suspend fun save(item: CreditReport) = local.save(item)

    suspend fun getLocalCreditReport(): CreditReport? = local.getCreditReport()

}