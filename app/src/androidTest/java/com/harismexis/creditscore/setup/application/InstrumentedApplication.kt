package com.harismexis.creditscore.setup.application

import com.harismexis.creditscore.setup.di.DaggerInstrumentedComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InstrumentedApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val mainComponent = DaggerInstrumentedComponent.factory().create(this)
        mainComponent.inject(this)
        return mainComponent
    }
}