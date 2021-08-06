package com.harismexis.creditscore.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.creditscore.core.result.CreditResult
import com.harismexis.creditscore.presentation.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import io.mockk.mockk
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

val mockVm: HomeViewModel = mockk(relaxed = true)
var mockCreditResult = MutableLiveData<CreditResult>()

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        provideMockVmMap()[modelClass]?.get() as T
}

fun provideMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeViewModel::class.java] = Provider { mockVm }
    return viewModels
}

@Module
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
    : ViewModelProvider.Factory
}