package com.harismexis.creditscore.setup.di.component

import com.harismexis.creditscore.framework.di.module.FragmentBindingsModule
import com.harismexis.creditscore.setup.application.InstrumentedApplication
import com.harismexis.creditscore.setup.di.module.InstrumentedApplicationModule
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
        InstrumentedApplicationModule::class
    ]
)
interface InstrumentedComponent : AndroidInjector<InstrumentedApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: InstrumentedApplication): InstrumentedComponent
    }

}