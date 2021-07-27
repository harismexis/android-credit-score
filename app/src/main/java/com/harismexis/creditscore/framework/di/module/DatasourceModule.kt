package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.core.data.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.data.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.datasource.CreditLocalDataSource
import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import com.harismexis.creditscore.framework.data.remote.datasource.CreditRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DatasourceModule {

    @Provides
    fun provideCreditBaseRemoteDataSource(
        api: CreditApi,
    ): CreditBaseRemoteDataSource {
        return CreditRemoteDataSource(api)
    }

    @Provides
    fun provideCreditBaseLocalDataSource(
        dao: CreditInfoLocalDao
    ): CreditBaseLocalDataSource {
        return CreditLocalDataSource(dao)
    }
}