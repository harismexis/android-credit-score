package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.presentation.ui.base.BaseFragment
import com.harismexis.creditscore.presentation.ui.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}
