package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.framework.application.MainApplication
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.schema.CreditInfoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideCreditInfoLocalDao(app: MainApplication): CreditInfoLocalDao {
        return CreditInfoDatabase.getDatabase(app.applicationContext).getCreditInfoLocalDao()
    }

}