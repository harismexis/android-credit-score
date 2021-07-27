package com.harismexis.creditscore.framework.di.component

import com.harismexis.creditscore.framework.application.MainApplication
import com.harismexis.creditscore.framework.di.module.*
import com.harismexis.creditscore.framework.viewmodel.factory.ViewModelModule
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
        ViewModelModule::class,
        CreditApiModule::class,
        ApplicationModule::class,
        RepositoryModule::class,
        DatasourceModule::class
    ]
)
interface MainComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication) : MainComponent
    }

}