package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.core.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.repository.CreditRepository
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.datasource.CreditLocalDataSource
import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import com.harismexis.creditscore.framework.data.remote.datasource.CreditRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideBaseCreditRepository(
        remote: CreditBaseRemoteDataSource,
        local: CreditBaseLocalDataSource
    ): CreditRepository {
        return CreditRepository(remote, local)
    }

    @Provides
    fun provideCreditRemoteDataSource(
        api: CreditApi,
    ): CreditBaseRemoteDataSource {
        return CreditRemoteDataSource(api)
    }

    @Provides
    fun provideCreditLocalDataSource(
        dao: CreditInfoLocalDao
    ): CreditBaseLocalDataSource {
        return CreditLocalDataSource(dao)
    }
}