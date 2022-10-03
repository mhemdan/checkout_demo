package com.hemdan.data.datasource.remote

import com.hemdan.data.model.VerifyCardRequest
import com.hemdan.data.model.VerifyCardResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface VerifyCardApi {

    @POST("pay")
    suspend fun verify(@Body request: VerifyCardRequest): VerifyCardResponse
}