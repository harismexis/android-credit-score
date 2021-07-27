package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.core.data.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.data.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.data.repository.CreditRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideCreditRepository(
        remote: CreditBaseRemoteDataSource,
        local: CreditBaseLocalDataSource
    ): CreditRepository {
        return CreditRepository(remote, local)
    }
}