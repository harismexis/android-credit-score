package com.harismexis.creditscore.framework.data.remote.api

import com.harismexis.creditscore.framework.data.remote.model.CreditResponse
import retrofit2.http.GET

interface CreditApi {

    @GET("endpoint.json")
    suspend fun getCreditInfo(): CreditResponse?
}