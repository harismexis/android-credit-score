package com.harismexis.creditscore.framework.data.local.datasource

import com.harismexis.creditscore.core.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.domain.CreditReport
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.extensions.toCreditReport
import com.harismexis.creditscore.framework.extensions.toLocalItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditLocalDataSource @Inject constructor(
    private val dao: CreditInfoLocalDao
) : CreditBaseLocalDataSource {

    override suspend fun save(item: CreditReport) {
        dao.delete()
        dao.insert(item.toLocalItem())
    }

    override suspend fun getCreditReport(): CreditReport? =
        dao.getCreditReport()?.toCreditReport()
}