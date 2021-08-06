package com.harismexis.creditscore.framework.di.module

import com.harismexis.creditscore.presentation.home.fragment.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment
}
