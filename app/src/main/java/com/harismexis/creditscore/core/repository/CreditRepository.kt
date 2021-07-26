package com.harismexis.creditscore.core.repository

import com.harismexis.creditscore.core.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.domain.CreditReport

class CreditRepository(
    val remote: CreditBaseRemoteDataSource,
    val local: CreditBaseLocalDataSource,
) {

    suspend fun getRemoteCreditReport(): CreditReport? = remote.getCreditReport()

    suspend fun save(item: CreditReport) = local.save(item)

    suspend fun getLocalCreditReport(): CreditReport? = local.getCreditReport()

}