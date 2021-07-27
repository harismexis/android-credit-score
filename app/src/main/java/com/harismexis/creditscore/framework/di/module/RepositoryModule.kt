package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.core.datasource.CreditBaseLocalDataSource
import com.harismexis.creditscore.core.datasource.CreditBaseRemoteDataSource
import com.harismexis.creditscore.core.repository.CreditRepository
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