package com.harismexis.creditscore.setup.di

import com.harismexis.creditscore.framework.di.module.FragmentBindingsModule
import com.harismexis.creditscore.setup.application.InstrumentedApplication
import com.harismexis.creditscore.setup.viewmodel.InstrumentedViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBindingsModule::class,
        InstrumentedViewModelModule::class,
    ]
)
interface InstrumentedComponent : AndroidInjector<InstrumentedApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedApplication): InstrumentedComponent
    }

}