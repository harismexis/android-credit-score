package com.harismexis.creditscore.setup.di.module

import android.content.Context
import com.harismexis.creditscore.setup.application.InstrumentedApplication
import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(application: InstrumentedApplication): Context {
        return application.applicationContext
    }

}