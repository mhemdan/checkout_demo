package com.hemdan.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VerifyCardRequest(
    @Json(name = "cvv")
    val cvv: String,
    @Json(name = "expiry_month")
    val expiryMonth: String,
    @Json(name = "expiry_year")
    val expiryYear: String,
    @Json(name = "failure_url")
    val failureUrl: String,
    @Json(name = "number")
    val number: String,
    @Json(name = "success_url")
    val successUrl: String
)