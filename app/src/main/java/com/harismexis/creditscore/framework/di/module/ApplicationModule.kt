package com.harismexis.creditscore.framework.di.module

import android.content.Context
import com.harismexis.creditscore.framework.application.MainApplication
import com.harismexis.creditscore.framework.data.local.dao.CreditInfoLocalDao
import com.harismexis.creditscore.framework.data.local.schema.CreditInfoDatabase
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideLocalCreditReportDao(app: MainApplication): CreditInfoLocalDao {
        return CreditInfoDatabase.getDatabase(app.applicationContext).getCreditInfoLocalDao()
    }

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

}