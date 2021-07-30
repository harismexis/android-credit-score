package com.harismexis.creditscore.framework.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.creditscore.BuildConfig
import com.harismexis.creditscore.framework.data.remote.api.CreditApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class CreditApiModule {

    @Provides
    @Singleton
    fun provideCreditApi(retrofit: Retrofit): CreditApi {
        return retrofit.create(CreditApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.CREDIT_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

}